package org.evanframework.toolbox.ormcreator.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * Orm输出对象，用于输出到velocity
 * 
 * @author <a href="mailto:shenw@hundsun.com">Evan.Shen</a>
 * @version Date: 2010-9-6 上午10:21:45
 * @since
 */
public class OrmTemplete {
	private String tableName;// 表明
	private String tableAlias;//sql语句中的表简名
	private String objectName;// 对象名
	private String className;// 类名
	// 比如表名为SYS_USER,则类名为SysUser,对象名为sysUser,tableAlias为su

	private String packageNamePo;// PO类的包名
	private String packageNameDao;// Dao类的包名
	private String baseDaoName;// Dao类的基类，默认为BaseDao
	private String baseDaoImplName;// Dao实现类的基类
	private String basePoName;// PO类的基类，默认为BasePPO;
	private OrmCreatorParam param;

	private String pkColumnNames;// 主键列名，有多个主键的以","分割
	private String pkParams;// 主键列参数名，比如表中主键为ID(NUMBER)，则该值为"Integer id"，比如表中主键为entId(VARCHAR2)、year(VARCHAR2)，则该值为“String
							// entId,String year”
	private Integer pkCount;// 主键数量
	private String pkFieldNames;// 主键属性名，比如表中主键为ID，则该值为"id"，比如表中主键为ENT_ID、YEAR，则该值为"entId,year"
	private String pkIbatisDataType;
	private String pkJavaDataType;
	
	private String  comment; //表单说明

	private List<Column> columns = new ArrayList<Column>();// 列集合
	private List<Column> columnNotPKs = new ArrayList<Column>();// 非主键列集合
	private List<Column> columnPKs = new ArrayList<Column>();// 主键列集合
	
	private long serialVersionUID;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getPkColumnNames() {
		return pkColumnNames;
	}

	public void setPkColumnNames(String pkColumnNames) {
		this.pkColumnNames = pkColumnNames;
	}

	public String getPkParams() {
		return pkParams;
	}

	public void setPkParams(String pkParams) {
		this.pkParams = pkParams;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<Column> getColumnNotPKs() {
		return columnNotPKs;
	}

	public void setColumnNotPKs(List<Column> columnNotPKs) {
		this.columnNotPKs = columnNotPKs;
	}

	public List<Column> getColumnPKs() {
		return columnPKs;
	}

	public void setColumnPKs(List<Column> columnPKs) {
		this.columnPKs = columnPKs;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getPkCount() {
		return pkCount;
	}

	public void setPkCount(Integer pkCount) {
		this.pkCount = pkCount;
	}

	public String getPkFieldNames() {
		return pkFieldNames;
	}

	public void setPkFieldNames(String pkFieldNames) {
		this.pkFieldNames = pkFieldNames;
	}

	public String getPkIbatisDataType() {
		return pkIbatisDataType;
	}

	public void setPkIbatisDataType(String pkIbatisDataType) {
		this.pkIbatisDataType = pkIbatisDataType;
	}

	public String getPackageNamePo() {
		return packageNamePo;
	}

	public void setPackageNamePo(String packageNamePo) {
		this.packageNamePo = packageNamePo;
	}

	public String getPackageNameDao() {
		return packageNameDao;
	}

	public void setPackageNameDao(String packageNameDao) {
		this.packageNameDao = packageNameDao;
	}

	public String getBaseDaoName() {
		return baseDaoName;
	}

	public void setBaseDaoName(String baseDaoName) {
		this.baseDaoName = baseDaoName;
	}

	public String getBasePoName() {
		return basePoName;
	}

	public void setBasePoName(String basePoName) {
		this.basePoName = basePoName;
	}

	public String getBaseDaoImplName() {
		return baseDaoImplName;
	}

	public void setBaseDaoImplName(String baseDaoImplName) {
		this.baseDaoImplName = baseDaoImplName;
	}

	public OrmCreatorParam getParam() {
		return param;
	}

	public void setParam(OrmCreatorParam param) {
		this.param = param;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPkJavaDataType() {
		return pkJavaDataType;
	}

	public void setPkJavaDataType(String pkJavaDataType) {
		this.pkJavaDataType = pkJavaDataType;
	}

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setSerialVersionUID(long serialVersionUID) {
		this.serialVersionUID = serialVersionUID;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

}
