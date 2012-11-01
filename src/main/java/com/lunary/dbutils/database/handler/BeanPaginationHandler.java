
package com.lunary.dbutils.database.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.RowProcessor;

/**
 * 
 * Pagination Handler for regular Java Beans
 * @author Steven
 *
 * @param <E> Bean Type
 */
public class BeanPaginationHandler<E> extends AbstractPaginationHandler<E> {

  /**
   * The RowProcessor implementation to use when converting rows 
   * into beans.
   */
  private RowProcessor rowProcessor = ROW_PROCESSOR;
  private final Class<E> type;
  
  /**
   * Constructor with default number of rows per page
   * @param type type of Bean
   * @param page number of page to retreive
   */
  public BeanPaginationHandler(Class<E> type, int page) {
    this(type, page, DEFAULT_ROWS_PER_PAGE);
  }

  /**
   * Constructor
   * @param type type of Bean
   * @param page number of page to retreive
   * @param rowsPerPage number of rows per page
   * @param rowProcessor RowProcessor for processing each row
   */
  public BeanPaginationHandler(Class<E> type, int page, int rowsPerPage, RowProcessor rowProcessor) {
    
    this(type, page, rowsPerPage);
    this.rowProcessor = rowProcessor;
  }

  /**
   * Constructor
   * @param type type of Bean
   * @param page number of page to retreive
   * @param rowsPerPage number of rows per page
   */
  public BeanPaginationHandler(Class<E> type, int page, int rowsPerPage) {
    super(page, rowsPerPage);
    this.type = type;
  }

  @Override
  protected E handleRow(ResultSet rs) throws SQLException {
    return rowProcessor.toBean(rs, type);
  }

}
