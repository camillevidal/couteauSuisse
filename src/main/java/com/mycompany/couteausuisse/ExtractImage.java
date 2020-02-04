
package com.mycompany.couteausuisse;
 
import com.itextpdf.kernel.PdfException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class ExtractImage {
    
    public void extractImageFromPdf(String DEST,String SRC) throws IOException{
        new File(DEST).getParentFile().mkdirs();
        new File(DEST).getParentFile().mkdirs();
        new ExtractImage().manipulatePdf(DEST,SRC);
    }
    //public static final String DEST = "./result/new";
 
    //public static final String SRC = "./result/image.pdf";
 
   
 
  
 
    protected void manipulatePdf(String dest,String SRC) throws IOException {
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