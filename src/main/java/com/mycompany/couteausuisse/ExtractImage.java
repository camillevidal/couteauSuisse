/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import static com.mycompany.couteausuisse.FileDownloadView.deleteFolder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

@Named
@RequestScoped
public class ExtractImage {

    public void writeImage() throws IOException {
        deleteFolder(new File("D:/Image/tp_pdf/export/"));
        File folder = new File("D:/Image/tp_pdf/input/");
        File[] listOfFiles = folder.listFiles();
        File file = listOfFiles[0];
        try (final PDDocument document = PDDocument.load(file)) {
            PDPageTree list = document.getPages();
            int count = 0;
            for (PDPage page : list) {
                PDResources pdResources = page.getResources();
                int i = 1;
                for (COSName name : pdResources.getXObjectNames()) {
                    PDXObject o = pdResources.getXObject(name);
                    if (o instanceof PDImageXObject) {
                        PDImageXObject image = (PDImageXObject) o;
                        String filename = "D:/Image/tp_pdf/work/image" + count + ".png";
                        ImageIO.write(image.getImage(), "png", new File(filename));
                        i++;
                        count++;
                    }
                }
            }
            ZipFile();

        } catch (IOException e) {
            System.err.println("Exception while trying to create pdf document - " + e);
        }

    }

    public static void ZipFile() throws FileNotFoundException, IOException {
        File folder = new File("D:/Image/tp_pdf/work/");
        File[] listOfFiles = folder.listFiles();
        FileOutputStream fos = new FileOutputStream("D:/Image/tp_pdf/export/multiImagesCompressed.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (File srcFile : listOfFiles) {
            FileInputStream fis = new FileInputStream(srcFile);
            ZipEntry zipEntry = new ZipEntry(srcFile.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }

}
