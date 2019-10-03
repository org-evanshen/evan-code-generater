package org.evan.libraries.codegenerate.model;

public class Column {
	private String columnName;//列名 比如ID，USER_NAME
	private String fieldName;//属性名 比如id,userName
	private int isPk = 0;//是否主键
	private String dataType;//数据类型
	private String jdbcDataType;//
	private String comment;//注释
	private String fieldNameFirstCharUpcase;//属性名(首字母大写) 比如Id,UserName
	private Integer length;
	private String dbDataType;//数据库数据类型

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getIsPk() {
		return isPk;
	}

	public void setIsPk(int isPk) {
		this.isPk = isPk;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFieldNameFirstCharUpcase() {
		return fieldNameFirstCharUpcase;
	}

	public void setFieldNameFirstCharUpcase(String fieldNameFirstCharUpcase) {
		this.fieldNameFirstCharUpcase = fieldNameFirstCharUpcase;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJdbcDataType() {
		return jdbcDataType;
	}

	public void setJdbcDataType(String jdbcDataType) {
		this.jdbcDataType = jdbcDataType;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getDbDataType() {
		return dbDataType;
	}

	public void setDbDataType(String dbDataType) {
		this.dbDataType = dbDataType;
	}
}
