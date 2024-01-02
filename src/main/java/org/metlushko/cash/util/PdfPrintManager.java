package org.metlushko.cash.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PdfPrintManager {

    private static final String PDF_OUTPUT = "pdf.output";
    private static final String PDF_LAYER = "pdf.layer";

    public static String getPathPdfOutput() {
        return PropertiesUtil.get(PDF_OUTPUT);
    }

    public static String getPathPdfLayer(){
        return PropertiesUtil.get(PDF_LAYER);
    }

}
