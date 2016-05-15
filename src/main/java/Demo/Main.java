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
import java.util.ArrayList;
import java.io.IOException;
import Storage.FileUtilities;
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

//        JsonCityReader jcr = new JsonCityReader(wr.getCities());
       //Loading city information from the api.
  //     jcr.loadCitiesInfo("http://transport.opendata.ch/v1/locations?query=");
   //    Connections cncts= new Connections(wr.getCities());
     //  cncts.loadStationsInfo();
       FileUtilities fl=new FileUtilities();
       Cities city=new Cities();
       //Directlink link=new Directlink();
       
       fl.LoadCities("Cities.txt");
     //    fl.LoadLinks("Links.txt");
      //   fl.SaveCities("Cities.txt", city.getCityList(), true);
      // fl.SaveLinks("Links.txt", link.returnLinks(), true);
     
      
        Database dtbs=new Database();
        dtbs.Connect();
      
        try {      
        dtbs.writeCitiesToDB(city.getCityList());
      //  dtbs.writeLinksToDB(link.returnLinks());
        }catch (DBHasDataException e) {
           // dtbs.resetDatabase();
            e.printStackTrace();
        } 
     
        dtbs.readLinksFromDB();
        dtbs.closeConnection();
        
    }

}
