/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Storage;

/**
 *
 * @author Cryowynd
 */
import Basics.Cities;
import Basics.Directlink;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList; 

public class FileUtilities {
    private  ArrayList<Directlink> Links=new ArrayList<Directlink>();
    private  ArrayList<Cities> citiesList = new ArrayList<Cities>();
    
    public void SaveLinks(String FileName, ArrayList Connections) throws FileNotFoundException {
          PrintWriter pw = new PrintWriter(new FileOutputStream(FileName));
          int i=0;
          Links=Connections;

        for (Directlink link : Links) {
            
            pw.println(link.getFromName(i));
            pw.println(link.getFromID(i));
            pw.println(link.getToName(i));
            pw.println(link.getToID(i));
            i++;
        }
         pw.close();
        
        
    }
}
