
package com.lunary.dbutils.database.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.RowProcessor;

/**
 * 
 * @author Steven
 *
 */
public class MapPaginationHandler extends AbstractPaginationHandler<Map<String, Object>> {

    /**
     * The RowProcessor implementation to use when converting rows into beans.
     */
    private RowProcessor rowProcessor = ROW_PROCESSOR;

    /**
     * Constructor
     * @param page number of total pages
     */
    public MapPaginationHandler(int page) {
        this(page, DEFAULT_ROWS_PER_PAGE);
    }

    /**
     * Constructor
     * @param page number of total pages
     * @param rowsPerPage row per page
     * @param rowProcessor RowProcessor to process each row
     */
    public MapPaginationHandler(int page, int rowsPerPage, RowProcessor rowProcessor) {

        this(page, rowsPerPage);
        this.rowProcessor = rowProcessor;
    }

    /**
     * Constructor
     * @param page number of total pages
     * @param rowsPerPage row per page
     */
    public MapPaginationHandler(int page, int rowsPerPage) {
        super(page, rowsPerPage);
    }

    @Override
    protected Map<String, Object> handleRow(ResultSet rs) throws SQLException {
        return rowProcessor.toMap(rs);
    }

}
