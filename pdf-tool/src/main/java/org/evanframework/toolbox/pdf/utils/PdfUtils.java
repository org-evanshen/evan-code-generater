package org.evanframework.toolbox.pdf.utils;


import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import com.itextpdf.text.DocumentException;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 */
public class PdfUtils {
    private static final Logger logger = LoggerFactory.getLogger(PdfUtils.class);
    // private static final int BSIZE = 1024;
    private static final String ENCODE = "UTF-8";

    private static String openOfficeAddr = "192.168.0.232";
    private static int openOfficePort = 8100;
    /**
     * office中各种格式
     */
    private static final String[] OFFICE_POSTFIXS = {"doc", "docx", "xls", "xlsx", "ppt", "pptx"};
    private static final ArrayList<String> officeFormats = new ArrayList<String>() {
        {
            addAll(Arrays.asList(OFFICE_POSTFIXS));
        }
    };

    public static void htmlToPdf(String inputHtmlFile, String outputPdfFile) throws IOException, DocumentException {
        String content = FileUtils.readFileToString(new File(inputHtmlFile));
        FileOutputStream os = getFileOutputStream(outputPdfFile);

        try {
            doCreatePdf(content, os);
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public static void createPdf(String content, String outputFile) throws DocumentException, IOException {
        FileOutputStream os = getFileOutputStream(outputFile);
        logger.info("Create pdf, pdf file is [{}] ", outputFile);
        try {
            doCreatePdf(content, os);
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    public static void office2Pdf(String inputFilePath, String outputFilePath) throws ConnectException{
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);
        // 假如目标路径不存在,则新建该路径
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }

        OpenOfficeConnection connection = new SocketOpenOfficeConnection( openOfficeAddr, openOfficePort);
        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);

        try {
            connection.connect();
            converter.convert(inputFile, outputFile);
        }finally {
            connection.disconnect();
        }

//        OfficeManager officeManager = buildOfficeManager(openOfficeAddr, openOfficePort);
//
//        try {
//            officeManager.start();
//            logger.debug("=============openOffice  service  is start ");
//            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
//            converter.convert(inputFile, outputFile);
//        } finally {
//            if (null != officeManager) {
//                officeManager.stop();
//                logger.debug("=============openOffice  service  is stop ");
//            }
//        }
    }

    private static OfficeManager buildOfficeManager(String homePath, int port) {
        OfficeManager officeManager;

//        logger.debug("=============尝试连接已启动的服务...");
//        ExternalOfficeManagerConfiguration externalProcessOfficeManager = new ExternalOfficeManagerConfiguration();
//        externalProcessOfficeManager.set
//        externalProcessOfficeManager.setConnectOnStart(true);
//        externalProcessOfficeManager.setPortNumber(port);
//        officeManager = externalProcessOfficeManager.buildOfficeManager();
//        officeManager.start();
//        logger.debug("=============office转换服务启动成功!");
//        return officeManager;

        logger.debug("=============创建并连接openoffice服务...");
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        configuration.setPortNumbers(port);
        configuration.setTaskExecutionTimeout(60 * 5 * 1000L);
        configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);
        officeManager = configuration.buildOfficeManager();

        return officeManager;

    }

    /**
     * 获取inputFilePath的后缀名，如："e:/test.pptx"的后缀名为："pptx"
     */
    private static String getPostfix(String inputFilePath) {
        int pos = inputFilePath.lastIndexOf(".");
        if (pos > 0) {
            return inputFilePath.substring(pos + 1).toLowerCase();
        }
        return null;
    }

    private static FileOutputStream getFileOutputStream(String outputFile) throws FileNotFoundException {
        FileUtils.mkdirs(outputFile);
        FileOutputStream os = new FileOutputStream(outputFile);
        return os;
    }

    private static void doCreatePdf(String content, FileOutputStream os) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(content);

        //ITextFontResolver fontResolver = renderer.getFontResolver();
        //fontResolver.addFont("system.pdf.font", BaseFont.IDENTITY_H,
        //BaseFont.NOT_EMBEDDED);

        renderer.layout();
        renderer.createPDF(os);
    }

    private static byte[] inputStreamToByte(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int count = -1;
        while ((count = in.read(data, 0, 1024)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return outStream.toByteArray();
    }
}
