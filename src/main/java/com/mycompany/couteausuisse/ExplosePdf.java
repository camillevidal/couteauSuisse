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
        deleteFolder(new File("D:/Image/tp_pdf/work/"));
        File folder = new File("D:/Image/tp_pdf/input/");
        File[] listOfFiles = folder.listFiles();
        File file = listOfFiles[0];
        try (PDDocument document = PDDocument.load(file)) {
            //spliter pages du pdf
            Splitter splitter = new Splitter();
            List<PDDocument> Pages = splitter.split(document);

            Iterator<PDDocument> iterator = Pages.listIterator();

            // creer un nouveau document pour chaque page
            int i = 1;
            while(iterator.hasNext()) {
                PDDocument pdd = iterator.next();
                pdd.save("D:/Image/tp_pdf/work/file"+ i++ +".pdf");
            }
            document.close();
        }
    }
}
