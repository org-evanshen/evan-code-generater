package test.org.evan.libraries.codegenerate;

import org.evan.libraries.codegenerate.OrmCreator;
import org.evan.libraries.codegenerate.model.OrmCreatorParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据层代码生成器
 * <p>
 *
 * @author <a href="mailto:shenw@hundsun.com">Evan.Shen</a>
 * @version 2011-2-27 下午08:12:23
 */
public class OrmCreatorTest {
    public static void main(String[] args) {
        OrmCreatorTest ormCreatorTest = new OrmCreatorTest();
        ormCreatorTest.execute();
    }

    public void execute() {
        OrmCreatorParam param = new OrmCreatorParam();

//        param.setOrmType(OrmType.mybatis);
//        param.setDatabaseType(DatabaseType.mysql);

        param.setJdbcUrl("jdbc:mysql://121.40.245.248:3306/information_schema?useSSL=false");
        param.setJdbcUser("shumai");
        param.setJdbcPassword("ShuMai@001");
//		param.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/information_schema?useSSL=false");
//		param.setJdbcUser("root");
//		param.setJdbcPassword("Shen0001");
        param.setDatabaseSchema("crawler");

        param.setPackageNameRoot("org.evan.springcloud.base");

        //param.setOutDir("orm-create-tool/ormoutput");
        //param.setTemplateDir("orm-create-tool/build/template");

        // 生成的表 可选 不提供该参数则生成全部表
        List<String> tables = new ArrayList<String>();
        tables.add("demo");
//        tables.add("demo_child1");
//        tables.add("pub_attach");
//        tables.add("pub_picture");
        param.setTables(tables);

        OrmCreator ormCreator = new OrmCreator();
        ormCreator.create(param);
    }
}
