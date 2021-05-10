package com.dcm.easypoi.cache;

import com.dcm.easypoi.cache.manager.POICacheManager;
import com.itextpdf.kernel.pdf.PdfReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
/**
 * PDF 缓存中心
 *
 * @Author hourz
 * @since 2018-08-06
 */
public class PdfCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfCache.class);

    public static PdfReader getDocument(String url) {
        InputStream is = null;
        try {
            is = POICacheManager.getFile(url);
            PdfReader pdfReader = new PdfReader(is);
            return pdfReader;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
