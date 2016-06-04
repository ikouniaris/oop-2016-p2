/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo;

import Storage.DBHasDataException;
import Basics.Cities;
import Basics.Links;
import Basics.Connections;
import Basics.JsonCityReader;
import Basics.WikiReader;
import Storage.Database;
import java.io.IOException;
import Storage.FileUtilities;
import Gui.JWindow;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author Ilianna
 */
//Main Class.
public class Main {

    public static void main(String[] args) throws IOException {

        //  WikiReader wr = new WikiReader();
        //  Loading city names from wikipedia.
        // wr.loadCityNames("https://en.wikipedia.org/wiki/List_of_cities_in_Switzerland");
        // JsonCityReader jcr = new JsonCityReader(wr.getCities());
        // Loading city information from the api.
        //jcr.loadCitiesInfo("http://transport.opendata.ch/v1/locations?query=");
        //Finds Connections between the cities from the api
        Cities city = new Cities();
        //Connections cncts = new Connections(city.getCitiesNames());
        //cncts.loadStationsInfo();

        FileUtilities fl = new FileUtilities();

        Links link = new Links();

        //Loading information from files
        fl.LoadCities("Cities.txt");
        fl.LoadLinks("Links.txt");
        //Saving information to files
        //  fl.SaveCities("Cities.txt", city.getCityList(), false);
        //fl.SaveLinks("Links.txt", link.returnLinks(), false);
/*
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println(sdf.format(cal.getTime()));
         */
        //Database managment
        Database dtbs = new Database();
        dtbs.Connect();

        //Writting data to database
        try {
            //     dtbs.createDB();
            //  dtbs.resetDatabase();
            //    dtbs.writeCitiesToDB(city.getCityList());
            dtbs.writeLinksToDB(link.returnLinks());
        } catch (DBHasDataException e) {
            //if database has data, resets it
            //     dtbs.resetDatabase();
            e.printStackTrace();
        }

        //Reads data from databases.
        //     dtbs.readCitiesFromDB();
        //   dtbs.readLinksFromDB();
        //Closes the connection
        dtbs.closeConnection();

        //      JWindow j = new JWindow();
        //    j.test();
    }

}
