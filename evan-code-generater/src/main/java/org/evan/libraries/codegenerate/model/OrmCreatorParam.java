package org.evan.libraries.codegenerate.model;

import org.evan.libraries.codegenerate.datatypeconvertor.DataTypeConvertor;
import org.evan.libraries.codegenerate.utils.OrmCreatorUtil;

import java.util.List;

/**
 * Orm生成参数
 *
 * @author <a href="mailto:shenw@hundsun.com">Evan.Shen</a>
 * @version Date: 2010-9-11 下午02:49:44
 */
public class OrmCreatorParam {
    private OrmType ormType = OrmType.mybatis;

    private DatabaseType databaseType = DatabaseType.mysql;
    private String databaseTypeClassName;
    private String jdbcDriverClassName;
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private String databaseSchema;

    private String packageNameRoot;
    private String packageNameMapper;
    private String packageNamePo;
    private String packageNameDao;
    private String packageNameQuery;
    private String packageNameList;
    private String packageNameDto;
    private String packageNameResponse;
    private String packageNameManager;

    private String basePoName = "BaseDomain";
    //private String baseQueryClass = "AbstractQuery";
    //private String queryInterface = "Query";
    private String baseQueryClass = "AbstractQueryParam";
    private String queryInterface = "QueryParam";
    private String baseDaoName;
    private String baseMapperName = "BaseMapper";

    private String outDir = "code-generater-output";
    private String templateDir = "template";
    private String templatePackage = "ormTemplate";

    private String deletedTagValueYes = "1";
    private String deletedTagValueNo = "0";
    private String deletedTagColumn = "is_deleted";
    private String statusColumn = "status";
    private String columnCreateTime = "gmt_create";
    private String columnUpdateTime = "gmt_modify";
    private String encoding = "UTF-8";
    private DataTypeConvertor dataTypeConvertor;
    private List<String> tables;
    private String prefixRemove;

    /**
     * PO包名 必须
     */
    public String getPackageNamePo() {
        return packageNamePo;
    }

    /**
     * PO包名 必须
     */
    public void setPackageNamePo(String packageNamePo) {
        this.packageNamePo = packageNamePo;
    }

    //	/**
    //	 * 数据库地址 必须
    //	 */
    //	public String getDbUrl() {
    //		return dbUrl;
    //	}
    //
    //	/**
    //	 * 数据库地址 必须
    //	 */
    //	public void setDbUrl(String dbUrl) {
    //		this.dbUrl = dbUrl;
    //	}
    //
    //	/**
    //	 * 数据库端口 可选 默认1521
    //	 */
    //	public String getDbPort() {
    //		return dbPort;
    //	}
    //
    //	/**
    //	 * 数据库端口 可选 默认1521
    //	 */
    //	public void setDbPort(String dbPort) {
    //		this.dbPort = dbPort;
    //	}
    //
    //	/**
    //	 * 数据库ServerId 必须
    //	 */
    //	public String getDbSid() {
    //		return dbSid;
    //	}
    //
    //	/**
    //	 * 数据库ServerId 必须
    //	 */
    //	public void setDbSid(String dbSid) {
    //		this.dbSid = dbSid;
    //	}
    //
    //	/**
    //	 * 数据库用户名 必须
    //	 */
    //	public String getDbUser() {
    //		return dbUser;
    //	}
    //
    //	/**
    //	 * 数据库用户名 必须
    //	 */
    //	public void setDbUser(String dbUser) {
    //		this.dbUser = dbUser;
    //	}
    //
    //	/**
    //	 * 数据库密码 必须
    //	 */
    //	public String getDbPassword() {
    //		return dbPassword;
    //	}
    //
    //	/**
    //	 * 数据库密码 必须
    //	 */
    //	public void setDbPassword(String dbPassword) {
    //		this.dbPassword = dbPassword;
    //	}

    /**
     * DAO包名 必须
     */
    public String getPackageNameDao() {
        return packageNameDao;
    }

    /**
     * DAO包名 必须
     */
    public void setPackageNameDao(String packageNameDao) {
        this.packageNameDao = packageNameDao;
    }

    /**
     * PO基类名 可选 默认 BaseDomain
     */
    public String getBasePoName() {
        return basePoName;
    }

    /**
     * PO基类名 可选 默认 BaseDomain
     */
    public void setBasePoName(String basePoName) {
        this.basePoName = basePoName;
    }

