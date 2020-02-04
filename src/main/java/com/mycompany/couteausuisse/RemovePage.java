package com.mycompany.couteausuisse;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.FileNotFoundException;

public class RemovePage {

    //public static final String SRC = "pdf/test.pdf";
    //public static final String DEST = "result/changed.pdf";

    public static void remove(String DEST, String SRC,int pageToRemove) throws FileNotFoundException, IOException{
        PdfReader reader = new PdfReader(SRC);
        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument document = new PdfDocument(reader, writer);
        document.removePage(pageToRemove);
        document.close();
    }
}