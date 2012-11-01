package com.lunary.dbutils.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.BeanProcessor;

import com.lunary.database.ColumnMapper;

/**
 * An extention to {@link org.apache.commons.dbutils.BeanProcessor} to handle
 * Enum. Also skips all File/InputStream properties.
 * 
 * @author Steven
 * 
 */
public class BaseBeanProcessor extends BeanProcessor {

//  public static final LobHandler DEFAULT_LOB_HANDLER = new DefaultLobHandler();
//  private LobHandler lobHandler;
  private final ColumnMapper columnMapper;
//  /**
//   * Constructor
//   */
//  public BaseBeanProcessor() {
//    this(DEFAULT_LOB_HANDLER);
//  }

//  /**
//   * Constructor
//   * 
//   * @param lobHandler
//   *          a specified LobHandler to handle BLOB and CLOB
//   */
//  public BaseBeanProcessor(LobHandler lobHandler) {
//    super();
//    this.lobHandler = (lobHandler == null ? DEFAULT_LOB_HANDLER : lobHandler);
//  }
  
  public BaseBeanProcessor(ColumnMapper columnMapper) {
    this.columnMapper = columnMapper;
  }

  @Override
  //@SuppressWarnings({"unchecked", "rawtypes"})
  protected Object processColumn(ResultSet rs, int index, Class<?> propType) throws SQLException {

    Object value = columnMapper.toObject(rs, index, propType);
    
    if(value == ColumnMapper.UNKNOWN) {
      value = super.processColumn(rs, index, propType);
    }
    
//    if (propType.equals(String.class)) {
////      int columnType = rs.getMetaData().getColumnType(index);
////      if(columnType == Types.CLOB) {
////        value = lobHandler.getClobAsString(rs, index);
////      }
////      else {
//        value = rs.getString(index);
////      }
//    }
//    else if (propType.equals(BigDecimal.class)) {
//      value = rs.getBigDecimal(index);
//    }
//    else if (propType.equals(java.sql.Date.class)) {
//      value = rs.getDate(index);
//    }
//    else if (Enum.class.isAssignableFrom(propType)) {
//      
//      String val = rs.getString(index);
//      try {
//        
//        if (val != null) {
//          value = Enum.valueOf((Class<? extends Enum>) propType, val);
//        }
//      }
//      catch (IllegalArgumentException e) {
//        throw new SQLException("Error converting to Enum ["+propType.getCanonicalName()+"] for column index " + index + " of value ["+val+"] : " + e.getMessage(), e);
//      }
//    }
////    else if (propType.equals(InputStream.class)) {
////      value = lobHandler.getBlobAsBinaryStream(rs, index);
////    }
//    else {
//      value = super.processColumn(rs, index, propType);
//    }
    return value;
  }

}
