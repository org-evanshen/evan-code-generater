package org.evan.libraries.codegenerate.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JdbcUtils
 * <p>
 * 
 * @author <a href="mailto:shenw@hundsun.com">Evan.Shen</a>
 * @version 2013-5-4 上午1:29:09
 */

public class JdbcUtils {

	public static Integer getResultSetInteger(ResultSet rs, String columnLabel) throws SQLException {
		Integer returnV = null;
		Object o = rs.getObject(columnLabel);
		if (o != null) {
			returnV = Integer.valueOf(o.toString());
		}
		return returnV;
	}

	public static Long getResultSetLong(ResultSet rs, String columnLabel) throws SQLException {
		Long returnV = null;
		Object o = rs.getObject(columnLabel);
		if (o != null) {
			returnV = Long.valueOf(o.toString());
		}
		return returnV;
	}

	public static BigDecimal getResultSetBigDecimal(ResultSet rs, String columnLabel) throws SQLException {
		BigDecimal returnV = null;
		Object o = rs.getObject(columnLabel);
		if (o != null) {
			returnV = new BigDecimal(o.toString());
		}
		return returnV;
	}

	public static Double getResultSetDouble(ResultSet rs, String columnLabel) throws SQLException {
		Double returnV = null;
		Object o = rs.getObject(columnLabel);
		if (o != null) {
			returnV = Double.valueOf(o.toString());
		}
		return returnV;
	}

	public static String getSqlColumns(String dynamicColumn, String defaultColumns) {
		return StringUtils.isNotBlank(dynamicColumn) ? dynamicColumn : defaultColumns;
	}

	/**
	 * 根据指定的列构建SELECT语句 <br>
	 * 如columns=["ID","USER_NAME"],则sql="select ID,USER FROM ..." 如columns=null或空,则sql="select "+defaultColumns+" ..."
	 * <p>
	 * author: <a href="mailto:shenw@hundsun.com">Evan.Shen</a><br>
	 * version: 2013-2-27 上午10:49:45 <br>
	 * 
	 * @param dynamicColumn
	 */
	public static String getSqlColumns(String[] dynamicColumn, String defaultColumns) {
		StringBuilder sqlColumns = new StringBuilder(1024);
		if (dynamicColumn == null || dynamicColumn.length == 0) {
			sqlColumns.append(defaultColumns);
		} else {
			int i = 0;
			for (String column : dynamicColumn) {
				if (i > 0) {
					sqlColumns.append(",");
				}
				sqlColumns.append(column);
				i++;
			}
		}
		return sqlColumns.toString();
	}

}
