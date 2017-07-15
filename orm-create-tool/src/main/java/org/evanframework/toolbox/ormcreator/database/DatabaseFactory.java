package org.evanframework.toolbox.ormcreator.database;

import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam.DatabaseType;

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
	public static Database getDatabase(DatabaseType database, OrmCreatorParam params) {
		if (DatabaseType.oracle.equals(database)) {
			return new Oracle(params);
		} else if (DatabaseType.mysql.equals(database)) {
			return new Mysql(params);
		} else {
			throw new IllegalStateException("databaseTyoe is null");
		}
	}
}
