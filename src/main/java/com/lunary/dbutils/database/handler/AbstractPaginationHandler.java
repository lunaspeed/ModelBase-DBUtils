package com.lunary.dbutils.database.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.RowProcessor;

import com.lunary.database.BasePageContainer;
import com.lunary.database.PageContainer;
import com.lunary.dbutils.database.BaseBeanProcessor;
import com.lunary.dbutils.database.BaseColumnMapper;

abstract class AbstractPaginationHandler<E> implements ResultSetHandler<PageContainer<E>> {

    /**
     * @see com.bi.base.config.AppStaticConfig#ROWS_PER_PAGE
     */
    public static final int DEFAULT_ROWS_PER_PAGE = 20;
    private int rowsPerPage = 0;

    /**
     * Singleton processor instance that handlers share to save memory. Notice
     * the default scoping to allow only classes in this package to use this
     * instance.
     */
    static final RowProcessor ROW_PROCESSOR = new BasicRowProcessor(new BaseBeanProcessor(new BaseColumnMapper()));

    private int startingRow = 0;
    private int endingRow = 0;

    /**
     * 
     * @param page
     *            the page to get
     * @param rowsPerPage
     *            number of rows of record in each page
     */
    public AbstractPaginationHandler(int page, int rowsPerPage) {

        this.rowsPerPage = rowsPerPage;
        this.startingRow = ((page - 1) * rowsPerPage) + 1;
        this.endingRow = this.startingRow + rowsPerPage;
    }

    public AbstractPaginationHandler(int page) {
        this(page, DEFAULT_ROWS_PER_PAGE);
    }

    @Override
    public PageContainer<E> handle(ResultSet rs) throws SQLException {

        PageContainer<E> container = new BasePageContainer<E>();
        List<E> rows = new ArrayList<E>(rowsPerPage);
        int cnt = 0;
        while (rs.next()) {
            cnt += 1;
            if (cnt >= startingRow && cnt <= endingRow) {
                rows.add(this.handleRow(rs));
            }
        }
        container.setRows(rows);
        container.setTotalRows(cnt);
        int totalPages = cnt / rowsPerPage;
        if (cnt % rowsPerPage != 0) {
            totalPages += 1;
        }
        container.setTotalPages(totalPages);
        return container;
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
