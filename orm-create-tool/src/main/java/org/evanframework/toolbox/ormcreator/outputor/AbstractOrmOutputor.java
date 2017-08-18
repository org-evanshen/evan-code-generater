package org.evanframework.toolbox.ormcreator.outputor;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.evanframework.toolbox.ormcreator.domain.OrmCreatorParam;
import org.evanframework.toolbox.ormcreator.domain.OrmTemplete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * Orm输出类，用于输出生成的文件
 *
 * @author <a href="mailto:shenw@hundsun.com">Evan.Shen</a>
 * @version Date: 2010-9-11 下午02:49:07
 */
public abstract class AbstractOrmOutputor implements OrmOutputor.InnerOrmOutputor {
    private static final Logger logger = LoggerFactory.getLogger(AbstractOrmOutputor.class);

    private VelocityEngine velocityEngine = null;
    protected OrmCreatorParam param;
    protected String ormTemplatePath;

    public AbstractOrmOutputor(OrmCreatorParam param) {
        this.param = param;

        //URL url = ClassLoader.getSystemResource(param.getTemplateDir());
        String templateDir = System.getProperty("user.dir") + "/" + param.getTemplateDir();
        if (logger.isInfoEnabled()) {
            logger.info("templateDir:" + templateDir);
        }

        velocityEngine = new VelocityEngine();

        velocityEngine.setProperty(Velocity.RESOURCE_LOADER, "file");
        velocityEngine.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, templateDir);
        ormTemplatePath = "/";

        //velocityEngine.setProperty(Velocity.RESOURCE_LOADER, "class");
        //velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //ormTemplatePath = param.getTemplateDir()+"/";

        velocityEngine.init();
    }

    public abstract void outPut(OrmTemplete outputor, Map<String, Object> mapOutputor);

    protected void write(String path, String file, String content) {
        String fullPath = param.getOutDir() + File.separatorChar + path;

        File dirPath = new File(fullPath);

        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }

        File outFile = new File(fullPath + File.separatorChar + file);
        if (outFile.exists()) {
            outFile.delete();
        }

        PrintWriter out = null;
        //FileOutputStream fos = null;
        //OutputStreamWriter osw = null;
        //BufferedReader input = null;

        try {
            outFile.createNewFile();
            //fos = new FileOutputStream(outFile);
            //osw = new OutputStreamWriter(fos, param.getEncoding());
            out = new PrintWriter(outFile, param.getEncoding());
            out.write(content);
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        } finally {
            if (out != null) {
                out.close();
                //				try {
                //					fos.close();
                //				} catch (IOException e) {
                //					logger.error(e.getMessage(), e);
                //				}
                //				try {
                //					osw.close();
                //				} catch (IOException e) {
                //					logger.error(e.getMessage(), e);
                //				}
                //				try {
                //					input.close();
                //				} catch (IOException e) {
                //					logger.error(e.getMessage(), e);
                //				}
            }
        }
    }

    protected String mergeTemplateToString(String templateLocation, Map<String, Object> model) {
        StringWriter writer = new StringWriter();
        VelocityContext velocityContext = new VelocityContext(model);
        velocityEngine.mergeTemplate(templateLocation, param.getEncoding(), velocityContext, writer);
        return writer.toString();
    }

    protected VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    /**
     * <p>
     * <a href="mailto:shenw@hundsun.com">Evan.Shen</a> create at 2013-9-26 上午10:28:52
     * </p>
     *
     * @param outputor
     * @param mapOutputor
     */
    protected void outputCommon(OrmTemplete outputor, Map<String, Object> mapOutputor) {

        String template;
        template = ormTemplatePath + "common-model.vm";
        String str = mergeTemplateToString(template, mapOutputor);
        write("model", outputor.getClassName() + ".java", str);

        template = ormTemplatePath + "common-query.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("query", outputor.getClassName() + "Query.java", str);

		template = ormTemplatePath + "common-list.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("list", outputor.getClassName() + "List.java", str);

		template = ormTemplatePath + "common-response.vm";
		str = mergeTemplateToString(template, mapOutputor);
		write("dto", outputor.getClassName() + "Response.java", str);

        template = ormTemplatePath + "common-columns.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("columns", outputor.getClassName() + "Columns.java", str);

        String name = outputor.getTableName().toLowerCase();

        template = ormTemplatePath + "page-form.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("page/" + name, name + "-form.vm", str);

        template = ormTemplatePath + "page-detail.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("page/" + name, name + "-detail.vm", str);

        template = ormTemplatePath + "page-list.vm";
        str = mergeTemplateToString(template, mapOutputor);
        write("page/" + name, name + "-list.vm", str);
    }
}
