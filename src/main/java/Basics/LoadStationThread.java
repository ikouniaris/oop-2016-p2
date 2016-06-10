/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

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
public class LoadStationThread extends Thread {

    private String url1 = "http://transport.opendata.ch/v1/connections?from=";
    private String url2 = "&to=";
    private String url3 = "&direct=1";
    int counter;
    int c1;
    int c2;
    private static Cities city = new Cities();
    boolean flag;
    static volatile boolean rateReached = false;

    public LoadStationThread(int c1) {
        this.c1 = c1;
    }

    public LoadStationThread() {
    }

    public void run() {

        System.out.println("Retrieving Station information");

        for (c2 = 0; c2 < city.getCityList().size(); c2++) {
            if (!rateReached) {
                linkConfirm(url1 + city.getCity(c1).getId() + url2 + city.getCity(c2).getId() + url3);
                System.out.println(c2);
            } else {
                c2--;
            }
        }

        System.out.println("Thread number " + c1 + " finished");

    }

    private void linkConfirm(String foo) {

        try {

            String jString = IOUtils.toString(new URL(foo));
            JSONObject jObject = (JSONObject) JSONValue.parseWithException(jString);
            JSONArray Connections = (JSONArray) jObject.get("connections");
            Iterator it = Connections.iterator();
            if (it.hasNext()) {
                Links templink = new Links(city.getCity(c1).getName(), city.getCity(c1).getId(), city.getCity(c2).getName(), city.getCity(c2).getId());
                templink.Addlink(templink);

            }

            //Catching errors and retrying.
        } catch (IOException | ParseException e) {
            if (counter == 0) {
                flag = false;
                if (!rateReached) {
                    rateReached = true;
                }
                System.out.println("An error occurred while trying to get " + city.getCity(c1).getName() + "'s information. \nHalting threads...");
                long time = System.currentTimeMillis();
                long timeLeft = 60000 - time % 60000;
                try {
                    Thread.sleep(timeLeft);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                if (rateReached == true) {
                    rateReached = false;
                    System.out.println("Resuming");
                }
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

            counter = 0;
            flag = true;
        }

    }

}
