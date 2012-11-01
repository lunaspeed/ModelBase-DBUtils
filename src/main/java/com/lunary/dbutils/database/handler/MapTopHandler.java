
package com.lunary.dbutils.database.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.RowProcessor;

/**
 * For retreiving the top rows from a ResultSet as List of Maps
 * @author Steven
 *
 */
public class MapTopHandler extends AbstractTopHandler<Map<String, Object>> {
  
  /**
   * The RowProcessor implementation to use when converting rows 
   * into beans.
   */
  private RowProcessor rowProcessor = AbstractPaginationHandler.ROW_PROCESSOR;
  
  /**
   * Constructor
   * @param numOfRows number of rows to retrieve from top of the result set
   */
  public MapTopHandler(int numOfRows) {

    super(numOfRows);
  }
  
  /**
   * Constructor
   * @param numOfRows number of rows to retrieve from top of the result set
   * @param rowProcessor RowProcessor for processing each row
   */
  public MapTopHandler(int numOfRows, RowProcessor rowProcessor) {

    this(numOfRows);
    this.rowProcessor = rowProcessor;
  }

  @Override
  protected Map<String, Object> handleRow(ResultSet rs) throws SQLException {
    return rowProcessor.toMap(rs);
  }

}
