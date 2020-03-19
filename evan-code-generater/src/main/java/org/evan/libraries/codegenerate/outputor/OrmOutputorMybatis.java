package org.evan.libraries.codegenerate.outputor;

import org.evan.libraries.codegenerate.model.OrmCreatorParam;
import org.evan.libraries.codegenerate.model.OutputModel;
import org.evan.libraries.codegenerate.utils.OrmCreatorUtil;

import java.util.Map;

public class OrmOutputorMybatis extends AbstractOrmOutputor implements OrmOutputor.InnerOrmOutputor {

    public OrmOutputorMybatis(OrmCreatorParam param) {
        super(param);
    }

    @Override
    public void outputByTable(OutputModel outputor, Map<String, Object> mapOutputor) {
        String template;

        outputCommon(outputor, mapOutputor);

        String subPath = OrmCreatorUtil.convertTableNameToSubPackageName(outputor.getTableName());

        String str;

        template = ormTemplatePath + "mybatis/mybatis-mapper-xml.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("mybatis", outputor.getClassName() + "Mapper.xml", str);

        template = ormTemplatePath + "mybatis/mybatis-mapper-class.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("mybatis" , outputor.getClassName() + "Mapper.java", str);

//		template = ormTemplatePath + "common/mybatis-dao.vm";
////		str = mergeTemplateToString(template, mapOutputor);
////		write("service/dao", outputor.getClassName() + "Dao.java", str);

        template = ormTemplatePath + "mybatis/mybatis-mapper-test.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("test", outputor.getClassName() + "MapperTest.java", str);

    }

}
