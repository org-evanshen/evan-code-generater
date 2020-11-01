package org.evan.libraries.codegenerate.outputor;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.evan.libraries.codegenerate.model.OrmCreatorParam;
import org.evan.libraries.codegenerate.model.OutputModel;
import org.evan.libraries.codegenerate.utils.OrmCreatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Orm输出类，用于输出生成的文件
 *
 * @author <a href="mailto:shenw@hundsun.com">Evan.Shen</a>
 * @version Date: 2010-9-11 下午02:49:07
 */
public abstract class AbstractOrmOutputor implements OrmOutputor.InnerOrmOutputor {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractOrmOutputor.class);
    protected OrmCreatorParam param;
    protected String ormTemplatePath;
    private VelocityEngine velocityEngine = null;

    public AbstractOrmOutputor(OrmCreatorParam param) {
        this.param = param;

        //URL url = ClassLoader.getSystemResource(param.getTemplateDir());
        String templateDir = param.getTemplateDir();

        velocityEngine = new VelocityEngine();

//        velocityEngine.setProperty(Velocity.RESOURCE_LOADER, "file");
//        velocityEngine.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, templateDir);
//        ormTemplatePath = "/";

        velocityEngine.setProperty(Velocity.RESOURCE_LOADERS, "class");
        velocityEngine.setProperty("resource.loader.class.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        ormTemplatePath = param.getTemplateDir() + "/";

        velocityEngine.init();
    }

    public abstract void outputByTable(OutputModel outputor, Map<String, Object> mapOutputor);

    /**
     * <p>
     * <a href="mailto:shenw@hundsun.com">Evan.Shen</a> create at 2013-9-26 上午10:28:52
     * </p>
     *
     * @param outputor
     * @param mapOutputor
     */
    protected void outputCommon(OutputModel outputor, Map<String, Object> mapOutputor) {
        String subPath = OrmCreatorUtil.convertTableNameToSubPackageName(outputor.getTableName());

        String template;

        template = ormTemplatePath + "common/common-po.vm";
        mergeTemplate(mapOutputor, template, "model/" +  subPath, outputor.getClassName() + ".java");

        template = ormTemplatePath + "common/common-query.vm";
        String str = mergeTemplateToString(template, mapOutputor);
        write("model/" +  subPath, outputor.getClassName() + "QueryDTO.java", str);

        template = ormTemplatePath + "common/common-addupdate.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("model/" +  subPath, outputor.getClassName() + "AddUpdateDTO.java", str);

        template = ormTemplatePath + "common/common-vo.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("model/" +  subPath, outputor.getClassName() + "VO.java", str);

        template = ormTemplatePath + "common/common-list.vm";
        mergeTemplate(mapOutputor, template, "model/" +  subPath, outputor.getClassName() + "VOList.java");

//        template = ormTemplatePath + "common-response.vm";
//        str = mergeTemplateToString(template, mapOutputor);
//        write("client/response", outputor.getClassName() + "Response.java", str);

//        template = ormTemplatePath + "common/common-do.vm";
//        str = mergeTemplateToString(template, mapOutputor);
//        write("domain/" + subPath, outputor.getClassName() + "Domain.java", str);
//
//        template = ormTemplatePath + "common/common-do-factory.vm";
//        str = mergeTemplateToString(template, mapOutputor);
//        write("domain/" + subPath, outputor.getClassName() + "Factory.java", str);

//        template = ormTemplatePath + "common-columns.vm";
//        str = mergeTemplateToString(template, mapOutputor);
//        write("service/columns", outputor.getClassName() + "Columns.java", str);

//        template = ormTemplatePath + "common-manager.vm";
//        str = mergeTemplateToString(template, mapOutputor);
//        write("service/manager", outputor.getClassName() + "Manager.java", str);

        String fileName = outputor.getClassName();

//        template = ormTemplatePath + "ui/page-list.vm";
//        str = mergeTemplateToString(template, mapOutputor);
//        write("ui/", fileName + "List.html", str);

        template = ormTemplatePath + "ui/page-form.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("vue/" + subPath, fileName + "-form.html", str);

        template = ormTemplatePath + "ui/page-detail.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("vue/" + subPath, fileName + "-detail.html", str);

        template = ormTemplatePath + "ui/page-vue-html.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("vue/" + subPath, fileName + "-list.html", str);

        template = ormTemplatePath + "ui/page-vue-js.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("vue/" + subPath, fileName + ".vue", str);

        template = ormTemplatePath + "ui/page-vue-service.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("vue/service/", fileName + "Service.vue", str);
    }

    public void outputAllTabls(List<OutputModel> outputModels) {
        String template = ormTemplatePath + "db-design/dbDesign.vm";
        Map map = new HashMap();
        map.put("tables", outputModels);
        mergeTemplate(map, template, "", "dbDesign.html");
    }

    protected void write(String path, String file, String content) {
        File outFile = getTargetFile(path, file);
        PrintWriter out = null;
        try {
            outFile.createNewFile();
            //fos = new FileOutputStream(outFile);
            //osw = new OutputStreamWriter(fos, param.getEncoding());
            out = new PrintWriter(outFile, param.getEncoding());
            out.write(content);
            LOGGER.info("output:{}", outFile);
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    protected String mergeTemplateToString(String templateLocation, Map<String, Object> model) {
        StringWriter writer = new StringWriter();
        VelocityContext velocityContext = new VelocityContext(model);
        velocityEngine.mergeTemplate(templateLocation, param.getEncoding(), velocityContext, writer);
        return writer.toString();
    }

    protected void mergeTemplate(Map<String, Object> model, String templateLocation, String targetDir, String targetFile) {
//        String writer = null;
//        File targetPath = getTargetFile(targetDir, targetFile);
//
//        try {
//            writer = new FileWriter(targetPath);
//        } catch (IOException ex) {
//            LOGGER.error(ex.getMessage(), ex);
//        }
//
//        VelocityContext velocityContext = new VelocityContext(model);
//        velocityEngine.mergeTemplate(templateLocation, param.getEncoding(), velocityContext, writer);
        String content = mergeTemplateToString(templateLocation, model);
        write(targetDir, targetFile, content);
//        LOGGER.info("output: {}",targetPath);
    }

    protected VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    private File getTargetFile(String path, String file) {
        String fullPath = param.getOutDir() + File.separatorChar + path;

        File dirPath = new File(fullPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

        File outFile = new File(fullPath + File.separatorChar + file);
        if (outFile.exists()) {
            outFile.delete();
        }
        return outFile;
    }
}
