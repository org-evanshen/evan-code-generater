package org.evanframework.toolbox.ormcreator.outputor;

import org.evanframework.toolbox.ormcreator.model.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.model.OrmCreatorParam.OrmType;
import org.evanframework.toolbox.ormcreator.model.OutputModel;

import java.util.HashMap;
import java.util.Map;

public class OrmOutputor {
	private InnerOrmOutputor innerOrmOutputor;

	public OrmOutputor(OrmCreatorParam param) {
		OrmType ormType = param.getOrmType();
		if (OrmType.hibernate.equals(ormType)) {
			innerOrmOutputor = new OrmOutputorHibernate(param);
		} else if (OrmType.jdbc.equals(ormType)) {
			innerOrmOutputor = new OrmOutputorJdbc(param);
		} else if (OrmType.ibatis.equals(ormType)) {
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

	public interface InnerOrmOutputor {
		void outPut(OutputModel outputor, Map<String, Object> mapOutputor);
	}
}
