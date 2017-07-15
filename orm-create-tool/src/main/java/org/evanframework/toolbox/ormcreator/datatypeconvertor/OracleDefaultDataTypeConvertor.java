package org.evanframework.toolbox.ormcreator.datatypeconvertor;

public class OracleDefaultDataTypeConvertor implements DataTypeConvertor {
	public String convertDBDataTypeToJavaDataType(String dbDataType, Integer length, String dbDataScale, boolean isPk) {
		String javaDataType = null;

		dbDataType = dbDataType.toLowerCase();

		int dbDataTypelength = dbDataType.length();
		if ((dbDataTypelength >= 4 && "date".equals(dbDataType.substring(0, 4))//
		|| (dbDataTypelength >= 9 && "timestamp".equals(dbDataType.substring(0, 9))))) {
			javaDataType = "Date";
		} else if ("number".equals(dbDataType)) {
			if ("0".equals(dbDataScale) && (length != null)) {
				if (length.intValue() >= 9) {
					javaDataType = "Long";
				} else {
					javaDataType = "Integer";
				}
			} else {
				javaDataType = "BigDecimal";
			}
		} else {
			javaDataType = "String";
		}

		return javaDataType;
	}

	@Override
	public String convertDBDataTypeToJdbcType(String dbDataType, Integer length, String dbDataScale) {
		String javaDataType = null;

		dbDataType = dbDataType.toLowerCase();

		int dbDataTypelength = dbDataType.length();

		if ((dbDataTypelength >= 4 && "date".equals(dbDataType.substring(0, 4)) || (dbDataTypelength >= 9 && "timestamp"
				.equals(dbDataType.substring(0, 9))))) {
			javaDataType = "TIMESTAMP";
		} else if ("number".equals(dbDataType)) {
			if ("0".equals(dbDataScale) && (length != null)) {
				if (length.intValue() >= 8) {
					javaDataType = "NUMERIC";
				} else {
					javaDataType = "INTEGER";
				}
			} else {
				javaDataType = "NUMERIC";
			}
			//javaDataType = "DECIMAL";
		} else {
			javaDataType = "VARCHAR";
		}

		return javaDataType;
	}
}
