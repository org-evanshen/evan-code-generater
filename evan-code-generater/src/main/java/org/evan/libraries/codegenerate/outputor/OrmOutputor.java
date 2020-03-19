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

	public void outPub(OutputModel outputor) {
		Map<String, Object> mapOutputor = new HashMap<String, Object>();
		mapOutputor.put("outputor", outputor);
		innerOrmOutputor.outPut(outputor, mapOutputor);
	}

	public void outAll(List<OutputModel> outputModels) {
		innerOrmOutputor.outAll(outputModels);
	}

	public interface InnerOrmOutputor {
		void outPut(OutputModel outputor, Map<String, Object> mapOutputor);
		void outAll(List<OutputModel> outputModels);
	}
}
