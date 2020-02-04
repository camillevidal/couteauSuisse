/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
//import FilenameUtils;

@Named
@RequestScoped
public class RemovePage {

    //public static final String SRC = "pdf/test.pdf";
    //public static final String DEST = "result/changed.pdf";
    public static void remove(String DEST, String SRC, int pageToRemove) throws FileNotFoundException, IOException {
        PdfReader reader = new PdfReader(SRC);
        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument document = new PdfDocument(reader, writer);
        document.removePage(pageToRemove);
        document.close();
    }

    public void remove_page(String pageToRemove) {
        int page = Integer.parseInt(pageToRemove);
        File folder = new File("D:/Image/tp_pdf/input/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            System.out.println(listOfFiles[0]);
            if (listOfFiles[i].isFile()) {
                System.out.println(listOfFiles[i].getName());
                String extension = getFileExtension(listOfFiles[i]);
                if (extension.equals("pdf")) {
                    try {
                        remove("D:/Image/tp_pdf/export/remove_page.pdf", listOfFiles[i].getAbsolutePath(), page);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
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
