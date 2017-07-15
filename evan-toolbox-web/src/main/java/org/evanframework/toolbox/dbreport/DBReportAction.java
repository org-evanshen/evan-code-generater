package org.evanframework.toolbox.dbreport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.evanframework.toolbox.ormcreator.database.Database;
import org.evanframework.toolbox.ormcreator.database.DatabaseFactory;
import org.evanframework.toolbox.ormcreator.domain.Column;
import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.domain.Table;
import org.evanframework.toolbox.utils.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 
 * <p>
 * create at 2014年7月21日 上午10:00:18
 * 
 * @author <a href="mailto:shenwei@ancun.com">ShenWei</a>
 * @version %I%, %G%
 * @see
 */
@Controller
@RequestMapping("/toolbox")
public class DBReportAction {
	private static Logger logger = LoggerFactory.getLogger(DBReportAction.class);
	private String jdbcDriverClassName = "oracle.jdbc.driver.OracleDriver";
	private PreparedStatement ps;
	private Connection cn;

	@RequestMapping(value = "/db-report", method = RequestMethod.GET)
	public ModelAndView report(HttpServletRequest request) throws Exception {
		OrmCreatorParam param = (OrmCreatorParam) CookieUtil.read(OrmCreatorParam.class, "dbReportAction", request,
				"UTF-8");
		ModelAndView mv = new ModelAndView("/toolbox/dbReport/dbReport");
		mv.addObject("param", param);
		return mv;
	}

	@RequestMapping(value = "/db-report", method = RequestMethod.POST)
	public String reportSubmit(Model model, @ModelAttribute() OrmCreatorParam param, HttpServletRequest request,
			HttpServletResponse response) {
		CookieUtil.save("dbReportAction", param, -1, response, "UTF-8");
		Database database = DatabaseFactory.getDatabase(param.getDatabaseType(), param);
		database.openConn();
		
		ResultSet rs = database.getTables(param.getTables());

		List<Table> tables = new ArrayList<Table>();
		Table table = null;
		Column column;
		List<Column> columns = null;// 列
		try {			
			while (rs.next()) {// 遍历表
				table = database.resultSetToTable(rs);
				columns = new ArrayList<Column>();
				ResultSet rs1 = database.getColumns(table.getTableName());
				while (rs1.next()) {
					column = database.resultSetToColumn(rs1);
					columns.add(column);
				}
				table.setColumns(columns);
				tables.add(table);
			}
			rs.close();
		} catch (Exception ex) {
			throw new RuntimeException("读取数据库出错", ex);
		} finally {
			database.openConn();
		}

		model.addAttribute("tables", tables);

		return "/toolbox/dbReport/dbReportTables";
	}
	// private List<Map> getTablesAndColumns(String jdbcUrl, String userName,
	// String password) throws SQLException {
	// userName = userName.toUpperCase();
	// openDataBase(jdbcUrl, userName, password);
	//
	// String sql = "select a.table_name, b.comments " +
	// "from user_tables a, user_tab_comments b "
	// + "where a.table_name = b.table_name " + "order by a.table_name";
	//
	// ps = cn.prepareStatement(sql);
	// ResultSet rs = ps.executeQuery();
	//
	// List<Map> tables = new ArrayList<Map>();
	// Map tableMap = null;
	// while (rs.next()) {
	// tableMap = new HashMap();
	// tableMap.put("tableName", rs.getString("table_name"));
	// tableMap.put("comments", rs.getString("comments"));
	// tableMap.put("cols", getCols(rs.getString("table_name")));
	// tables.add(tableMap);
	// }
	//
	// closeDataBase();
	//
	// return tables;
	// }
	//
	// private List<Map> getCols(String tableName) throws SQLException {
	// List<Map> cols = new ArrayList<Map>();
	//
	// String sql =
	// "select a.COLUMN_NAME, a.DATA_TYPE,a.DATA_LENGTH , b.constraint_name, c.COMMENTS from user_tab_columns a"
	// +
	// " left join (select * from user_cons_columns t where t.constraint_name ="
	// +
	// " (select constraint_name from user_constraints where table_name = ? and constraint_type = 'P')) b "
	// + " on a.COLUMN_NAME = b.column_name"
	// +
	// " left join user_col_comments c on a.TABLE_NAME = c.TABLE_NAME and a.COLUMN_NAME = c.COLUMN_NAME"
	// + " where a.TABLE_NAME = ?";
	//
	// ps = cn.prepareStatement(sql);
	// ps.setString(1, tableName);
	// ps.setString(2, tableName);
	//
	// ResultSet rs = ps.executeQuery();
	// Map colMap = null;
	//
	// String constraint_name = null;
	// while (rs.next()) {
	// colMap = new HashMap();
	// colMap.put("colName", rs.getString("COLUMN_NAME"));
	// colMap.put("dataType", rs.getString("DATA_TYPE"));
	// colMap.put("length", rs.getString("DATA_LENGTH"));
	// colMap.put("comments", rs.getString("COMMENTS"));
	// constraint_name = rs.getString("constraint_name");
	// if (constraint_name != null && !"".equals(constraint_name)) {
	// colMap.put("isPk", "Y");
	// } else {
	// colMap.put("isPk", "");
	// }
	// cols.add(colMap);
	// }
	//
	// return cols;
	// }
}
