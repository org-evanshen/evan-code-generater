package org.evanframework.toolbox.ormcreator;

import org.apache.commons.lang3.StringUtils;
import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class OrmGeneratorMain {

    //属性文件的路径
    private static final String CONFIG_FILE = "orm-generator.properties";

    private static Properties props;

    public static void main(String[] args) throws IOException {
        props = new Properties();

        String configPath = System.getProperty("user.dir") + "/" + CONFIG_FILE;
        //URL configFile = ClassLoader.getSystemResource(CONFIG_FILE);

        InputStream in = new BufferedInputStream(new FileInputStream(configPath));
        props.load(in);

        OrmCreatorParam param = new OrmCreatorParam();

        param.setDatabaseType(OrmCreatorParam.DatabaseType.valueOf(props.getProperty("db.type")));
        param.setOrmType(OrmCreatorParam.OrmType.valueOf(props.getProperty("orm.type")));

        param.setJdbcUrl(props.getProperty("jdbc.url"));
        param.setJdbcUser(props.getProperty("jdbc.username"));
        param.setJdbcPassword(props.getProperty("jdbc.password"));
        param.setDatabaseSchema(props.getProperty("jdbc.schema"));

        param.setOutDir(props.getProperty("target.dir"));
        param.setTemplateDir(props.getProperty("template.dir"));

        param.setPackageNamePo(props.getProperty("package.name.entity"));// 数据表实体类包名
        param.setPackageNameQuery(props.getProperty("package.name.query"));
        param.setPackageNameDao(props.getProperty("package.name.dao"));// DAO包名
        param.setPackageNameMapper(props.getProperty("package.name.mapper"));// Mybatis Mapper包名
        param.setPackageNameDto(props.getProperty("package.name.dto"));// dto包名
        param.setPackageNameList(props.getProperty("package.name.list"));// list包名

        String tmp = props.getProperty("column.name.createtime");
        if (StringUtils.isNotBlank(tmp)) {
            param.setColumnCreateTime(tmp);
        }
        tmp = props.getProperty("column.name.updatetime");
        if (StringUtils.isNotBlank(tmp)) {
            param.setColumnUpdateTime(tmp);
        }

        String tablesStr = props.getProperty("generate.tables");

        if (StringUtils.isNotBlank(tablesStr)) {
            String[] tableArray = StringUtils.split(tablesStr, ",");
            List<String> tableList = Arrays.asList(tableArray);
            param.setTables(tableList);
        }

        OrmCreator ormCreator = new OrmCreator();
        ormCreator.create(param);
    }


}
