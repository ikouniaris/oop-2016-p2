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

public class CitiesCompare implements Comparator<Cities> {

    @Override
    public int compare(Cities c1, Cities c2) {
        return c1.getName().compareTo(c2.getName());
    }
}
