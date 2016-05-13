/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

import java.util.ArrayList;

/**
 *
 * @author Ilianna
 */
public class Indirectlink {
    
    private String FromName, ToName;
    private int FromID, ToID;
    private ArrayList<String> BNames = new ArrayList<String>();
    private ArrayList<Integer> BIDs = new ArrayList<Integer>();
    
    private static ArrayList<Indirectlink> Links = new ArrayList<Indirectlink>();
    
    public Indirectlink(String name1, int id1, String name2,int id2, ArrayList<String> BNames, ArrayList<Integer> BIDs) {
        FromName=name1;
        FromID=id1;
        ToName=name2;
        ToID=id2;
        this.BNames = BNames;
        this.BIDs = BIDs;
    }
    
    public Indirectlink(){};
    
    public void Addlink(Indirectlink link){
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
    
    public ArrayList<String> getBetweenNames(){
        return BNames;
    }
    
    public ArrayList<Integer> getBetweenIDs(){
        return BIDs;
    }
    
    public ArrayList<String> getBetweenNames(int i){
        return Links.get(i).BNames;
    }
    
    public ArrayList<Integer> getBetweenIDs(int i){
        return Links.get(i).BIDs;
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
