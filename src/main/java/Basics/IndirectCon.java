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
import java.util.LinkedList;
import java.util.ArrayList;
import Basics.Links;

public class IndirectCon {

    private int toID;
    private int fromID;
    private ArrayList<Links> xoxo;
    private ArrayList<Integer> temp = new ArrayList<Integer>();
    private LinkedList<Node> tempnodeList = new LinkedList<Node>();
    private static ArrayList<LinkedList> ListOfOmens = new ArrayList<LinkedList>();
    private static LinkedList<Node> nodeList = new LinkedList<Node>();
    public IndirectCon(int fromID, int toID, ArrayList<Links> xoxo) {
        this.fromID = fromID;
        this.toID = toID;
        this.xoxo = xoxo;
    }

    public IndirectCon(int fromID, int toID, ArrayList<Links> xoxo, LinkedList found) {
        this.fromID = fromID;
        this.toID = toID;
        this.xoxo = xoxo;
        nodeList = found;
    }

    
    
    
    
    public LinkedList findIndLinks(int fromID) {

        temp = getLinkByID(fromID);
        
        Node firstNode=new Node(fromID);
        tempnodeList.add(firstNode);
        
        for (int i = 0; i < temp.size(); i++) {
            if (Reappear(temp.get(i))) {
                
                continue;
            }
            if (toID == temp.get(i)) {
                Node tempNode=new Node(temp.get(i)); 
                tempnodeList.add(tempNode);
                return tempnodeList;
            } else {
               LinkedList testlist= findIndLinks(temp.get(i));
               if ((tempnodeList.size()>testlist.size())||(tempnodeList.size()==0)){
                   tempnodeList=testlist;
               }
            }
        }
return tempnodeList;
    }

    
    
    
    
    
    
    
    
    private boolean Reappear(int id) {
        for (Node tempNode : nodeList) {
            if (id == tempNode.getID()) {
                return true;
            }
        }
        
        for (Node tempNode : nodeList) {
            if (id == tempNode.getID()) {
                return true;
            }
        } 
        return false;
    
    }
    
    
    public ArrayList getLinkByID(int id) {
        ArrayList<Integer> toList = new ArrayList<Integer>();
        for (Links templink : xoxo) {
            if (templink.getFromID() == id) {
                toList.add(templink.getToID());
            }
        }
        return toList;
    }

}