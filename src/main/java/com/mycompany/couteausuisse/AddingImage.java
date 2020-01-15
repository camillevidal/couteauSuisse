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

public class AddingImage {      
   public static void main(String args[]) throws Exception {              
      
      // Creating a PdfWriter       
      String dest = "result/image.pdf";       
      PdfWriter writer = new PdfWriter(dest);        
      
      // Creating a PdfDocument       
      PdfDocument pdf = new PdfDocument(writer);              
      
      // Creating a Document        
      Document document = new Document(pdf);              
      
      // Creating an ImageData object       
      String imFile = "images/chat1.jpg";       
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
