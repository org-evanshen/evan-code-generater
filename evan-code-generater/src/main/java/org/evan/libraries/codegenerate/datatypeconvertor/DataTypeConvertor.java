package org.evan.libraries.codegenerate.datatypeconvertor;

/**
 * 数据类型转换器
 * <p>
 * 
 * @author <a href="mailto:shenw@hundsun.com">Evan.Shen</a>
 * @version 2011-4-22 下午10:57:31
 */
public interface DataTypeConvertor {
	/**
	 * 将数据库类型转换成java类型
	 * <p>
	 * author: <a href="mailto:shenw@hundsun.com">Evan.Shen</a><br>
	 * version: 2012-1-14 上午11:47:21 <br>
	 * 
	 * @param dbDataType
	 * @param dbDataScale
	 * @param isPk
	 * @return
	 */
	String convertDBDataTypeToJavaDataType(String dbDataType, Integer length, String dbDataScale, boolean isPk);

	/**
	 * 将数据库类型转换成jdbc类型
	 * <p>
	 * author: <a href="mailto:shenw@hundsun.com">Evan.Shen</a><br>
	 * version: 2012-1-14 上午11:47:59 <br>
	 * 
	 * @param dbDataType
	 * @return
	 */
	String convertDBDataTypeToJdbcType(String dbDataType, Integer length, String dbDataScale);
}
