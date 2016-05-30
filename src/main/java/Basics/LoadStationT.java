/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 *
 * @author it21447
 */
public class LoadStationT extends Thread{
     private String url1 = "http://transport.opendata.ch/v1/connections?from=";
    private String url2 = "&to=";
    private String url3 = "&direct=1";
    int counter;
    int c1;
    int c2;
    static ArrayList<Cities> cities;
    boolean flag;
    
    public LoadStationT(ArrayList<Cities> cities){
        this.cities=cities;
        
        
    }
    
    
    public LoadStationT(int c1){
    this.c1=c1;
    }
    
  public void run() {

      
      System.out.println("Retrieving Station information");
        
        for (c2 = 0; c2 < cities.size(); c2++) {
            
            linkConfirm(url1 + cities.get(c1).getId() + url2 + cities.get(c2).getId() + url3);
        }
  //      System.out.println("Finished getting " + cities.get(c1).getName() + "'s connections.");
        
        System.out.println("100% complete. \nData downloading complete.\n ");
      
      
}
  
 
  
public void loop (int c1) {
    for ( c1=0;c1<cities.size();c1++) { 
(new LoadStationT(c1)).start();
}   
}
    
    


private void linkConfirm(String foo) {

        try {

            String jString = IOUtils.toString(new URL(foo));
            JSONObject jObject = (JSONObject) JSONValue.parseWithException(jString);
            JSONArray Connections = (JSONArray) jObject.get("connections");
            Iterator it = Connections.iterator();
            if (it.hasNext()) {
                Links templink = new Links(cities.get(c1).getName(), cities.get(c1).getId(), cities.get(c2).getName(), cities.get(c2).getId());
                templink.Addlink(templink);

            }

            //Catching errors and retrying.
        } catch (IOException | ParseException e) {
            if (counter == 0) {
                flag = false;
                System.out.println("An error occurred while trying to get " + cities.get(c1).getName() + "'s information. \nRetrying...");
            }
            //    if(counter<10){  
            counter++;
            linkConfirm(foo);
            //If retrying fails more than four times, the application stops.
            //   }else{
            //     System.out.println("There was an issue with retrieving "+cities.get(c1).getName()+"'s data.\nPlease restart the application and try again.");
            //   System.exit(1);
            //}
        }
        if (!flag) {
            System.out.println("The error has been resolved. Continuing to the next Station.");
            counter = 0;
            flag = true;
        }

    }

}