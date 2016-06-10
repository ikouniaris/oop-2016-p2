/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Cryowynd
 */
import java.util.Collections;
public class Links {

    private String FromName, ToName;
    private int FromID, ToID;
    
    private LinkedList<Node> encounteredList = new LinkedList<Node>();
    private static ArrayList<LinkedList> ListOfLists = new ArrayList<LinkedList>();
    private static boolean check = false;
    private boolean xxx;
    private static int counter;

    private static volatile ArrayList<Links> Links = new ArrayList<Links>();

    public Links(String name1, int id1, String name2, int id2) {
        FromName = name1;
        FromID = id1;
        ToName = name2;
        ToID = id2;
    }

    public Links(int id1, int id2, LinkedList nodelist) {
        FromName = getNameById(id1);
        FromID = id1;
        ToName = getNameById(id2);
        ToID = id2;
    }

    public Links() {
    }
    
    public ArrayList<String> getNameLinks(String fromName){
        ArrayList<String> block = new ArrayList<String>();
        int i=0;
        
        for(i=0;i<Links.size();i++){
            if(fromName.equals(Links.get(i).FromName)){
                while(fromName.equals(Links.get(i).FromName)){
                    block.add(Links.get(i).ToName);
                }
                break;
            }
        }
        return block;
    }

    public boolean dlinked(String from, String to){
        for(int i=0;i<Links.size();i++){
            if(from.equals(Links.get(i).getFromName()) && to.equals(Links.get(i).getToName())){
                return true;
            }
        }
        return false;
    }
    
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

    public String getNameById(int id) {
        Cities tempCity = new Cities();
        String tempName = tempCity.getNameById(id);
        return tempName;
    }

    /*public boolean IsDirect() {
        if(.isEmpty()){
            return true;
        }
        return true;
    }
*/
    public Links getLinkByIndex(int i) {
        return Links.get(i);
    }

    public int getListSize() {
        return Links.size();
    }

    
    public void sortLinks(){
        Collections.sort(Links, new LinksCompareTo());
        Collections.sort(Links, new LinksCompareFrom());
    }
    
    public LinkedList findIndLinks(int fromID, int toID, int dist) {
        LinkedList<Node> tempnodeList = new LinkedList<Node>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        path = getLinkByID(fromID);

        int esize = encounteredList.size();
        Node firstNode = new Node(fromID);
        encounteredList.add(firstNode);
        tempnodeList.add(firstNode);

        for (int i = 0; i < path.size(); i++) {
            System.out.println(i);
            if (path.isEmpty()) {
                continue;
            }
            if (Reappear(path.get(i), encounteredList, dist)) {

                encounteredList.subList(dist, esize).clear();

                continue;
            }
            if (toID == path.get(i)) {
                Node tempNode = new Node(path.get(i));
                tempNode.setCheck(true);
                tempnodeList.add(tempNode);
                ListOfLists.add(tempnodeList);
                check = true;
                counter = tempnodeList.size();
                continue;
            }
        }

        if (path.size() < encounteredList.size()) {

        }

        if (!check && counter <= tempnodeList.size()) {
            for (int i = 0; i < path.size(); i++) {
                LinkedList<Node> testlist = findIndLinks(path.get(i), toID, dist + 1);
                if (testlist.getLast().getID() != toID) {
                    continue;
                }
                int size = tempnodeList.size();
                if (size - 1 == 0) {
                    tempnodeList.addAll(testlist);
                } else if (tempnodeList.size() - 1 > testlist.size()) {
                    tempnodeList = testlist;

                }
            }
            System.out.println("Sorry, those two can't be connected!");
            return null;

        }
        
        return tempnodeList;

    }

    private boolean Reappear(int id, LinkedList<Node> list, int dist) {
        for (int i = 0; i < dist + 1; i++) {
            if (!list.isEmpty()) {
                if (id == list.get(i).getID()) {
                    return true;
                }
            }
        }

        return false;

    }

    public ArrayList getLinkByID(int id) {
        ArrayList<Integer> toList = new ArrayList<Integer>();
        for (Links templink : Links) {
            if (templink.getFromID() == id) {
                toList.add(templink.getToID());
            }
        }
        return toList;
    }
    
    public LinkedList getFullPath(int id){
        return ListOfLists.get(id);
    }

}
