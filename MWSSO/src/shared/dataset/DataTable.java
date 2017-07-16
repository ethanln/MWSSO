package shared.dataset;

import java.util.ArrayList;

/**
 * Created by ethan on 7/15/17.
 */
public class DataTable{
    /**
     * Table rows.
     */
    public ArrayList<DataRow> rows;

    /**
     * Constructor.
     */
    public DataTable(){
        this.rows = new ArrayList<DataRow>();
    }
}
