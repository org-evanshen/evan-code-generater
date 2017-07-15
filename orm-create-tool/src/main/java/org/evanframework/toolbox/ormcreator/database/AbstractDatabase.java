package org.evanframework.toolbox.ormcreator.database;

import org.evanframework.toolbox.ormcreator.OrmCreator;
import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public abstract class AbstractDatabase implements Database {
	private static final Logger logger = LoggerFactory.getLogger(OrmCreator.class);

	private Connection cn;
	private PreparedStatement ps = null;
	protected OrmCreatorParam param;

	public AbstractDatabase(OrmCreatorParam param) {
		this.param = param;
	}

	public void openConn() {
		String jdbcDriver = getJdbcDriver();
		String jdbcUrl;

		// jdbcUrl = JdbcDriverAndUrlHead[1] + param.getJdbcUrl();
		jdbcUrl = param.getJdbcUrl();

		try {
			Class.forName(jdbcDriver);
			cn = DriverManager.getConnection(jdbcUrl, param.getJdbcUser(), param.getJdbcPassword());
			cn.setAutoCommit(false);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			throw new IllegalStateException(e);
		}
	}

	public void closeConn() {
		try {
			if (ps != null) {
				ps.close();
			}
			cn.close();
		} catch (SQLException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			throw new IllegalStateException(e);
		}
	}

	public ResultSet getTables(List<String> tables) {
		try {
			ps = getCn().prepareStatement(getTablesSql(tables));
			return ps.executeQuery();
		} catch (SQLException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			closeConn();
			throw new IllegalStateException(e);
		}
	}

	public ResultSet getColumns(String tableName) {
		try {
			ps = buildGetColumnsPreparedStatement(tableName);
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			closeConn();
			throw new IllegalStateException(e);
		}
	}

	/**
	 * return[0]=JdbcDriver,return[1]=JdbcUrlHead
	 * <p>
	 * author: <a href="mailto:shenw@hundsun.com">Evan.Shen</a><br>
	 * version: 2012-1-14 上午5:29:07 <br>
	 * 
	 * @return
	 */
	protected abstract String getJdbcDriver();

	/**
	 * 获取table的sql
	 * <p>
	 * author: <a href="mailto:shenw@hundsun.com">Evan.Shen</a><br>
	 * version: 2012-1-14 下午6:03:38 <br>
	 * 
	 * @param tables
	 * @return
	 */
	protected abstract String getTablesSql(List<String> tables);

	/**
	 * 
	 * 
	 * @param tableName
	 *            <p>
	 * @author: <a href="mailto:shenw@hundsun.com">shenw</a><br>
	 */
	protected abstract PreparedStatement buildGetColumnsPreparedStatement(String tableName) throws SQLException;

	protected Connection getCn() {
		return cn;
	}
}
