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
public class Directlink {
    private String FromName, ToName;
    private int FromID, ToID;
    
    private static ArrayList<Directlink> Links=new ArrayList<Directlink>();
    
    public Directlink(String name1, int id1, String name2,int id2) {
        FromName=name1;
        FromID=id1;
        ToName=name2;
        ToID=id2;
    }
    
    public Directlink(){};
    
    public void Addlink(Directlink link){
        Links.add(link);
    }
    
    public ArrayList returnLinks() {
        return Links;
    }
    
    public String getFromName(){
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
    
    public String getFromName(int i){
        return Links.get(i).getFromName();
    }

    public String getToName(int i) {
        return Links.get(i).getToName();
    }

    public int getFromID(int i) {
        return Links.get(i).getFromID();
    }

    public int getToID(int i) {
        return Links.get(i).getToID();
    }
    
    
}
