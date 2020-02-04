/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import com.itextpdf.kernel.PdfException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ExtractImage {

    public void extraireImg() throws IOException {
        File folder = new File("D:/Image/tp_pdf/input/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println(listOfFiles[i].getName());
                String extension = getFileExtension(listOfFiles[i]);
                if (extension.equals("pdf")) {
                    this.extractImageFromPdf("D:/Image/tp_pdf/export/", listOfFiles[i].getAbsolutePath());
                }
            }
        }
    }
                
                
    public String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    public void extractImageFromPdf(String DEST, String SRC) throws IOException {
        new File(DEST).getParentFile().mkdirs();
        new File(DEST).getParentFile().mkdirs();
        new ExtractImage().manipulatePdf(DEST, SRC);
    }
    //public static final String DEST = "./result/new";

    //public static final String SRC = "./result/image.pdf";
    protected void manipulatePdf(String dest, String SRC) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC));

        int numberOfPdfObjects = pdfDoc.getNumberOfPdfObjects();
        for (int i = 1; i <= numberOfPdfObjects; i++) {
            PdfObject obj = pdfDoc.getPdfObject(i);
            if (obj != null && obj.isStream()) {
                byte[] b;
                try {

                    // Get decoded stream bytes.
                    b = ((PdfStream) obj).getBytes();
                } catch (PdfException exc) {

                    // Get originally encoded stream bytes
                    b = ((PdfStream) obj).getBytes(false);
                }

                try (FileOutputStream fos = new FileOutputStream(String.format(dest + "/extract_streams%s.pdf", i))) {
                    fos.write(b);
                }
            }
        }

        pdfDoc.close();
    }
}
