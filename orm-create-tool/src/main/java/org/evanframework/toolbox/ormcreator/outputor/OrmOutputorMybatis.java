package org.evanframework.toolbox.ormcreator.outputor;

import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.domain.OrmTemplete;

import java.util.Map;

public class OrmOutputorMybatis extends AbstractOrmOutputor implements OrmOutputor.InnerOrmOutputor {

	public OrmOutputorMybatis(OrmCreatorParam param) {
		super(param);
	}

	@Override
	public void outPut(OrmTemplete outputor, Map<String, Object> mapOutputor) {
		String template;

		outputCommon(outputor, mapOutputor);

		String str;

		template = ormTemplatePath + "mybatis-mapper-xml.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("mybatismapperxml", outputor.getClassName() + "Mapper.xml", str);

		template = ormTemplatePath + "mybatis-mapper-class.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("mybatismapperclass", outputor.getClassName() + "Mapper.java", str);

		template = ormTemplatePath + "mybatis-dao.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("dao", outputor.getClassName() + "Dao.java", str);
		
		template = ormTemplatePath + "mybatis-mapper-test.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("test", outputor.getClassName() + "MapperTest.java", str);

	}

}
