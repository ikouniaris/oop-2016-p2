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
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList; 
import java.io.IOException;

public class FileUtilities {
    private  ArrayList<Directlink> Links=new ArrayList<Directlink>();
    private  ArrayList<Cities> citiesList = new ArrayList<Cities>();
    
    public void SaveLinks(String FileName, ArrayList Connections) throws FileNotFoundException {
          PrintWriter pw = new PrintWriter(new FileWriter(FileName, true));
          
          Links=Connections;

        for (Directlink link : Links) {
            
            pw.println(link.getFromName()+'|'+link.getFromID()+'|'+link.getToName()+'|'+link.getToID());
     
        }
         pw.close();
              
    }
    
    
   
    
    
    
    
}
