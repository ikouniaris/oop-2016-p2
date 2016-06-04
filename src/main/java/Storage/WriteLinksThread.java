/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Storage;

import java.sql.Connection;
import Basics.Links;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Cryowynd
 */
public class WriteLinksThread extends Thread {

    private Statement stmt;
    private int fromId;
    private String fromName;
    private int toId;
    private String toName;
    private Connection con;

    public WriteLinksThread(Links link, Connection con) {
        fromId = link.getFromID();
        fromName = link.getFromName();
        toId = link.getToID();
        toName = link.getToName();
        this.con = con;

    }

    public void run() {

        String query = "insert into Links Values(" + fromId + "," + "'" + fromName + "'" + "," + toId + "," + "'" + toName + "'" + ")";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
            try {
                stmt.close();
            } catch (Exception ignore) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
