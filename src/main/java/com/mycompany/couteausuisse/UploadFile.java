/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
//import org.primefaces.model.UploadedFile;

/**
 *
 * @author lucas
 */


@Named
@RequestScoped
public class UploadFile {
    
    private UploadedFile file;
 
    public UploadedFile getFile() {
        System.out.println(file);
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
    public void upload() throws IOException {
        if (file != null && file.getSize() > 0) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            InputStream fileContent = file.getInputstream();
            byte[] buffer = new byte[fileContent.available()];
            fileContent.read(buffer);
            
            File targetFile = new File("D:/Image/java/" + file.getFileName());
            FileOutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
