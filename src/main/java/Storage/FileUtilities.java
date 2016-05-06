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
import java.io.BufferedReader;
import java.io.FileReader;
import Basics.Coordinates;




public class FileUtilities {

    private  ArrayList<Directlink> Links = new ArrayList<Directlink>();
    private  ArrayList<Cities> CitiesList = new ArrayList<Cities>();

    public FileUtilities() {

    }

    public void SaveLinks(String FileName, ArrayList Connections, boolean mode) throws FileNotFoundException, IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(FileName, mode));

        Links = Connections;

        for (Directlink link : Links) {

            pw.println(link.getFromName() + ' ' + link.getFromID() + ' ' + link.getToName() + ' ' + link.getToID());

            pw.close();
        }
    }
    
    public void SaveCities(String FileName, ArrayList Cities, boolean mode) throws FileNotFoundException, IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(FileName, mode));

        CitiesList = Cities;

        for (Cities City : CitiesList) {

            pw.println(City.getId()+' '+City.getName()+' '+City.getScore()+' '+City.getCoordinate().getX()+' '+City.getCoordinate().getY()+' '+City.getCoordinate().getType()+' '+City.getDistance());

            pw.close();
        }
    }
    
    
    
    
    
    public void LoadLinks(String Name) throws IOException {
	
	BufferedReader br = new BufferedReader(new FileReader(Name));
 
	String line = null;
	while ((line = br.readLine()) != null) {
		String[] lines=line.split("\\s+");
                Directlink templink=new Directlink(lines[0],Integer.parseInt(lines[1]),lines[2],Integer.parseInt(lines[3]));
                templink.Addlink(templink);            
	}
 
	br.close();
}
    
    
    
    public void LoadCities(String Name) throws IOException {
	
	BufferedReader br = new BufferedReader(new FileReader(Name));
 
	String line = null;
	while ((line = br.readLine()) != null) {
		String[] lines=line.split("\\s+");
                Coordinates tempCoordinates=new Coordinates(lines[6],Double.parseDouble(lines[4]),Double.parseDouble(lines[5]));
                Cities tempcity=new Cities(lines[0],lines[1],lines[2], tempCoordinates, lines[7]);
                tempcity.addCity(tempcity);            
	}
 
	br.close();
}
    
        
    
    
    
    
}
