
package com.lunary.dbutils.database.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.RowProcessor;

/**
 * 
 * For retreiving the top rows from a ResultSet as List of Bean type E
 * @author Steven
 *
 * @param <E> type of bean in the list
 */
public class BeanTopHandler<E> extends AbstractTopHandler<E> {

  private final Class<E> type;
  
  /**
   * The RowProcessor implementation to use when converting rows 
   * into beans.
   */
  private RowProcessor rowProcessor = AbstractPaginationHandler.ROW_PROCESSOR;
  
  /**
   * Constructor
   * @param numOfRows number of rows to retrieve from top of the result set
   * @param clazz class of the list represent
   */
  public BeanTopHandler(int numOfRows, Class<E> clazz) {

    super(numOfRows);
    this.type = clazz;
  }
  
  /**
   * Constructor
   * @param numOfRows number of rows to retrieve from top of the result set
   * @param clazz class of the list represent
   * @param rowProcessor RowProcessor for processing each row
   */
  public BeanTopHandler(int numOfRows, Class<E> clazz, RowProcessor rowProcessor) {

    this(numOfRows, clazz);
    this.rowProcessor = rowProcessor;
  }

  @Override
  protected E handleRow(ResultSet rs) throws SQLException {
    return rowProcessor.toBean(rs, type);
  }

}
