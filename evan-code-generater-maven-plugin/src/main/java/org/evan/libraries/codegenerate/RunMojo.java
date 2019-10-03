package org.evan.libraries.codegenerate;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.evan.libraries.codegenerate.model.OrmCreatorParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Evan.Shen
 * @since 2019-10-03
 *
 */
@Mojo(name = "run", defaultPhase = LifecyclePhase.PACKAGE)
public class RunMojo extends AbstractMojo {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunMojo.class);

    @Parameter
    private String ormType;

    @Parameter
    private String databaseType;

    @Parameter
    private String jdbcUrl;

    @Parameter
    private String jdbcUser;

    @Parameter
    private String jdbcPassword;

    @Parameter
    private String databaseSchema;

    @Parameter
    private String packageNameRoot;

    @Parameter
    private String basePoName = "BaseDomain";

    @Parameter
    private String baseQueryClass = "AbstractQueryParam";

    @Parameter
    private String queryInterface = "QueryParam";

    @Parameter
    private String outDir = "evan-codegenerate-output";

    private String templateDir = "template";
    private String templatePackage = "ormTemplate";

    @Parameter
    private String deletedTagColumn = "is_deleted";

    @Parameter
    private String statusColumn = "status";

    @Parameter
    private String columnCreateTime = "gmt_create";

    @Parameter
    private String columnUpdateTime = "gmt_modify";

    @Parameter
    private List<String> tables;

    @Parameter
    private String prefixRemove;

    @Override
    public void execute() {
        OrmCreatorParam param = new OrmCreatorParam();

        if (databaseType != null && !databaseType.isEmpty()) {
            param.setDatabaseType(OrmCreatorParam.DatabaseType.valueOf(databaseType));
        }
        if (ormType != null && !ormType.isEmpty()) {
            param.setOrmType(OrmCreatorParam.OrmType.valueOf(ormType));
        }

        param.setJdbcUrl(jdbcUrl);
        param.setJdbcUser(jdbcUser);
        param.setJdbcPassword(jdbcPassword);
        param.setDatabaseSchema(databaseSchema);

        param.setPackageNameRoot(packageNameRoot);

        param.setTables(tables);

        param.setColumnCreateTime(columnCreateTime);
        param.setColumnUpdateTime(columnUpdateTime);

        OrmCreator ormCreator = new OrmCreator();

        ormCreator.create(param);
    }
}
