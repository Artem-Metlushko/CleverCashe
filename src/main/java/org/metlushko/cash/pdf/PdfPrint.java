package org.metlushko.cash.pdf;

import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.metlushko.cash.entity.User;
import org.metlushko.cash.util.PdfPrintManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PdfPrint {

    public static void createReport(List<User> list) {


        String output = PdfPrintManager.getPathPdfOutput();
        PdfWriter pdfWriter = getPdfWriter(output);

        String pathPdfLayer = PdfPrintManager.getPathPdfLayer();
        PdfReader pdfReader = getPdfReader(pathPdfLayer);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);

        addLayout(pdfReader, pdfDocument);

        Document doc = new Document(pdfDocument);


        addParagraph(doc);

        Table table = getTable(list);

        doc.add(table);
        doc.close();

    }

    private static Table getTable(List<User> list) {

        float[] pointColumnWidths = {100F, 100F, 100F, 100F, 100F, 100F};
        Table table = new Table(pointColumnWidths);
        table.addCell(new Cell().add("Id"));
        table.addCell(new Cell().add("FirstName"));
        table.addCell(new Cell().add("LastName"));
        table.addCell(new Cell().add("SurName"));
        table.addCell(new Cell().add("Email"));
        table.addCell(new Cell().add("PhoneNumber"));

        for (User user : list) {
            table.addCell(user.getUserId());
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
            table.addCell(user.getSurName());
            table.addCell(user.getEmail());
            table.addCell(user.getPhoneNumber());
        }

        return table;
    }

    private static PdfReader getPdfReader(String exist) {
        try {
            return new PdfReader(exist);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static PdfWriter getPdfWriter(String dest) {
        try {
            return new PdfWriter(dest);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addParagraph(Document doc) {
        for (int i = 0; i < 10; i++) {
            doc.add(new Paragraph(""));
        }
    }

    private static void addLayout(PdfReader pdfReader, PdfDocument pdfDocument) {
        PdfDocument srcPdf = new PdfDocument(pdfReader);

        PdfPage origPage = srcPdf.getPage(1);
        Rectangle orig = origPage.getPageSizeWithRotation();

        PdfPage page = pdfDocument.addNewPage();

        AffineTransform transformationMatrix = AffineTransform.getScaleInstance(
                page.getPageSize().getWidth() / orig.getWidth(),
                page.getPageSize().getHeight() / orig.getHeight());


        PdfCanvas canvas = new PdfCanvas(page);
        canvas.concatMatrix(transformationMatrix);

        PdfFormXObject pageCopy = getPageCopy(pdfDocument, origPage);
        canvas.addXObject(pageCopy, 0, 0);
    }

    private static PdfFormXObject getPageCopy(PdfDocument pdfDocument, PdfPage origPage) {
        try {
            return origPage.copyAsFormXObject(pdfDocument);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
