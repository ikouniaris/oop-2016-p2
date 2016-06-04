package Basics;

import java.util.ArrayList;

/**
 *
 * @author Cryowynd
 */
public class Connections {

    private int c1;
    //city names list.
    private ArrayList<Cities> cities = new ArrayList<Cities>();

    //Constructor.
    public Connections(ArrayList cities) {
        this.cities = cities;
    }

    //Main process
    public void loadStationsInfo() {

        ArrayList<Thread> threads = new ArrayList<Thread>();
        //Starting 3 original threads
        for (c1 = 0; c1 < 3; c1++) {
            threads.add(new LoadStationThread(c1));
            threads.get(c1).start();
        }
        //Until all cities are checked, a new thread starts when one finishes.
        for (int i = 0; i < cities.size(); i++) {
            try {
                threads.get(i).join();
                if (c1 < cities.size()) {
                    threads.add(new LoadStationThread(c1));
                    threads.get(c1++).start();
                }
                System.out.println("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threads.clear();

    }
}
