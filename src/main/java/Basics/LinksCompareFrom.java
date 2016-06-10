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
import java.util.Comparator;

public class LinksCompareFrom implements Comparator<Links> {

    @Override
    public int compare(Links l1, Links l2) {
        return l1.getFromName().compareTo(l2.getFromName());
    }
}
