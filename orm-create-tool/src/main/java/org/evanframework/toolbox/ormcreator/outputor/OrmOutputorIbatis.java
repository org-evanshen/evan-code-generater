package org.evanframework.toolbox.ormcreator.outputor;

import org.evanframework.toolbox.ormcreator.model.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.model.OutputModel;

import java.util.Map;

public class OrmOutputorIbatis extends AbstractOrmOutputor implements OrmOutputor.InnerOrmOutputor {
	public OrmOutputorIbatis(OrmCreatorParam param) {
		super(param);
	}

	public void outPut(OutputModel outputor, Map<String, Object> mapOutputor) {
		String template;

		outputCommon(outputor, mapOutputor);

		String str;

		template = ormTemplatePath + "ibatis-sqlmap.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("sqlmap", outputor.getObjectName() + "-sqlmap.xml", str);

		template = ormTemplatePath + "ibatis-dao.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("dao", outputor.getClassName() + "Dao.java", str);

		// template = ormTemplatePath + "ibatis-daoImpl.vm";
		// str = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
		// template,encoding, map);
		// write("dao" + File.separatorChar + "ibatis", outputor.getClassName()
		// + "DaoIbatis.java", str);
	}
}
