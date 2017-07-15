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
public class Office2Pdf {
    private static final Logger logger = LoggerFactory.getLogger(Office2Pdf.class);

    public static void main(String[] args) {
        if (args.length < 2) {
            logger.error("参数不能小于2个");
        } else {
            logger.info("Begin office to pdf");
            logger.info("Source:{}", args[0]);
            logger.info("Target:{}", args[1]);

            try {
                PdfUtils.office2Pdf(args[0], args[1]);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
}
