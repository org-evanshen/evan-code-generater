package org.evan.libraries.codegenerate.outputor;

import org.evan.libraries.codegenerate.model.OrmCreatorParam;
import org.evan.libraries.codegenerate.model.OutputModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrmOutputor {
	private InnerOrmOutputor innerOrmOutputor;

	public OrmOutputor(OrmCreatorParam param) {
		OrmCreatorParam.OrmType ormType = param.getOrmType();
		if (OrmCreatorParam.OrmType.hibernate.equals(ormType)) {
			innerOrmOutputor = new OrmOutputorHibernate(param);
		} else if (OrmCreatorParam.OrmType.jdbc.equals(ormType)) {
			innerOrmOutputor = new OrmOutputorJdbc(param);
		} else if (OrmCreatorParam.OrmType.ibatis.equals(ormType)) {
			innerOrmOutputor = new OrmOutputorIbatis(param);
		} else {
			innerOrmOutputor = new OrmOutputorMybatis(param);
		}
	}

	/**
	 * 根据单张表输出
	 * @param outputor
	 */
	public void outputByTable(OutputModel outputor) {
		Map<String, Object> mapOutputor = new HashMap<String, Object>();
		mapOutputor.put("outputor", outputor);
		innerOrmOutputor.outputByTable(outputor, mapOutputor);
	}

	/**
	 * 根据所有表输出
	 * @param outputModels
	 */
	public void outputAllTabls(List<OutputModel> outputModels) {
		innerOrmOutputor.outputAllTabls(outputModels);
	}

	public interface InnerOrmOutputor {
		void outputByTable(OutputModel outputor, Map<String, Object> mapOutputor);
		void outputAllTabls(List<OutputModel> outputModels);
	}

	public InnerOrmOutputor getInnerOrmOutputor() {
		return innerOrmOutputor;
	}
}
