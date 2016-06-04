/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Ilianna
 */
public class JsonCityReader {

    //City names list.
    private ArrayList<String> cities = new ArrayList<String>();

    //Error check variables.
    private boolean flag = true;
    private int counter = 0;

    //Constructor.
    public JsonCityReader(ArrayList cities) {
        this.cities = cities;
    }

    //Loading information for every city from the api.
    public void loadCitiesInfo(String url) {
        System.out.println("Retrieving city information from http://transport.opendata.ch");
        for (int i = 0; i < cities.size(); i++) {
            jsonBreakDown(url + cities.get(i), i);
            if ((i % 26) == 0 && i != 0) {
                System.out.println((i * 100) / cities.size() + "% complete.");
            }
        }
        System.out.println("100% complete. \nData downloading complete.\n ");

    }

    //Connects to http://transport.opendata.ch and gets data.
    private void jsonBreakDown(String foo, int i) {

        try {

            String jString = IOUtils.toString(new URL(foo));
            JSONObject jObject = (JSONObject) JSONValue.parseWithException(jString);

            JSONArray stations = (JSONArray) jObject.get("stations");
            Iterator it = stations.iterator();
            jObject = (JSONObject) it.next();

            //Getting city information.
            String tempID = (String) jObject.get("id");
            String tempScore;
            if (jObject.get("score") != null) {
                tempScore = jObject.get("score").toString();
            } else {
                tempScore = "";
            }

            String tempDistance = (String) jObject.get("distance");
            String Coordinates = jObject.get("coordinate").toString();

            //Getting Coordinates.
            JSONObject coordinate = (JSONObject) JSONValue.parseWithException(Coordinates);
            String tempType = (String) coordinate.get("type");
            Double tempX = (Double) coordinate.get("x");
            Double tempY = (Double) coordinate.get("y");

            //Creating temporary city with the information.
            Coordinates tempCoordinates = new Coordinates(tempType, tempX, tempY);
            Cities tempCity = new Cities(tempID, cities.get(i), tempScore, tempCoordinates, tempDistance);

            //Adding city to the list.
            tempCity.addCity(tempCity);

            //Catching errors and retrying.
        } catch (IOException | ParseException e) {
            if (counter == 0) {
                flag = false;
                System.out.println("An error occurred while trying to get " + cities.get(i) + "'s information. \nRetrying...");
            }
            if (counter < 5) {
                counter++;
                jsonBreakDown(foo, i);
                //If retrying fails more than four times, the application stops.
            } else {
                System.out.println("There was an issue with retrieving " + cities.get(i) + "'s data.\nPlease restart the application and try again.");
                System.exit(1);
            }
        }
        if (!flag) {
            System.out.println("The error has been resolved. Continuing to the next city.");
            counter = 0;
            flag = true;
        }

    }
}
