/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Cryowynd
 */
public class Connections {
    
    private String url1="http://transport.opendata.ch/v1/connections?from=";
    private String url2="&to=";
    private String url3="&direct=1";
    
    private int c1,c2;
    //city names list.
    private ArrayList<String> cities = new ArrayList<String>();
    

    
    
    
    //Error check variables.
    private boolean flag = true;
    private int counter=0;
    
    //Constructor.
    public Connections(ArrayList cities){
        this.cities = cities;
    }
    
    //Loading information for every Station from the api.
    public void loadStationsInfo(){
       System.out.println("Retrieving Station information");
        for ( c1=0;c1<cities.size();c1++) {
            for (c2=0;c2<cities.size();c2++){
            jsonBreakDown(url1+cities.get(c1)+url2+cities.get(c2)+url3);
            }
           System.out.println("Finished getting "+cities.get(c1)+"'s connections.");
        }
        System.out.println("100% complete. \nData downloading complete.\n ");
        Directlink testlink=new Directlink();
        ArrayList testlist=testlink.returnLinks();
        System.out.println('1');
    }   
    
    
    private void jsonBreakDown(String foo){
        
        try {
         
         String jString = IOUtils.toString(new URL(foo));
         JSONObject jObject = (JSONObject) JSONValue.parseWithException(jString);
         JSONArray Connections= (JSONArray) jObject.get("connections");
         Iterator it = Connections.iterator();
         if (it.hasNext()){
             
             JSONObject tempFrom = (JSONObject) jObject.get("from");
             String tempname1=(String) tempFrom.get("name");
             int Id1 = Integer.parseInt(tempFrom.get("id").toString());
             
             JSONObject tempTo = (JSONObject) jObject.get("to");
             String tempname2=(String) tempTo.get("name");
             int Id2 = Integer.parseInt(tempFrom.get("id").toString());
             
             Directlink templink= new Directlink(tempname1,Id1,tempname2,Id2);
             templink.Addlink(templink);
             System.out.println(c1+' '+c2);
         }
         
         
            
            
            
            
            
            
            
            
            
            
            
            
                      

            //Catching errors and retrying.
        }catch(IOException | ParseException e){
            if(counter==0){
                flag=false;
                System.out.println("An error occurred while trying to get "+cities.get(c1)+"'s information. \nRetrying...");
            }
            if(counter<5){  
                counter++;
                jsonBreakDown(foo);
            //If retrying fails more than four times, the application stops.
            }else{
                System.out.println("There was an issue with retrieving "+cities.get(c1)+"'s data.\nPlease restart the application and try again.");
                System.exit(1);
            }
        }
        if(!flag){
            System.out.println("The error has been resolved. Continuing to the next Station.");
            counter=0;
            flag=true;
        }
        
    }
    
  /*  
    public ArrayList getfrom(){
        return FromLists;
    }
    
    public ArrayList getto(){
        return ToLists;
    }
*/
}

