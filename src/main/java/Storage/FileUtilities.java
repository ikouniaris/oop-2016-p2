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
import Basics.Links;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import Basics.Coordinates;

public class FileUtilities {

    private ArrayList<Links> Links = new ArrayList<Links>();
    
//Blank constructor

    public FileUtilities() {

    }
//Saving links between cities to file

    public void SaveLinks(String FileName, ArrayList<Links> Connections, boolean mode) throws FileNotFoundException, IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(FileName, mode));



        for (Links link : Connections) {

            pw.println(link.getFromName() + " " + link.getFromID() + " " + link.getToName() + " " + link.getToID());

        }
        pw.close();
    }

    //Saving cities to file
    public void SaveCities(String FileName, ArrayList<Cities> Cities, boolean mode) throws FileNotFoundException, IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(FileName, mode));

        

        for (Cities City : Cities) {

            pw.println(City.getId() + " " + City.getName() + " " + City.getScore() + " " + City.getCoordinate().getX() + " " + City.getCoordinate().getY() + " " + City.getCoordinate().getType() + " " + City.getDistance());

        }
        pw.close();
    }

    //Loading Connections from files
    public void LoadLinks(String Name) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(Name));

        String line = null;
        while ((line = br.readLine()) != null) {
            String[] lines = line.split("\\s+");
            Links templink = new Links(lines[0], Integer.parseInt(lines[1]), lines[2], Integer.parseInt(lines[3]));
            templink.Addlink(templink);
        }

        br.close();
    }

    //Loading Cities from list
    public void LoadCities(String Name) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(Name));

        String line = null;
        while ((line = br.readLine()) != null) {
            String[] lines = line.split("\\s+");
            Coordinates tempCoordinates = new Coordinates(lines[5], Double.parseDouble(lines[3]), Double.parseDouble(lines[4]));
            Cities tempcity = new Cities(lines[0], lines[1], lines[2], tempCoordinates, lines[6]);
            tempcity.addCity(tempcity);
        }
        br.close();
    }

}
