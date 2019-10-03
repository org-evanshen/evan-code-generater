package org.evan.libraries.codegenerate.datatypeconvertor;

import java.util.HashMap;
import java.util.Map;

public class MysqlDefaultDataTypeConvertor implements DataTypeConvertor {

	private final Map<String, String> map = new HashMap<String, String>(24);

	public MysqlDefaultDataTypeConvertor() {
		map.put("tinyint", "Integer");
		//		map.put(key, value);
		//		map.put(key, value);
		//		map.put(key, value);
		//		map.put(key, value);
		//		map.put(key, value);
		//		map.put(key, value);
		//		map.put(key, value);
		//		map.put(key, value);
		//		map.put(key, value);
		//		map.put(key, value);
		//		map.put(key, value);
		//		map.put(key, value);
	}

	@Override
	public String convertDBDataTypeToJavaDataType(String dbDataType, Integer length, String dbDataScale, boolean isPk) {
		String javaDataType = null;

		dbDataType = dbDataType.toLowerCase();

		//int dbDataTypelength = dbDataType.length();
		if ("datetime".equals(dbDataType) //
				|| "timestamp".equals(dbDataType) //
				|| "date".equals(dbDataType)//
				|| "time".equals(dbDataType)) {
			javaDataType = "Date";
		} else if ("bigint".equals(dbDataType)) {
			javaDataType = "Long";
		} else if (dbDataType.indexOf("int") != -1) {
			javaDataType = "Integer";
		} else if ("double".equals(dbDataType) //
				|| "decimal".equals(dbDataType) //
				|| "numeric".equals(dbDataType)//
				|| "float".equals(dbDataType)) {
			javaDataType = "BigDecimal";
		} else {
			javaDataType = "String";
		}

		return javaDataType;
	}

	@Override
	public String convertDBDataTypeToJdbcType(String dbDataType, Integer length, String dbDataScale) {
		String jdbcDataType = null;

		dbDataType = dbDataType.toLowerCase();

		//int dbDataTypelength = dbDataType.length();
		if ("datetime".equals(dbDataType) //
				|| "timestamp".equals(dbDataType) //
				|| "date".equals(dbDataType)//
				|| "time".equals(dbDataType)) {
			jdbcDataType = "TIMESTAMP";
		} else if ("bigint".equals(dbDataType)) {
			jdbcDataType = "BIGINT";
		} else if (dbDataType.indexOf("int") != -1) {
			jdbcDataType = "INTEGER";
		} else if ("double".equals(dbDataType) //
				|| "decimal".equals(dbDataType) //
				|| "numeric".equals(dbDataType)//
				|| "float".equals(dbDataType)) {
			jdbcDataType = "NUMERIC";
		} else {
			jdbcDataType = "VARCHAR";
		}

		return jdbcDataType;
	}

}
