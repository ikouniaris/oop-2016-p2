/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

import java.util.ArrayList;

/**
 *
 * @author Cryowynd
 */
public class Links {

    private String FromName, ToName;
    private int FromID, ToID;

    private static volatile ArrayList<Links> Links = new ArrayList<Links>();

    public Links(String name1, int id1, String name2, int id2) {
        FromName = name1;
        FromID = id1;
        ToName = name2;
        ToID = id2;

    }

    public Links() {
    }

    ;
    
    public void Addlink(Links link) {
        Links.add(link);
    }

    public ArrayList returnLinks() {
        return Links;
    }

    public String getFromName() {
        return FromName;
    }

    public String getToName() {
        return ToName;
    }

    public int getFromID() {
        return FromID;
    }

    public int getToID() {
        return ToID;
    }

}
