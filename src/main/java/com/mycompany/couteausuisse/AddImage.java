/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.couteausuisse;

import com.itextpdf.io.image.ImageData; 
import com.itextpdf.io.image.ImageDataFactory; 

import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Image; 
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author lucas
 */
@Named
@RequestScoped
@WebServlet(name = "AddImage", urlPatterns = {"/AddImage"})
public class AddImage {
    
    public AddImage(){
        
    }
        
    public void addimage() throws IOException{              
      
      // Creating a PdfWriter       
      String dest = "D:/Image/tp_pdf/addingImage.pdf";       
      PdfWriter writer = new PdfWriter(dest);        
      
      // Creating a PdfDocument       
      PdfDocument pdf = new PdfDocument(writer);              
      
      // Creating a Document        
      Document document = new Document(pdf);              
      
      // Creating an ImageData object       
      String imFile = "D:/Image/tp_pdf/stade.jpg";       
      ImageData data = ImageDataFactory.create(imFile);              
      
      // Creating an Image object        
      Image image = new Image(data);                        
      
      // Adding image to the document       
      document.add(image);              
      
      // Closing the document       
      document.close();              
      
      System.out.println("Image added");    
   } 
      
   
}
