package shared.util;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

import java.io.*;

/**
 * Created by ethan on 6/18/17.
 */
public class ConfigManager {
    private static ConfigManager instance = new ConfigManager();

    public static ConfigManager getInstance() {
        return instance;
    }

    /**
     * The configuration instance.
     */
    private Object config;

    private ConfigManager() {
        // load configuration.
        this.loadConfigurations("config.json");
    }

    /**
     * Loads the initial server-side configuration.
     * @param fileUrl
     */
    private void loadConfigurations(String fileUrl){
        try {
            Gson gson = new Gson();
            // Load configuration from source file.
            this.config  = gson.fromJson(new FileReader(fileUrl), Object.class);
        }
        catch(Exception e){
            System.out.println("Could not load settings");
        }
    }

    /**
     * Fetches any setting strictly related to the database configuration.
     * @param query
     * @return
     */
    public Object fetchDbConfiguration(String query){
        try {
            // Execute query and return result as a generic object type.
            query = query == null ? "" : query;
            String accessor = query != null && !query.equals("") ? "." : "";
            return JsonPath.read(this.config, "$.db_config" + accessor + query);
        }
        catch(Exception e){
            // return null if an error occurs.
            return null;
        }
    }
}
