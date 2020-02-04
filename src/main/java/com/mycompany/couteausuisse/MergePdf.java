/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;


import com.itextpdf.kernel.pdf.PdfReader;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.pdf.*;
//import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergePdf {

//    public void mergePdf(String filePath1, String filePath2) throws DocumentException, IOException {
//
//        //crée la liste avec les documents a fusionner
//        List<URL> files = new ArrayList<URL>(Arrays.asList(
//                MergePdf.class.getResource(filePath1),
//                MergePdf.class.getResource(filePath2)
//        ));
//        fusion(files);
//
//    }
//
//    public static void fusion(List<URL> files) throws FileNotFoundException, DocumentException, IOException {
//        Document document = new Document();
//        PdfCopy copy = new PdfCopy(document, new FileOutputStream("result/merge-pdf-result.pdf"));
//
//        document.open();
//        for (URL file : files) {
//            System.out.println(file);
//            PdfReader reader = new PdfReader(file);
//            copy.addDocument(reader);
//            copy.freeReader(reader);
//            reader.close();
//        }
////        document.close();
//    }
}