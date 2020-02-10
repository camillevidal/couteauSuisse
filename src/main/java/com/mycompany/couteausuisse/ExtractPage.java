package com.mycompany.couteausuisse;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExtractPage {
//extraction d'une page d'un

    public void extractPageFromPdf() throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("result/fichierSortie.pdf"));
        PdfReader pdfr = new PdfReader("pdf/test.pdf");
        PdfReader pdfr2 = new PdfReader("pdf/test1.pdf");
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        for (int i = 1; i <= pdfr.getNumberOfPages(); i++) {

            document.newPage();
            cb.addTemplate(writer.getImportedPage(pdfr, i), 0, 0);
        }
        for (int i = 1; i <= pdfr2.getNumberOfPages(); i++) {
            document.newPage();
            cb.addTemplate(writer.getImportedPage(pdfr2, i), 0, 0);
        }
        document.close();

    }
}
