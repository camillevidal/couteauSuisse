/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import com.itextpdf.text.Document;
//import org.dom4j.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import static com.mycompany.couteausuisse.FileDownloadView.deleteFolder;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class ExtractPage {
    public void extractPaceFromPdf(String saisie) throws FileNotFoundException, IOException, DocumentException {
        deleteFolder(new File("D:/Image/tp_pdf/export/"));
        ExplosePdf b = new ExplosePdf();
        b.diviserPdf();
        String[] burst = null;
        ArrayList a = new ArrayList<String>();
        
        if(saisie != null){
            burst = saisie.split(";");
        }
        
        for(String element : burst){
            a.add(Integer.parseInt(element));
        }
        
        Collections.sort(a, Collections.reverseOrder());
        
        File folder = new File("D:/Image/tp_pdf/work");
        File[] listOfFiles = folder.listFiles();
        
        for (File e : listOfFiles){
            System.out.println("test1 " + e.getAbsolutePath());        
        }
        
        ArrayList tempList = new ArrayList<File>();
        for(int i=0; i < a.size(); i++){
            System.out.println("test2 " + listOfFiles[((int)a.get(i))-1]);
            tempList.add(listOfFiles[((int)a.get(i))-1]);               
        }
        
        for(File f : listOfFiles){
            if(!tempList.contains(f)){
                f.delete();
            }
        }
        MergePdf m = new MergePdf();
        m.mergePdfWithPath("D:/Image/tp_pdf/work/");
    }
}
