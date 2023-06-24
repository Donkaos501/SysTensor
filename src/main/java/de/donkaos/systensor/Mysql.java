package de.donkaos.systensor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Mysql {

    private final String host;
    private final int port;
    private final String user;
    private final String password;
    private final String database;
    private boolean isConnected = false;
    protected Connection connection;


    public Mysql(String host, int port, String user, String password, String database) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public Mysql(Config config, String configEntryHost, String configEntryPort, String configEntryUser, String configEntryPassword, String configEntryDatabase){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.host = config.getString(configEntryHost);
        this.port = config.getInt(configEntryPort);
        this.user = config.getString(configEntryUser);
        this.password = config.getString(configEntryPassword);
        this.database = config.getString(configEntryDatabase);
    }

    public boolean isConnected() {
        return isConnected;
    }

    public Connection getConnection(){
        return this.connection;
    }

    public boolean tableExists(String table){
        for (String s : getTables()) {
            if (s.equalsIgnoreCase(table)){
                return true;
            }
        }
        return false;
    }

    public List<String> getTables(){
        List<String> list = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();

            // Retrieve all table names from the specified database
            String[] tableTypes = { "TABLE" };
            ResultSet resultSet = metaData.getTables(null, null, "%", tableTypes);

            // Iterate over the result set and print table names
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                list.add(tableName);
            }
        } catch (SQLException e){
            e.printStackTrace();
            return list;
        }
        return list;
    }

    public boolean isInTable(UUID uuid, String tableID){
        if (!tableExists(tableID)){
            System.out.println("Error table not found!");
            return false;
        }
        String query = "SELECT COUNT(*) FROM " + tableID + " WHERE uuid=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, uuid.toString());
            ResultSet result = pstmt.executeQuery();
            result.next();
            int count = result.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            System.out.println("Exception while checking if row exists");
            e.printStackTrace();
            return false;
        }
    }


    public void deleteEntry(UUID uuid, String tableID){
        if (!tableExists(tableID)){
            System.out.println("Error table not found!");
            return;
        }
        String query = String.format("DELETE FROM %s WHERE uuid = ?", tableID);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid.toString());
            statement.executeUpdate();
            System.out.println("Row with UUID " + uuid + " deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting row with UUID " + uuid + ": " + e.getMessage());
        }
    }




    public void disconnect(){
        if (!isConnected){
            // is not connected
            System.out.println("Msql is not Connected!");
            return;
        }
        try {
            this.connection.close();
            isConnected = false;
            this.connection = null;
        } catch (SQLException e) {
            System.out.println("Connection closed failed.");
            e.printStackTrace();
        }
    }

    public void connect(){
        if (isConnected){
            // Already connected
            System.out.println("Already connected");
            return;
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(getURL(), getUser(), getPassword());
            System.out.println("Connected to database successfully!");
            this.connection = connection;
            isConnected = true;
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
        return;
    }

    public String getURL(){
        return "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDatabase();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }
}
