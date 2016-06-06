/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ilianna
 */
public class Cities {

    //List with cities' information.
    private static ArrayList<Cities> citiesList = new ArrayList<Cities>();
    //Fields.
    private int id;
    private String name;
    private int score;
    private Coordinates coordinate;
    private int distance;

    //Cities Constructor.
    public Cities(String id, String name, String score, Coordinates coordinate, String distance) {

        if (id != null && !id.isEmpty()) {
            this.id = Integer.parseInt(id);
        } else {
            this.id = 0;
        }
        this.name = name;
        if (score != null && !score.isEmpty()) {
            this.score = Integer.parseInt(score);
        } else {
            this.score = 0;
        }

        this.coordinate = coordinate;
        if (distance != null && !distance.isEmpty()) {
            this.distance = Integer.parseInt(distance);
        } else {
            this.distance = 0;
        }
    }

    public Cities() {

    }

    //Getters and Setters.
    public void addCity(Cities city) {
        citiesList.add(city);

    }

    public ArrayList getCityList() {
        return citiesList;
    }

    public Cities getCity(String name) {
        for (int i = 0; i < citiesList.size(); i++) {
            if (name.equals(citiesList.get(i).name)) {
                return citiesList.get(i);
            }
        }
        return null;
    }

    public Cities getCity(int i) {
        if (i < citiesList.size() && i >= 0) {
            return citiesList.get(i);
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Coordinates getCoordinate() {
        return coordinate;
    }

    public int getDistance() {
        return distance;
    }

    public static ArrayList<String> getCitiesNames() {
        ArrayList<String> namesList = new ArrayList<String>();
        for (Cities city : citiesList) {
            namesList.add(city.getName());
        }
        return namesList;
    }

    
    public String getNameById(int id){
        String tempName="";
        for (Cities city:citiesList){
            if (id==city.getId()){
               tempName=city.getName();
            }
        }
        return tempName;
    }    
    
    public int getIdByName(String name){
        int id=0;
        for (Cities city:citiesList){
            if (name==city.getName()){
               id=city.getId();
            }
        }
        return id;
    }    
    
    public void sortCities(){
        Collections.sort(citiesList, new CitiesCompare());
        Collections.sort(citiesList, new CitiesCompare());
    }
    
    
}
