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
    
    private ArrayList<String> BNames = new ArrayList<String>();
    private ArrayList<Integer> BIDs = new ArrayList<Integer>(); 
    private static ArrayList<Links> Links=new ArrayList<Links>();
    
    public Links(String name1, int id1, String name2,int id2) {
        FromName=name1;
        FromID=id1;
        ToName=name2;
        ToID=id2;
 
    }
    
    public Links(){};
    
    public void Addlink(Links link){
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
    
    
    
    public ArrayList getLinkByID(int id){
        ArrayList<Integer> toList=new ArrayList<Integer>();
        for (Links templink:Links){
            if (templink.getFromID()==id){
                toList.add(templink.getToID());
            }
        }
        
        
        
        
        return toList;
    }
    
    
    
}




