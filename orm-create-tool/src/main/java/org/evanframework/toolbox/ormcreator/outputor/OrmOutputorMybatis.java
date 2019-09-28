package org.evanframework.toolbox.ormcreator.outputor;

import org.evanframework.toolbox.ormcreator.model.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.model.OutputModel;
import org.evanframework.toolbox.ormcreator.utils.OrmCreatorUtil;

import java.util.Map;

public class OrmOutputorMybatis extends AbstractOrmOutputor implements OrmOutputor.InnerOrmOutputor {

    public OrmOutputorMybatis(OrmCreatorParam param) {
        super(param);
    }

    @Override
    public void outPut(OutputModel outputor, Map<String, Object> mapOutputor) {
        String template;

        outputCommon(outputor, mapOutputor);

        String subPath = OrmCreatorUtil.convertTableNameToSubPackageName(outputor.getTableName());

        String str;

        template = ormTemplatePath + "mybatis/mybatis-mapper-xml.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("mapper", outputor.getClassName() + "Mapper.xml", str);

        template = ormTemplatePath + "mybatis/mybatis-mapper-class.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("domain" , outputor.getClassName() + "Mapper.java", str);

//		template = ormTemplatePath + "common/mybatis-dao.vm";
//		str = mergeTemplateToString(template, mapOutputor);
//		write("service/dao", outputor.getClassName() + "Dao.java", str);

        template = ormTemplatePath + "mybatis/mybatis-mapper-test.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("test", outputor.getClassName() + "MapperTest.java", str);

    }

}
