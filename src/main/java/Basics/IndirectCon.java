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
public class IndirectCon {

    private ArrayList<Links> ListOfLinks;
    private LinkedList<Node> encounteredList = new LinkedList<Node>();
    private static ArrayList<LinkedList> ListOfLists = new ArrayList<LinkedList>();
    private static boolean check = false;

    private static int counter;

    public IndirectCon(ArrayList<Links> ListOfLinks) {

        this.ListOfLinks = ListOfLinks;
    }

    //Finding the shortest routing path between 2 nodes
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
        for (Links templink : ListOfLinks) {
            if (templink.getFromID() == id) {
                toList.add(templink.getToID());
            }
        }
        return toList;
    }

}
