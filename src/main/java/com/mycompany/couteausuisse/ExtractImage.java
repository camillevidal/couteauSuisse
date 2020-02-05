// $Id$
package com.mycompany.couteausuisse;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


/**
 * @author mkl
 */
public class ExtractImage
{
    public void writeImage() throws IOException{
        File folder = new File("D:/assets/pdf/input/");
        File[] listOfFiles = folder.listFiles();
        File file = listOfFiles[0];
        
        try (final PDDocument document = PDDocument.load(file)){
            PDPageTree list = document.getPages();
            System.out.println(list.getCount());
            int count = 0;
            for (PDPage page : list) {
                PDResources pdResources = page.getResources();
                int i = 1;
                for (COSName name : pdResources.getXObjectNames()) {
                    PDXObject o = pdResources.getXObject(name);
                    if (o instanceof PDImageXObject) {
                        PDImageXObject image = (PDImageXObject)o;
                        String filename = "D:/assets/pdf/output/extracted-image-" + count + ".png";
                        ImageIO.write(image.getImage(), "png", new File(filename));
                        i++; count++;
                    }
                }
            }

        } catch (IOException e){
            System.err.println("Exception while trying to create pdf document - " + e);
        }
    }
    }