package org.evan.libraries.codegenerate.database;

import org.evan.libraries.codegenerate.model.OrmCreatorParam;

public class DatabaseFactory {
	/**
	 * 获取数据库访问接口
	 * <p>
	 * author: <a href="mailto:shenw@hundsun.com">Evan.Shen</a><br>
	 * version: 2012-1-13 下午2:48:08 <br>
	 * 
	 * @param database
	 * @return
	 */
	public static Database getDatabase(OrmCreatorParam.DatabaseType database, OrmCreatorParam params) {
		if (OrmCreatorParam.DatabaseType.oracle.equals(database)) {
			return new Oracle(params);
		} else if (OrmCreatorParam.DatabaseType.mysql.equals(database)) {
			return new Mysql(params);
		} else {
			throw new IllegalStateException("databaseType is null");
		}
	}
}
