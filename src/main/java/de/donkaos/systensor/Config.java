package de.donkaos.systensor;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Config {

    private final File path;
    private final File file;
    private final File absolutFile;
    private final File corrupted;
    private Yaml yaml;
    private DumperOptions dumperOptions;
    private Map<String, Object> configMap;
    private Map<String, Object> mapDefaults;

    public Config(File path, File file) {
        this.path = path;
        this.file = file;
        this.corrupted = new File(path + "/corrupted");
        this.absolutFile = new File(path.toString(), file.getName());
        init();
    }

    public Config(String path, String file) {
        this.path = new File(path);
        this.file = new File(file);
        this.corrupted = new File(path + "/corrupted");
        this.absolutFile = new File(path, file);
        init();
    }

    public Config(String absolutPath) {
        this.path = new File(new File(absolutPath).getParent());
        this.file = new File(new File(absolutPath).getName());
        this.corrupted = new File(path + "/corrupted");
        this.absolutFile = new File(absolutPath);
        init();
    }

    public Config(File path, File file, Map<String, Object> mapDefaults) {
        this.mapDefaults = mapDefaults;
        this.path = path;
        this.file = file;
        this.corrupted = new File(path + "/corrupted");
        this.absolutFile = new File(path.toString(), file.getName());
        init();
    }

    public Config(String path, String file, Map<String, Object> mapDefaults) {
        this.mapDefaults = mapDefaults;
        this.path = new File(path);
        this.file = new File(file);
        this.corrupted = new File(path + "/corrupted");
        this.absolutFile = new File(path, file);
        init();
    }

    public Config(String absolutPath, Map<String, Object> mapDefaults) {
        this.mapDefaults = mapDefaults;
        this.path = new File(absolutPath).getParentFile();
        this.file = new File(new File(absolutPath).getName());
        this.corrupted = new File(path + "/corrupted");
        this.absolutFile = new File(absolutPath);
        init();
    }




    private void init(){
        // Yaml config
        dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
        /*dumperOptions.setProcessComments(true);*/
        yaml = new Yaml(dumperOptions);

        if (!this.absolutFile.exists()){
            if (!this.path.exists()){
                this.path.mkdir();
            }
            // try { this.absolutFile.createNewFile(); } catch (IOException e0){ }
            // new config Map
            if (mapDefaults != null && !mapDefaults.isEmpty()){
                configMap = mapDefaults;
                submit();
                return;
            }
            configMap = new LinkedHashMap<>();
            return;
        } else {
            try {
                // trying to load entry's from existing config
                configMap = yaml.load(new FileReader(absolutFile));
                if (configMap == null){
                    if (mapDefaults != null && !mapDefaults.isEmpty()){
                        configMap = mapDefaults;
                        submit();
                        return;
                    }
                } else if (configMap.isEmpty()){
                    if (mapDefaults != null && !mapDefaults.isEmpty()){
                        configMap = mapDefaults;
                        submit();
                        return;
                    }
                }
            } catch (Exception e1){
                // no entry's found in config or it's corrupted
                /*System.out.println("no entry's found in config or it's corrupted");*/
                if (!corrupted.exists()){
                    corrupted.mkdir();
                }
                String time_stamp = (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date());
                String corrupted_file_url = corrupted.getPath() + "\\" +  file +"(" + time_stamp + ").yml";
                // System.out.println("Config was corrupted, coping corrupted config to: " + corrupted_file_url + " and regenerating a new!");
                try {
                    /*System.out.println("Here: " + this.file.toPath() + " To: " + new File(corrupted_file_url).toPath());*/
                    Files.copy(this.absolutFile.toPath(), new File(corrupted_file_url).toPath());
                    this.absolutFile.createNewFile();
                } catch (Exception e2){ e2.printStackTrace(); }
                if (mapDefaults != null ){
                    configMap = mapDefaults;
                    submit();
                    return;
                }
                configMap = new LinkedHashMap<>();
                return;
            }
        }
    }


    /**
     * Sets an existing value
     * */
    public void set(String entry, Object value){
        configMap.replace(entry, value);
        submit();
    }
    /**
     * Gets an existing value
     * */
    public Object get(String entry){
        return configMap.get(entry);
    }

    public boolean getBoolean(String entry){
        return (boolean) configMap.get(entry);
    }

    public String getString(String entry){
        return (String) configMap.get(entry);
    }

    public Integer getInt(String entry) {
        return (Integer) configMap.get(entry);
    }
    /**
     * Adds a value
     * */
    public void add(String entry, Object value) {
        configMap.put(entry,value);
        submit();
    }
    /**
     * Removes an Entry
     * */
    public void remove(String entry){
        configMap.remove(entry);
        submit();
    }
    /**
     * Clears a value by Entry
     * */
    public void clear(String entry){
        configMap.put(entry, null);
        submit();
    }

    private void submit(){
        /*System.out.println("Submit!");*/
        try {
            FileWriter writer = new FileWriter(absolutFile);
            yaml.dump(configMap, writer);
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }


    public String getAbsolutPath() {
        return absolutFile.toString();
    }

    private void test(){
        System.out.println("Path: " + path + " File: " + file);
    }
    private Yaml getYaml() {
        return yaml;
    }
    private DumperOptions getDumperOptions() {
        return dumperOptions;
    }

}