    /**
     * DAO基类名 可选 <li>当ormType为hibernate时,默认HibernateDao</li> <li>
     * 当ormType为ibatis时,默认IbatisDao</li>
     */
    public String getBaseDaoName() {
        if (baseDaoName == null) {
            if (this.ormType == null) {
                baseDaoName = "Ibatis";
            } else {
                if (OrmType.hibernate.name().equals(this.ormType.toString())) {
                    baseDaoName = "Hibernate";
                } else {
                    baseDaoName = "Ibatis";
                }
            }
        }

        return baseDaoName;
    }

    /**
     * DAO基类名 必须
     */
    public void setBaseDaoName(String baseDaoName) {
        this.baseDaoName = baseDaoName;
    }

    /**
     * ORM类型 必须
     */
    public OrmType getOrmType() {
        return ormType;
    }

    /**
     * ORM类型 必须
     */
    public void setOrmType(OrmType ormType) {
        this.ormType = ormType;
    }

    /**
     * 生成的表 可选 不提供该参数则生成全部表
     */
    public List<String> getTables() {
        return tables;
    }

    /**
     * 生成的表 可选 不提供该参数则生成全部表
     */
    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    /**
     * 输出路径
     */
    public String getOutDir() {
        return outDir;
    }

    /**
     * 输出路径 可选 默认 d:\\evan-ormcreator-output
     */
    public void setOutDir(String outDir) {
        this.outDir = outDir;
    }

    /**
     * 去掉前缀
     */
    public String getPrefixRemove() {
        return prefixRemove;
    }

    /**
     * 去掉前缀
     */
    public void setPrefixRemove(String prefixRemove) {
        this.prefixRemove = prefixRemove;
    }

    public String getDeletedTagValueYes() {
        return deletedTagValueYes;
    }

    public void setDeletedTagValueYes(String deletedTagValueYes) {
        this.deletedTagValueYes = deletedTagValueYes;
    }

    public String getDeletedTagValueNo() {
        return deletedTagValueNo;
    }

    public void setDeletedTagValueNo(String deletedTagValueNo) {
        this.deletedTagValueNo = deletedTagValueNo;
    }

    public String getDeletedTagColumn() {
        return deletedTagColumn;
    }

