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

    private static ArrayList<Links> Links = new ArrayList<Links>();

    public Links(String name1, int id1, String name2, int id2) {
        FromName = name1;
        FromID = id1;
        ToName = name2;
        ToID = id2;

    }

    public Links() {
    }
    
    public void Addlink(Links link) {
        Links.add(link);
    }

    public ArrayList getLinks() {
        return Links;
    }
    
    public boolean dlinked(String from, String to){
        for(int i=0;i<Links.size();i++){
            
            if(from.equals(Links.get(i).getFromName()) && to.equals(Links.get(i).getToName())){
                return true;
            }
        }
        return false;
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
