package org.evan.libraries.codegenerate.database;

import org.evan.libraries.codegenerate.model.Column;
import org.evan.libraries.codegenerate.model.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Database {

	public void openConn();

	public void closeConn();

	/**
	 * 获取要生成orm代码的表
	 * <p>
	 * author: <a href="mailto:shenw@hundsun.com">Evan.Shen</a><br>
	 * version: 2012-1-13 下午2:46:52 <br>
	 * 
	 * @param tables
	 * @return
	 */
	public ResultSet getTables(List<String> tables);

	public Table resultSetToTable(ResultSet rs) throws SQLException;

	/**
	 * 获取一个表的所有列
	 * <p>
	 * author: <a href="mailto:shenw@hundsun.com">Evan.Shen</a><br>
	 * version: 2012-1-13 下午2:47:24 <br>
	 * 
	 * @param tableName
	 * @return
	 */
	public ResultSet getColumns(String tableName);

	public Column resultSetToColumn(ResultSet rs) throws SQLException;

	//public String getStringFromResultSet(ResultSet rs);
}
