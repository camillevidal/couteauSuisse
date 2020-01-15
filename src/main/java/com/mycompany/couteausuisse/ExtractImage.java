/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author camillevidal
 */
public class ExtractImage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        PdfReader reader;

        File file = new File("example.pdf");
        reader = new PdfReader(file.getAbsolutePath());
        for (int i = 0; i < reader.getXrefSize(); i++) {
            PdfObject pdfobj = reader.getPdfObject(i);
            if (pdfobj == null || !pdfobj.isStream()) {
                continue;
            }
            PdfStream stream = (PdfStream) pdfobj;
            PdfObject pdfsubtype = stream.get(PdfName.SUBTYPE);
            if (pdfsubtype != null && pdfsubtype.toString().equals(PdfName.IMAGE.toString())) {
                byte[] img = PdfReader.getStreamBytesRaw((PRStream) stream);
                FileOutputStream out = new FileOutputStream(new File(file.getParentFile(), String.format("%1$05d", i) + ".jpg"));
                out.write(img);
                out.flush();
                out.close();
            }
        }
    }

}
