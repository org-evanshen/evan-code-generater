package org.evanframework.toolbox.ormcreator.database;

import org.apache.commons.lang3.StringUtils;
import org.evanframework.toolbox.ormcreator.datatypeconvertor.DataTypeConvertor;
import org.evanframework.toolbox.ormcreator.model.Column;
import org.evanframework.toolbox.ormcreator.model.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.model.Table;
import org.evanframework.toolbox.ormcreator.utils.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oracle extends AbstractDatabase implements Database {
	private static final Logger logger = LoggerFactory.getLogger(Oracle.class);

	public Oracle(OrmCreatorParam param) {
		super(param);
	}

	@Override
	protected String getJdbcDriver() {
		return "oracle.jdbc.driver.OracleDriver";
	}

	@Override
	protected String getTablesSql() {
		return "select a.table_name,b.comments,b.table_type from user_tables a,user_tab_comments b where a.table_name=b.table_name ";
	}

	@Override
	protected PreparedStatement buildGetColumnsPreparedStatement(String tableName) throws SQLException {
		String sql = "select a.COLUMN_NAME,a.DATA_TYPE,a.DATA_PRECISION,a.DATA_LENGTH,DATA_SCALE,b.constraint_name,c.COMMENTS from user_tab_columns a "
				+ " left join (select * from  user_cons_columns t where  t.constraint_name=(select constraint_name from user_constraints where table_name =? and constraint_type ='P')) b on a.COLUMN_NAME=b.column_name "
				+ " left join user_col_comments c on a.TABLE_NAME=c.TABLE_NAME and a.COLUMN_NAME=c.COLUMN_NAME "
				+ " where a.TABLE_NAME =?";

		PreparedStatement ps = getCn().prepareStatement(sql);
		ps.setString(1, tableName);
		ps.setString(2, tableName);

		return ps;
	}

	@Override
	public Column resultSetToColumn(ResultSet rs) throws SQLException {
		// 列表，java数据类型，java名，java名首字母大写
		String columnName, javaDataType, javaFieldName, javaFieldNameFirstCharUpcase, constraint_name;

		Column column = new Column();

		DataTypeConvertor dataTypeConvertor = param.getDataTypeConvertor();

		// 判断是否主键
		constraint_name = rs.getString("CONSTRAINT_NAME");
		boolean isPk = StringUtils.isNotBlank(constraint_name);
		
		column.setIsPk(isPk ? 1 : 0);

		column.setColumnName(rs.getString("COLUMN_NAME"));// 字段名，		
		column.setComment(rs.getString("COMMENTS"));// 注释		
		Integer dataLength = rs.getInt("DATA_LENGTH");

		Integer dataPrecision = JdbcUtils.getResultSetInteger(rs, "DATA_PRECISION");

		column.setLength(dataPrecision == null ? dataLength : dataPrecision);
		column.setDbDataType(rs.getString("DATA_TYPE"));

		// 将数据库数据类型转成成java数据类型
		javaDataType = dataTypeConvertor.convertDBDataTypeToJavaDataType(column.getDbDataType(),
				column.getLength(), rs.getString("DATA_SCALE"), isPk);
		column.setDataType(javaDataType);
		column.setJdbcDataType(dataTypeConvertor.convertDBDataTypeToJdbcType(column.getDbDataType(),
				column.getLength(), rs.getString("DATA_SCALE")));

		return column;
	}

	@Override
	public Table resultSetToTable(ResultSet rs) throws SQLException {
		
		Table table = new Table();

		table.setTableName(rs.getString("table_name"));
		table.setTableComments(rs.getString("comments"));

		return table;
	}
}
