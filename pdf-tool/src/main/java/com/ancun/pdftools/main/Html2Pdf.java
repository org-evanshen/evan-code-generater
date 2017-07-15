package com.ancun.pdftools.main;

import com.ancun.pdftools.utils.PdfUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Html2Pdf
 *
 * @author <a href="mailto:shenwei@ancun.com">Evan.shen</a>
 * @date 16/8/16
 */
public class Html2Pdf {
    private static final Logger logger = LoggerFactory.getLogger(Html2Pdf.class);

    public static void main(String[] args){
        if(args.length<2){
            logger.error("参数不能小于2个");
        }else {
            logger.info("source:{}",args[0]);
            logger.info("target:{}",args[1]);

            try {
                PdfUtils.htmlToPdf(args[0], args[1]);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
}
