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
public class node {
    private boolean check=false;
    private int id;
    private node nextNode = new node();
    private node previousNode;
    
    //constructor that sets previous node
    
    
    void setID(int id){
        this.id=id;
    }
    
    void setCheck(boolean check){
        this.check=check;
    }
    
    boolean getCheck(){
        return check;
    }
    
    int getID(){
        return id;
    }
    
    node getNextNode(){
        return nextNode;
    }
    
    node getpreviousNode(){
        return nextNode;
    }
}