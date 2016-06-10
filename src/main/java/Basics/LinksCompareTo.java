/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basics;

/**
 *
 * @author Cryowynd
 */
//might delete it
import java.util.Comparator;
public class LinksCompareTo implements Comparator<Links>{
    @Override
    public int compare(Links l1, Links l2) {
        return l1.getToName().compareTo(l2.getToName());
    }
}

