/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/30911216/how-to-re-arrange-pages-in-pdf-using-itext
 */
package com.mycompany.couteausuisse;

import com.itextpdf.io.source.RandomAccessSourceFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.ReaderProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReorderPages {

    public static final String DEST = "pdf/test.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ReorderPages().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfReader reader;
        reader = new PdfReader(baos.toString());
        n = reader.getNumberOfPages();
        reader.selectPages(String.format("%d, 1-%d", n, n - 1));
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(filename));
        stamper.close();
    }
}
