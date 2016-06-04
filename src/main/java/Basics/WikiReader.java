/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ilianna
 */
public class WikiReader {

    private int counter = 0;
    private boolean flag = true;

    //List with city names.
    private ArrayList<String> cities = new ArrayList<String>();

    //Returns the city names' list.
    public ArrayList getCities() {
        return cities;
    }

    public void loadCityNames(String url) throws IOException {
        if (counter == 0) {
            System.out.println("Fetching " + url);

        }
        try {

            //Getting html code.
            Document doc = Jsoup.connect(url).get();

            //Getting the tables from the page.
            Elements tables = doc.select("table");
            Elements links = null;
            int i = 0;

            for (Element table : tables) {
                if (i == 1) {
                    links = table.select("td");
                    break;
                }
                i++;
            }
            tables = null;

            //Selecting city name text.
            for (Element link : links) {
                if (i == 1) {
                    i = 7;
                    cities.add(link.text().replaceAll("[^A-Za-z-]", ""));
                }
                i--;
            }
            System.out.println("Finished downloading the list of city names from " + url + "\n");

            //Catching errors and retrying.
        } catch (IOException e) {

            if (counter == 0) {
                flag = false;
                System.out.println("An error occurred while trying to download the list of cities. \nRetrying...");
            }
            if (counter < 4) {
                counter++;
                loadCityNames(url);
                //If retrying fails more than three times, the application stops.
            } else {
                System.out.println("There was an issue with retrieving the list of cities.\nPlease restart the application and try again.");
                System.exit(1);
            }
        }
        if (!flag) {
            System.out.println("The error has been resolved.");
            counter = 0;
            flag = true;
        }

    }
}
