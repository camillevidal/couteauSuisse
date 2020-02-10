/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import static com.mycompany.couteausuisse.FileDownloadView.deleteFolder;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author lucas
 */
public class ExplosePdf {
    public void diviserPdf() throws java.io.IOException{
        //Loading an existing PDF document
        deleteFolder(new File("D:/Image/tp_pdf/work/"));
        File folder = new File("D:/Image/tp_pdf/input/");
        File[] listOfFiles = folder.listFiles();
        File file = listOfFiles[0];
        //Instantiating Splitter class
        try (PDDocument document = PDDocument.load(file)) {
            //Instantiating Splitter class
            Splitter splitter = new Splitter();

            //splitting the pages of a PDF document
            List<PDDocument> Pages = splitter.split(document);

            //Creating an iterator
            Iterator<PDDocument> iterator = Pages.listIterator();

            //Saving each page as an individual document
            int i = 1;
            while(iterator.hasNext()) {
                PDDocument pd = iterator.next();
                pd.save("D:/Image/tp_pdf/work/file"+ i++ +".pdf");
            }
            System.out.println("Multiple PDF’s created");
            document.close();
        }
    }
}
