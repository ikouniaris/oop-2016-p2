/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Storage;

/**
 *
 * @author Cryowynd
 */
public class DBHasDataException extends Exception{
    
    public DBHasDataException(){
        
    }
    
    public DBHasDataException(String ms){
        super(ms);
    }
    
}
