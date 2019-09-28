package org.evanframework.toolbox.ormcreator;

import org.apache.commons.lang3.StringUtils;
import org.evanframework.toolbox.ormcreator.model.OrmCreatorParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class OrmGeneratorMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrmGeneratorMain.class);

    //属性文件的路径
    private static final String CONFIG_FILE = "orm-generator.properties";

    public static void main(String[] args) throws IOException {
        String workDir = System.getProperty("user.dir");

        String configPath = null;
        if (args.length > 0) {
            configPath = args[0];
        } else {
            configPath = workDir + "/" + CONFIG_FILE;
        }

        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>Start generate>>>>>>>>>>>>>>>>>>>>>>>");
        LOGGER.info("Config file: {}", configPath);

        Properties props = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(configPath));
        props.load(in);

        OrmCreatorParam param = new OrmCreatorParam();

        if (args.length > 1) {
            param.setTemplateDir(args[1]);
        } else {
            param.setTemplateDir(workDir + "/" + props.getProperty("template.dir"));
        }
        if (args.length > 2) {
            param.setOutDir(args[2]);
        } else {
            param.setOutDir(workDir + "/" + props.getProperty("target.dir"));
        }

        param.setDatabaseType(OrmCreatorParam.DatabaseType.valueOf(props.getProperty("db.type")));
        param.setOrmType(OrmCreatorParam.OrmType.valueOf(props.getProperty("orm.type")));

        param.setJdbcUrl(props.getProperty("jdbc.url"));
        param.setJdbcUser(props.getProperty("jdbc.username"));
        param.setJdbcPassword(props.getProperty("jdbc.password"));
        param.setDatabaseSchema(props.getProperty("jdbc.schema"));

        param.setPackageNameRoot(props.getProperty("package.name.root"));

        String tablesStr = props.getProperty("generate.tables");

        if (StringUtils.isNotBlank(tablesStr)) {
            String[] tableArray = StringUtils.split(tablesStr, ",");
            List<String> tableList = Arrays.asList(tableArray);
            param.setTables(tableList);
        }

        String tmp = props.getProperty("column.name.createtime");
        if (StringUtils.isNotBlank(tmp)) {
            param.setColumnCreateTime(tmp);
        }
        tmp = props.getProperty("column.name.updatetime");
        if (StringUtils.isNotBlank(tmp)) {
            param.setColumnUpdateTime(tmp);
        }

        LOGGER.info("{}", param);
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        OrmCreator ormCreator = new OrmCreator();
        ormCreator.create(param);
    }
}

//        param.setPackageNamePo(props.getProperty("package.name.entity"));// 数据表实体类包名
//        param.setPackageNameQuery(props.getProperty("package.name.query"));
//        param.setPackageNameDao(props.getProperty("package.name.dao"));// DAO包名
//        param.setPackageNameMapper(props.getProperty("package.name.mapper"));// Mybatis Mapper包名
//        param.setPackageNameResponse(props.getProperty("package.name.response"));// dto包名
//        param.setPackageNameList(props.getProperty("package.name.list"));// list包名
//        param.setPackageNameManager(props.getProperty("package.name.manager"));

