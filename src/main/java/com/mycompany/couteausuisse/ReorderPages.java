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
 
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
 
@Named
@RequestScoped
public class ReorderPages {
    //public static final String fileToReord = "result/fichierSortie.pdf";
   
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
                System.out.println(listOfFiles[i].getName());
                String extension = getFileExtension(listOfFiles[i]);
                if (extension.equals("pdf")) {
                    try {
                        System.out.println("uuuuuuui");
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
        System.out.println("burst_____" + Arrays.toString(burst));
        System.out.println("burst_____" + burst[1]);
        
        
        for(String element : burst){
            a.add(Integer.parseInt(element));
        }
        Collections.sort(a, Collections.reverseOrder());
        
        File folder = new File("D:/Image/tp_pdf/work");
        File[] listOfFiles = folder.listFiles();
        
//        int i = 0;
        ArrayList tempList = new ArrayList<File>();
        System.out.println("len "+ burst.length);
        for(int i =0; i<burst.length; i++)
            for (File e : listOfFiles){
                System.out.println(e.getName().getClass().getName());
                System.out.println("----"+ "file"+burst[i]+".pdf");
                if(String.valueOf(e.getName()).equals("file"+burst[i]+".pdf")){
                    System.out.println("=====================================");
                    tempList.add(e.getAbsolutePath());
                }
                System.out.println("test1 " + e.getAbsolutePath()); 
        }
        System.out.println(tempList);
        
//        ArrayList tempList = new ArrayList<File>();
//        for(int i=0; i < a.size(); i++){
//            System.out.println("test2 " + listOfFiles[((int)a.get(i))-1]);
//            tempList.add(listOfFiles[((int)a.get(i))-1]);               
//        }
        
//        for(File f : listOfFiles){
//            if(!tempList.contains(f)){
//                f.delete();
//            }
//        }
        MergePdf m = new MergePdf();
        m.fusion(tempList);
//        m.mergePdfWithPath("D:/Image/tp_pdf/work/");
    }
}