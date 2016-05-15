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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class IndirectCon {
    private int toID;
    private int fromID;
    private ArrayList<Directlink> xoxo;
    private ArrayList<Integer> temp = new ArrayList<Integer>();
    private static ArrayList<node> nodeList = new ArrayList<node>();
    
    public IndirectCon(int fromID, int toID, ArrayList<Directlink> xoxo){
        this.fromID = fromID;
        this.toID = toID;
        this.xoxo = xoxo;
    }
    
    public void findIndLinks(int fromID){
        
        temp = xoxo.getIDs(fromID);
       
        for(int i=0;i<temp.size();i++){
            if(repeat(temp.get(i),)){
                //deletes chain of node from list
                continue;
            }
            if(toID==temp.get(i)){
                //sets check of nodelist to true
            }else if(/*checks from node chain if it's false*/){
                findIndLinks(temp.get(i));
            }
        }
        
        
        
        
    }
    
    private boolean reapeat(int id, ){
        return true;
    }
        

    
}
