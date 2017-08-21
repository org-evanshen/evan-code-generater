package org.evanframework.toolbox.test;

import org.evanframework.toolbox.ormcreator.OrmCreator;
import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam.DatabaseType;
import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam.OrmType;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据层代码生成器
 * <p>
 * 
 * @author <a href="mailto:shenw@hundsun.com">Evan.Shen</a>
 * @version 2011-2-27 下午08:12:23
 */
public class OrmCreatorExecutor {
	public void execute() throws Exception {
		OrmCreatorParam param = new OrmCreatorParam();

		param.setOrmType(OrmType.mybatis);

		// param.setOrmType(OrmType.ibatis);
		// param.setBaseDaoName("IbatisDao");

		param.setDatabaseType(DatabaseType.mysql);

//		param.setJdbcUrl("jdbc:mysql://192.168.0.150:3306/information_schema?useSSL=false");
//		param.setJdbcUser("root");
//		param.setJdbcPassword("MiZhi001");

		param.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/information_schema?useSSL=false");
		param.setJdbcUser("root");
		param.setJdbcPassword("Shen0001");

		param.setDatabaseSchema("btbs_user");

		// param.setDatabaseType(DatabaseType.oracle);
		// param.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		// param.setJdbcUser("evan_base");
		// param.setJdbcPassword("evan_base");

		param.setPackageNameDao("com.mizhi.btbs.userservice.domain.dao");// DAO包名
		param.setPackageNameMapper("com.mizhi.btbs.userservice.domain.mapper");// DAO包名
		param.setPackageNamePo("com.mizhi.btbs.userservice.domain.domain.model");// Po包名
		param.setPackageNameQuery("com.mizhi.btbs.userservice.domain.domain.query");
		param.setPackageNameDto("com.mizhi.btbs.userservice.domain.domain.dto");// dto包名
		param.setPackageNameList("com.mizhi.btbs.userservice.domain.domain.list");// list包名
		// param.setPrefixRemove("ztzq");
		param.setOutDir("orm-create-tool/target/ormoutput");

		param.setTemplateDir("orm-create-tool/build/template");

		// 生成的表 可选 不提供该参数则生成全部表
//		List<String> tables = new ArrayList<String>();
//		tables.add("pub_picture");
//		tables.add("pub_attach");
//		param.setTables(tables);
		OrmCreator ormCreator = new OrmCreator();
		ormCreator.create(param);
	}

	public static void main(String[] args) throws Exception {
		OrmCreatorExecutor ormCreatorExecutor = new OrmCreatorExecutor();
		ormCreatorExecutor.execute();
	}
}
