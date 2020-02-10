/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import static com.mycompany.couteausuisse.FileDownloadView.deleteFolder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author lucas
 */
@Named
@RequestScoped
public class AddImage {

    public AddImage() {}

    public void addImgInPdf() throws FileNotFoundException, MalformedURLException, IOException {
        // Creating a PdfWriter     
        deleteFolder(new File("D:/Image/tp_pdf/export/"));
        String dest = "D:/Image/tp_pdf/export/addingImage.pdf";
        PdfWriter writer = new PdfWriter(dest);

        // Creating a PdfDocument       
        PdfDocument pdf = new PdfDocument(writer);

        // Creating a Document        
        Document document = new Document(pdf);
        File folder = new File("D:/Image/tp_pdf/input/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String imFile = listOfFiles[i].getAbsolutePath();
                ImageData data = ImageDataFactory.create(imFile);

                // Creating an Image object        
                Image image = new Image(data);

                // Adding image to the document       
                document.add(image);
            }
        }
        document.close();
        pdf.close();
        writer.close();
    }

    public void deleteAllFiles(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
    }

}
