/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import static com.mycompany.couteausuisse.FileDownloadView.deleteFolder;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class MergePdf {

    public void mergePdf() throws DocumentException, IOException {
        deleteFolder(new File("D:/Image/tp_pdf/export/"));

        ArrayList<String> list_filepath = new ArrayList();

        //crée la liste avec les documents a fusionner
        File folder = new File("D:/Image/tp_pdf/input/");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            String extension = getFileExtension(listOfFiles[i]);
            if (listOfFiles[i].isFile() && extension.equals("pdf")) {
                list_filepath.add(listOfFiles[i].getAbsolutePath());
            }
        }
        this.fusion(list_filepath);
        
       
    }
    
    public void mergePdfWithPath(String path) throws DocumentException, IOException {
        deleteFolder(new File("D:/Image/tp_pdf/export/"));

        ArrayList<String> list_filepath = new ArrayList();

        //crée la liste avec les documents a fusionner
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            String extension = getFileExtension(listOfFiles[i]);
            if (listOfFiles[i].isFile() && extension.equals("pdf")) {
                list_filepath.add(listOfFiles[i].getAbsolutePath());
            }
        }
        this.fusion(list_filepath);
       
    }

    public static void fusion(ArrayList<String> list_of_files) throws FileNotFoundException, DocumentException, IOException {

        Document doc = new Document() {
        };
        PdfCopy copy = new PdfCopy(doc, new FileOutputStream("D:/Image/tp_pdf/export/merge-pdf-result.pdf"));

        doc.open();

        for (String file : list_of_files) {
            
            PdfReader reader = new PdfReader(file);
            copy.addDocument(reader);
            copy.freeReader(reader);
            reader.close();
        }
        doc.close();
        copy.close();
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}
