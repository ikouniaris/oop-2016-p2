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
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
public class Database {
    
    
    public  void Connect(){
        
    
try {
    
    Connection conn= DriverManager.getConnection("jdbc:mysql://10.100.51.123/test?user=it21447&password=Iakinthos");
    System.out.println("Connecting");

} catch (SQLException ex){    
    System.out.println("SQLException: " + ex.getMessage()); System.out.println("SQLState: " +
ex.getSQLState()); System.out.println("VendorError: " + ex.getErrorCode());
}
}
}