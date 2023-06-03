package de.donkaos.systensor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sys {
    public static class wait extends Wait{}
    private static String prefix = "SYS";
    private static boolean colorsEnabled = true;
    private static boolean debugging = false;
    public static boolean getDebugging(){
        return debugging;
    }
    public static boolean isColorsEnabled() {
        return colorsEnabled;
    }
    public static void setDebugging(boolean debugging){
        Sys.debugging = debugging;
    }
    public static void useColors(boolean colorsEnabled){
        Sys.colorsEnabled = colorsEnabled;
    }
    public static void setPrefix(String prefix){
        Sys.prefix = prefix;
    }
    public static String getPrefix() {
        return "[" + prefix + "]";
    }
    private static File logDir;
    private static boolean logFiles = false;
    private static BufferedWriter logWriter = null;

    // TODO: add method to check if String is a number

    public static OS getOS(){
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("windows 10")) {
            return OS.WINDOWS_10;
        } else if (osName.contains("windows 11")) {
            return OS.WINDOWS_11;
        } else if (osName.contains("mac")) {
            return OS.MAC;
        } else if (osName.contains("nix") || osName.contains("nux")) {
            return OS.LINUX;
        } else {
            return OS.UNKNOWN;
        }
    }

    public static void writeLog(String s){
        if (!logFiles){
            return;
        }
        try {
            logWriter.write(s + "\n");
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void enableLogfiles(String dir){
        logDir = new File(dir);
        if (!logDir.exists() || !logDir.isDirectory()){
            // is not there
            logDir.mkdirs();
        }
        try {
            File log = new File(dir, getLogTimeStamp() + ".log");
            if (!log.exists()){
                log.createNewFile();
            }
            logWriter = new BufferedWriter(new FileWriter(log));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logFiles = true;
    }

    private static String getLogTimeStamp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now).replace(":", "-").replace(" ", "_");
    }

    private static String getTimeStamp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void debug(String msg){
        if (!debugging){
            return;
        }
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [DEBUG] " + msg;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.YELLOW + "["+getTimeStamp()+"] " + getPrefix() + " " + Colors.BLACK + Colors.YELLOW_BACKGROUND + "[DEBUG]" + Colors.YELLOW + " " + msg + Colors.RESET);
            return;
        }
        System.out.println(s);
    }

    public static void log(String msg){
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [LOG] " + msg;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.BLUE_BOLD_BRIGHT + s + Colors.RESET);
            return;
        }
        System.out.println(s);
    }

    public static void success(String msg){
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [SUCCESS] " + msg;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.GREEN_BOLD_BRIGHT + s + Colors.RESET);
            return;
        }
        System.out.println(s);
    }

    public static void info(String msg){
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [INFO] " + msg;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.CYAN_BOLD_BRIGHT + s + Colors.RESET);
            return;
        }
        System.out.println(s);
    }

    public static void init(String msg){
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [INIT] " + msg;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.PURPLE_BOLD_BRIGHT + s + Colors.RESET);
            return;
        }
        System.out.println(s);
    }

    public static void warn(String msg){
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [WARN] " + msg;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.YELLOW_BOLD_BRIGHT + s + Colors.RESET);
            return;
        }
        System.out.println(s);
    }

    public static void warn(String header, String body){
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [WARN] " + header + "\n" + body;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.YELLOW_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [WARN] " + header + "\n" + body + Colors.RESET);
            return;
        }
        System.out.println(s);
    }

    public static void warn(String header, String body, Exception e){
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [WARN] " + header + "\n" + body + "\n" + e;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.YELLOW_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [WARN] " + header + "\n" + body + "\n" + e + Colors.RESET);
            return;
        }
        System.out.println(s);
    }


    public static void error(String msg){
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [ERROR] " + msg;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.RED_BOLD_BRIGHT + s + Colors.RESET);
            return;
        }
        System.out.println(s);
    }

    public static void error(String header, String body){
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [ERROR] " + header + "\n" + body;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.RED_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [ERROR] " + header + "\n" + body + Colors.RESET);
            return;
        }
        System.out.println(s);
    }
    public static void error(String header, String body,Exception e){
        String s = "["+getTimeStamp()+"] " + getPrefix() + " [ERROR] " + header + "\n" + body + "\n" + e;
        writeLog(s);
        if (isColorsEnabled()){
            System.out.println(Colors.RED_BOLD_BRIGHT + "["+getTimeStamp()+"] " + getPrefix() + " [ERROR] " + header + "\n" + body + "\n" + e + Colors.RESET);
            return;
        }
        System.out.println(s);
    }
}
