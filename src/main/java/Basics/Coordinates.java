/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

/**
 *
 * @author Ilianna
 */
public class Coordinates {

    //Fields.
    private String type;
    private Double x;
    private Double y;

    public String getType() {
        return type;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    //Constructor.
    public Coordinates(String type, Double x, Double y) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Coordinates(String type, String x, String y) {
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.type = type;
    }

}
