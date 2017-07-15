package org.evanframework.toolbox.ormcreator.outputor;

import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.domain.OrmTemplete;

import java.util.Map;

public class OrmOutputorJdbc extends AbstractOrmOutputor implements OrmOutputor.InnerOrmOutputor {

	public OrmOutputorJdbc(OrmCreatorParam param) {
		super(param);
	}

	public void outPut(OrmTemplete outputor, Map<String, Object> mapOutputor) {
		String template;

		String dataBaseType = outputor.getParam().getDatabaseType().name();
		String databaseclassName = outputor.getParam().getDatabaseTypeClassName();

		outputCommon(outputor, mapOutputor);

		String str;

		template = ormTemplatePath + "jdbc-dao-interface.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("dao", outputor.getClassName() + "Dao.java", str);

		template = ormTemplatePath + "jdbc-dao-abstract.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("dao/impl", outputor.getClassName() + "AbstractDao.java", str);

		template = ormTemplatePath + "jdbc-dao-db.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("dao/impl/" + dataBaseType, outputor.getClassName() + databaseclassName + "Dao.java", str);
	}
}