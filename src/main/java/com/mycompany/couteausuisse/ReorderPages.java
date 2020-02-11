/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import static com.mycompany.couteausuisse.FileDownloadView.deleteFolder;
 
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
 
@Named
@RequestScoped
public class ReorderPages {
    
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    public void reorderPages() {
        deleteFolder(new File("D:/Image/tp_pdf/export/"));
        File folder = new File("D:/Image/tp_pdf/input/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String extension = getFileExtension(listOfFiles[i]);
                if (extension.equals("pdf")) {
                    try {
                        reorder(listOfFiles[i].getAbsolutePath());
                        break;
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }
 
    public void reorder(String saise) throws Exception {
        deleteFolder(new File("D:/Image/tp_pdf/export/"));
        ExplosePdf b = new ExplosePdf();
        b.diviserPdf();
        String[] burst = null;
        ArrayList a = new ArrayList<String>();
        
        if(saise != null){
            burst = saise.split(";");
        }
        
        for(String element : burst){
            a.add(Integer.parseInt(element));
        }
        Collections.sort(a, Collections.reverseOrder());
        
        File folder = new File("D:/Image/tp_pdf/work");
        File[] listOfFiles = folder.listFiles();
        
        ArrayList tempList = new ArrayList<File>();
        for(int i =0; i<burst.length; i++)
            for (File e : listOfFiles){
                if(String.valueOf(e.getName()).equals("file"+burst[i]+".pdf")){
                    tempList.add(e.getAbsolutePath());
                }
        }
        
        MergePdf m = new MergePdf();
        m.fusion(tempList);
    }
}