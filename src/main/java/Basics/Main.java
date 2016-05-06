/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

import java.io.IOException;

/**
 *
 * @author Ilianna
 */

//Main Class.
public class Main {

    public static void main(String[] args) throws IOException {

        WikiReader wr = new WikiReader();
        //Loading city names from wikipedia.
        wr.loadCityNames("https://en.wikipedia.org/wiki/List_of_cities_in_Switzerland");

        JsonCityReader jcr = new JsonCityReader(wr.getCities());
        //Loading city information from the api.
        jcr.loadCitiesInfo("http://transport.opendata.ch/v1/locations?query=");

    }

}
