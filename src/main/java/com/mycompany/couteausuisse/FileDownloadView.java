/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@RequestScoped
public class FileDownloadView {

    private StreamedContent file;

    public FileDownloadView() {
        InputStream stream = null;
        File folder = new File("D:/Image/tp_pdf/export/");
        File[] listOfFiles = folder.listFiles();
        try {
            stream = new FileInputStream(listOfFiles[0].getAbsolutePath());
            this.file = new DefaultStreamedContent(stream, "application/pdf", "downloaded_file.pdf");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileDownloadView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(FileDownloadView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        deleteFolder(new File("D:/Image/tp_pdf/export/"));
    }

    public StreamedContent getFile() throws IOException {
        InputStream stream = null;
        File folder = new File("D:/Image/tp_pdf/export/");
        File[] listOfFiles = folder.listFiles();
        String fileName = listOfFiles[0].getName();
        try {
            stream = new FileInputStream(listOfFiles[0].getAbsolutePath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileDownloadView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.file = new DefaultStreamedContent(stream, "application/pdf", fileName);
        deleteFolder(new File("D:/Image/tp_pdf/input/"));
        deleteFolder(new File("D:/Image/tp_pdf/work/"));
        
        return this.file;
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                f.delete();
            }
        }
    }

}
