package com.dcm.easypoi.pdf.export;

import com.dcm.easypoi.cache.PdfCache;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.MyDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * PDF转换读取、
 *
 * @Author hourz
 * @since 2018-01-06
 */
public class PdfTemplateServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfTemplateServer.class);

    private MyDocument document;


    public Document parsePdf(String url, Map<String, Object> map, OutputStream outStream) {
        PdfDocument outDocument = null;
        try {
            PdfReader pdfReader = PdfCache.getDocument(url);
            PdfWriter pdfWriter = new PdfWriter(outStream);
            outDocument = new PdfDocument(pdfReader, pdfWriter);
            document = new MyDocument(outDocument);
            replaceDocument(outDocument, map);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            document.close();
            outDocument.close();
            ;
        }
        return document;
    }

    public void replaceDocument(PdfDocument redDocument, Map<String, Object> map) {
        int numberOfPages = redDocument.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {
            PdfDictionary dict = redDocument.getPage(i).getPdfObject();
            PdfObject object = dict.get(PdfName.Contents);
            if (object instanceof PdfStream) {
                PdfStream stream = (PdfStream) object;
                byte[] data = stream.getBytes();
                String replacedData = new String(data).replace("testCode", "小明");
                stream.setData(replacedData.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
