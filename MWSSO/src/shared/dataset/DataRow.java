package shared.dataset;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by ethan on 7/15/17.
 */
public class DataRow implements Iterable{
    /**
     * Column values of row.
     */
    private HashMap<String, String> columns;

    /**
     * Constructor
     */
    public DataRow(){
        this.columns = new HashMap<String, String>();
    }

    /**
     * Adds a new column value to the row.
     * @param key
     * @param value
     */
    public void add(String key, String value){
        this.columns.put(key, value);
    }

    @Override
    public Iterator iterator() {
        return this.columns.values().iterator();
    }

    /**
     * Fetches a column value in the row.
     * @param key
     * @return
     */
    public String get(String key){
        if(this.columns.containsKey(key)) {
            return this.columns.get(key);
        }
        return null;
    }

}