    public void setDeletedTagColumn(String deletedTagColumn) {
        this.deletedTagColumn = deletedTagColumn;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public DataTypeConvertor getDataTypeConvertor() {
        return dataTypeConvertor;
    }

    public void setDataTypeConvertor(DataTypeConvertor dataTypeConvertor) {
        this.dataTypeConvertor = dataTypeConvertor;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
        this.databaseTypeClassName = OrmCreatorUtil.toFirstCharUpcase(databaseType.name());
    }

    /**
     * @return the statusColumn
     */
    public String getStatusColumn() {
        return statusColumn;
    }

    /**
     * @param statusColumn the statusColumn to set
     */
    public void setStatusColumn(String statusColumn) {
        this.statusColumn = statusColumn;
    }

    public String getDatabaseTypeClassName() {
        return databaseTypeClassName;
    }

    public String getBaseQueryClass() {
        return baseQueryClass;
    }

    public void setBaseQueryClass(String baseQueryClass) {
        this.baseQueryClass = baseQueryClass;
    }

    public String getQueryInterface() {
        return queryInterface;
    }

    public void setQueryInterface(String queryInterface) {
        this.queryInterface = queryInterface;
    }

    public String getPackageNameQuery() {
        return packageNameQuery;
    }

    public void setPackageNameQuery(String packageNameQuery) {
        this.packageNameQuery = packageNameQuery;
    }

    public String getPackageNameMapper() {
        return packageNameMapper;
    }

    public void setPackageNameMapper(String packageNameMapper) {
        this.packageNameMapper = packageNameMapper;
    }

    public String getJdbcDriverClassName() {
        return jdbcDriverClassName;
    }

    public void setJdbcDriverClassName(String jdbcDriverClassName) {
        this.jdbcDriverClassName = jdbcDriverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public void setJdbcUser(String jdbcUser) {
        this.jdbcUser = jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getBaseMapperName() {
        return baseMapperName;
    }

    public void setBaseMapperName(String baseMapperName) {
        this.baseMapperName = baseMapperName;
    }

    public String getTemplateDir() {
        return templateDir;
    }

    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }

    public String getColumnCreateTime() {
        return columnCreateTime;
    }

    public void setColumnCreateTime(String columnCreateTime) {
        this.columnCreateTime = columnCreateTime;
    }

    public String getColumnUpdateTime() {
        return columnUpdateTime;
    }

    public void setColumnUpdateTime(String columnUpdateTime) {
        this.columnUpdateTime = columnUpdateTime;
    }

    public String getPackageNameList() {
        return packageNameList;
    }

    public void setPackageNameList(String packageNameList) {
        this.packageNameList = packageNameList;
    }

    public String getPackageNameDto() {
        return packageNameDto;
    }

    public void setPackageNameDto(String packageNameDto) {
        this.packageNameDto = packageNameDto;
    }

    public String getTemplatePackage() {
        return templatePackage;
    }

    public void setTemplatePackage(String templatePackage) {
        this.templatePackage = templatePackage;
    }

    public String getDatabaseSchema() {
        return databaseSchema;
    }

    public void setDatabaseSchema(String databaseSchema) {
        this.databaseSchema = databaseSchema;
    }

    /**
     *
     */
    public String getPackageNameResponse() {
        return packageNameResponse;
    }

    /***/
    public void setPackageNameResponse(String packageNameResponse) {
        this.packageNameResponse = packageNameResponse;
    }

    /**
     *
     */
    public String getPackageNameManager() {
        return packageNameManager;
    }

    /***/
    public void setPackageNameManager(String packageNameManager) {
        this.packageNameManager = packageNameManager;
    }

    /**
     *
     */
    public String getPackageNameRoot() {
        return packageNameRoot;
    }

    /***/
    public void setPackageNameRoot(String packageNameRoot) {
        this.packageNameRoot = packageNameRoot;
    }

    @Override
    public String toString() {
        return "OrmCreatorParam{" +
                "ormType=" + ormType +
                ", databaseType=" + databaseType +
                ", databaseTypeClassName='" + databaseTypeClassName + '\'' +
                ", jdbcDriverClassName='" + jdbcDriverClassName + '\'' +
                ", jdbcUrl='" + jdbcUrl + '\'' +
                ", jdbcUser='" + jdbcUser + '\'' +
                ", jdbcPassword='" + jdbcPassword + '\'' +
                ", databaseSchema='" + databaseSchema + '\'' +
//                ", packageNameRoot='" + packageNameRoot + '\'' +
//                ", packageNameMapper='" + packageNameMapper + '\'' +
//                ", packageNamePo='" + packageNamePo + '\'' +
//                ", packageNameDao='" + packageNameDao + '\'' +
//                ", packageNameQuery='" + packageNameQuery + '\'' +
//                ", packageNameList='" + packageNameList + '\'' +
//                ", packageNameDto='" + packageNameDto + '\'' +
//                ", packageNameResponse='" + packageNameResponse + '\'' +
//                ", packageNameManager='" + packageNameManager + '\'' +
//                ", basePoName='" + basePoName + '\'' +
//                ", baseQueryClass='" + baseQueryClass + '\'' +
//                ", queryInterface='" + queryInterface + '\'' +
//                ", baseDaoName='" + baseDaoName + '\'' +
//                ", baseMapperName='" + baseMapperName + '\'' +
                ", outDir='" + outDir + '\'' +
                ", templateDir='" + templateDir + '\'' +
                ", templatePackage='" + templatePackage + '\'' +
                ", deletedTagValueYes='" + deletedTagValueYes + '\'' +
                ", deletedTagValueNo='" + deletedTagValueNo + '\'' +
                ", deletedTagColumn='" + deletedTagColumn + '\'' +
                ", statusColumn='" + statusColumn + '\'' +
                ", columnCreateTime='" + columnCreateTime + '\'' +
                ", columnUpdateTime='" + columnUpdateTime + '\'' +
//                ", encoding='" + encoding + '\'' +
//                ", dataTypeConvertor=" + dataTypeConvertor +
                ", tables=" + tables +
//                ", prefixRemove='" + prefixRemove + '\'' +
                '}';
    }

    /**
     * OrmType hibernate, ibatis
     */
    public enum OrmType {
        hibernate, ibatis, jdbc, mybatis
    }

    /**
     * DatabaseType oracle, mysql
     */
    public enum DatabaseType {
        oracle, mysql
    }
}


