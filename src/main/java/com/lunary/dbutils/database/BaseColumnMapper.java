package com.lunary.dbutils.database;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lunary.database.ColumnMapper;
import com.lunary.database.exception.DataMappingException;

public class BaseColumnMapper implements ColumnMapper {

  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public Object toObject(ResultSet rs, int index, Class<?> propType) throws SQLException {
    
    Object value = null;
    if (propType.equals(String.class)) {

      value = rs.getString(index);
    }
    else if (propType.equals(BigDecimal.class)) {
      value = rs.getBigDecimal(index);
    }
    else if (propType.equals(java.sql.Date.class)) {
      value = rs.getDate(index);
    }
    else if (Enum.class.isAssignableFrom(propType)) {

      String val = rs.getString(index);
      try {

        if (val != null) {
          value = Enum.valueOf((Class<? extends Enum>) propType, val);
        }
      }
      catch (IllegalArgumentException e) {
        throw new DataMappingException("Error converting to Enum [" + propType.getCanonicalName() + "] for column index " + index + " of value [" + val + "] : " + e.getMessage(), e);
      }
    }
    else {
      value = UNKNOWN;
    }
    return value;
  }

}
