
package com.lunary.dbutils.database.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

/**
 * Handle Sql results to return first numOfRows rows
 * 
 * @author Steven
 * 
 * @param <E>
 */
public abstract class AbstractTopHandler<E> implements ResultSetHandler<List<E>> {

    private int numOfRows = 0;

    /**
     * Constructor
     * 
     * @param numOfRows
     */
    public AbstractTopHandler(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> handle(ResultSet rs) throws SQLException {

        List<E> rows = new ArrayList<E>(this.numOfRows);
        int cnt = 0;
        while (rs.next() && cnt < numOfRows) {

            rows.add(this.handleRow(rs));

            cnt++;
        }

        return rows;
    }

    /**
     * Row handler. Method converts current row into some Java object.
     * 
     * @param rs
     *            <code>ResultSet</code> to process.
     * @return row processing result
     * @throws SQLException
     *             error occurs
     */
    protected abstract E handleRow(ResultSet rs) throws SQLException;
}
