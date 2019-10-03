package org.evan.libraries.codegenerate.database;

import org.apache.commons.lang3.StringUtils;
import org.evan.libraries.codegenerate.model.OrmCreatorParam;
import org.evan.libraries.codegenerate.utils.JdbcUtils;
import org.evan.libraries.codegenerate.datatypeconvertor.DataTypeConvertor;
import org.evan.libraries.codegenerate.model.Column;
import org.evan.libraries.codegenerate.model.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql extends AbstractDatabase implements Database {

	private String schemaName;

	public Mysql(OrmCreatorParam param) {
		super(param);
		// String jdbcUrl = param.getJdbcUrl();
		// int index = StringUtils.lastIndexOf(jdbcUrl, "/");
		schemaName = param.getDatabaseSchema();
	}

	@Override
	protected String getJdbcDriver() {
		return "com.mysql.cj.jdbc.Driver";
	}

	@Override
	protected String getTablesSql() {
		return "select a.TABLE_NAME,a.TABLE_COMMENT from TABLES a where a.TABLE_SCHEMA='" + schemaName + "' ";
	}

	@Override
	public Table resultSetToTable(ResultSet rs) throws SQLException {
		Table table = new Table();

		table.setTableName(rs.getString("table_name"));
		table.setTableComments(rs.getString("TABLE_COMMENT"));

		return table;
	}

	@Override
	protected PreparedStatement buildGetColumnsPreparedStatement(String tableName) throws SQLException {
		String sql = " select a.COLUMN_NAME,a.COLUMN_TYPE,a.DATA_TYPE,a.COLUMN_COMMENT,a.NUMERIC_PRECISION,a.NUMERIC_SCALE,a.COLUMN_KEY,a.CHARACTER_MAXIMUM_LENGTH from COLUMNS a where a.TABLE_NAME=? and a.TABLE_SCHEMA=?";

		PreparedStatement ps = getCn().prepareStatement(sql);
		ps.setString(1, tableName);
		ps.setString(2, this.schemaName);

		return ps;
	}

	@Override
	public Column resultSetToColumn(ResultSet rs) throws SQLException {
		// 列表，java数据类型，java名，java名首字母大写
		String columnName, javaDataType, javaFieldName, javaFieldNameFirstCharUpcase, columnKey;

		Column column = new Column();	

		// 判断是否主键
		columnKey = rs.getString("COLUMN_KEY");
		boolean isPk = StringUtils.equalsIgnoreCase(columnKey, "PRI");

		column.setIsPk(isPk ? 1 : 0);

		column.setColumnName(rs.getString("COLUMN_NAME"));// 字段名
		column.setComment(rs.getString("COLUMN_COMMENT"));// 注释
		column.setDbDataType(rs.getString("DATA_TYPE"));// 数据类型

		if (!StringUtils.equalsIgnoreCase(column.getDbDataType(), "longtext")) {
			Integer characterMaximumLength = rs.getInt("CHARACTER_MAXIMUM_LENGTH");
			Integer numericPrecision = JdbcUtils.getResultSetInteger(rs, "NUMERIC_PRECISION");

			column.setLength(numericPrecision == null ? characterMaximumLength : numericPrecision);
		}

		DataTypeConvertor dataTypeConvertor = param.getDataTypeConvertor();
		if (dataTypeConvertor != null) {
			// 将数据库数据类型转成成java数据类型
			javaDataType = dataTypeConvertor.convertDBDataTypeToJavaDataType(column.getDbDataType(),
					column.getLength(), rs.getString("NUMERIC_SCALE"), isPk);
			column.setDataType(javaDataType);
			column.setJdbcDataType(dataTypeConvertor.convertDBDataTypeToJdbcType(column.getDbDataType(),
					column.getLength(), rs.getString("NUMERIC_SCALE")));
		}

		return column;
	}

}
