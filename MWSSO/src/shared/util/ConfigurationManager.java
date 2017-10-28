package shared.util;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;

import java.io.*;

/**
 * Created by ethan on 6/18/17.
 */
public class ConfigurationManager {

    public enum ConfigurationType{
        PRODUCTION,
        DEMO,
        TESTING;
    }

    private static ConfigurationManager instance = null;

    private static ConfigurationManager getInstance() {
        if(instance == null){
            instance = new ConfigurationManager();
        }
        return instance;
    }

    /**
     * The configuration instance.
     */
    private Object config;

    /**
     * The demo state.
     */
    private ConfigurationType configurationType = ConfigurationType.PRODUCTION;

    private ConfigurationManager() {
        setupConfiguration();
    }

    /**
     * Applies configuration type.
     */
    private void setupConfiguration(){
        switch(this.configurationType){
            case PRODUCTION:
                this.loadConfigurations("prod_config.json");
                break;
            case DEMO:
                this.loadConfigurations("demo_config.json");
                break;
            case TESTING:
                this.loadConfigurations("test_config.json");
                break;
            default:
                break;
        }
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
            System.out.println("Could not load settings " + fileUrl);
        }
    }

    /**
     * Fetches any setting strictly related to the database configuration.
     * @param query
     * @return
     */
    private Object _fetchDbConfiguration(String query){
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

    public static Object fetchDbConfiguration(String query){
        return getInstance()._fetchDbConfiguration(query);
    }

    /**
     * Fetches any setting strictly related to the dao dependency configuration.
     * @param query
     * @return
     */
    private Object _fetchDaoDependencyConfiguration(String query){
        try{
            // Execute query and return result as a generic object type.
            query = query == null ? "" : query;
            String accessor = query != null && !query.equals("") ? "." : "";
            return JsonPath.read(this.config, "$.dao_dependencies_config" + accessor + query);
        }
        catch(Exception e){
            // return null if an error occurs.
            return null;
        }
    }

    public static Object fetchDaoDependencyConfiguration(String query){
        return getInstance()._fetchDaoDependencyConfiguration(query);
    }

    /**
     * Sets the configuration type and its file source.
     * @param type
     */
    private void _setConfigurationType(ConfigurationType type){
        if(type == null){
            this.configurationType = ConfigurationType.PRODUCTION;
        }
        else {
            this.configurationType = type;
        }
        this.setupConfiguration();
    }

    private Object _fetchDaoSetRegistryConfiguration(String query) {
        try{
            query = query == null ? "" : query;
            String accessor = query != null  && !query.equals("") ? "." : "";
            return JsonPath.read(this.config, "$.dao_set_registry" + accessor + query);
        }
        catch(Exception e) {
            return null;
        }
    }

    public static Object fetchDaoSetRegistryConfiguration(String query){
        return getInstance()._fetchDaoSetRegistryConfiguration(query);
    }


    public static void setConfigurationType(ConfigurationType type){
        getInstance()._setConfigurationType(type);
    }
}
