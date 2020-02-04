
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
    public static final String DEST = "./result/new";
 
    public static final String SRC = "./result/image.pdf";
 
    public static void before() {
        new File(DEST).getParentFile().mkdirs();
    }
 
    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.mkdirs();
 
        new ExtractImage().manipulatePdf(DEST);
    }
 
    protected void manipulatePdf(String dest) throws IOException {
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