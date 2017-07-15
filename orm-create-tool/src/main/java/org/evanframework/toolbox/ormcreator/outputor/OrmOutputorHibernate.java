package org.evanframework.toolbox.ormcreator.outputor;

import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.domain.OrmTemplete;

import java.util.Map;

public class OrmOutputorHibernate extends AbstractOrmOutputor implements OrmOutputor.InnerOrmOutputor {

	public OrmOutputorHibernate(OrmCreatorParam param) {
		super(param);
	}

	public void outPut(OrmTemplete outputor, Map<String, Object> mapOutputor) {
		String poTemplate;	

		if (outputor.getColumnPKs().size() <= 1) {
			poTemplate = "hibernate-po-one-pk.vm";
		} else {
			poTemplate = "hibernate-po-more-pk.vm";
		}

		String ormTemplatePath = param.getTemplateDir() + "/";
		String str = mergeTemplateToString(ormTemplatePath + poTemplate, mapOutputor);

		write("po", outputor.getClassName() + ".java", str);

		if (outputor.getColumnPKs().size() > 1) {
			str = mergeTemplateToString("hibernate-po-pk.vm", mapOutputor);
			write("po", outputor.getClassName() + "PK.java", str);
		}
	}
}
