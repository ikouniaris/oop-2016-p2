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
import Basics.Directlink;
import Basics.Coordinates;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import Basics.Cities;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    Connection con;

    public void Connect() {

        System.out.println("------- Oracle JDBC Connection Testing -------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        con = null;

        try {

            con = DriverManager.getConnection("jdbc:oracle:thin:@10.100.51.123:1521:orcl", "it21447", "Iakinthos");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (con != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    public void writeCitiesToDB(ArrayList<Cities> cities) /*throws DBHasDataException*/ {
        Statement stmt = null;

        for (Cities city : cities) {

            int id = city.getId();
            String name = city.getName();
            int score = city.getScore();
            String coordinatestype = city.getCoordinate().getType();
            double coordinatesx = city.getCoordinate().getX();
            double coordinatesy = city.getCoordinate().getY();
            int distance = city.getDistance();

            String query = "insert into Cities Values(" + id + ',' + "'" + name + "'" + ',' + score + ',' + "'" + coordinatestype + "'" + ',' + coordinatesx + ',' + coordinatesy + ',' + distance + ")";

            try {
                stmt = con.createStatement();
                stmt.executeUpdate(query);
                System.out.print('s');

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

    public void writeLinksToDB(ArrayList<Directlink> links) /*throws DBHasDataException*/ {
        Statement stmt = null;

        for (Directlink link : links) {
            int fromId = link.getFromID();
            String fromName = link.getFromName();
            int toId = link.getToID();
            String toName = link.getToName();
            String query = "insert into Links Values(" + fromId + "," + "'" + fromName + "'" + "," + toId + "," + "'" + toName + "'" + ")";
            try {
                stmt = con.createStatement();
                stmt.execute(query);

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

    public void resetDatabase() {
        Statement stmt = null;
        String query = "drop table Cities";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        query = "create table Cities (id number(9),name varchar2(30),score varchar2(20), coordinatetype varchar2(20), coordinatex number(9,6), coordinatey number(9,6),distance varchar2(10))";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "drop table Links";
        try {
            stmt = con.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        query = "Create table Links (fromId number(9), fromName varchar2(30), toId number(9), toName varchar2(30))";

        try {
            stmt = con.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Cities> readCitiesFromDB() {

        Statement stmt = null;
        String query = "select " + "* " + "from " + "Cities";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Cities tempCities;
            while (rs.next()) {

                String id = rs.getString("id");
                System.out.print(id);
                String name = rs.getString("name");
                String score = rs.getString("score");
                Coordinates coordinate = new Coordinates(rs.getString("coordinatetype"), rs.getString("coordinatex"), rs.getString("coordinatey"));
                String distance = rs.getString("distance");
                tempCities = new Cities(id, name, score, coordinate, distance);
                tempCities.addCity(tempCities);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        Cities city = new Cities();
        return city.getCityList();
    }

    public ArrayList<Directlink> readLinksFromDB() {

        Statement stmt = null;
        String query = "select fromId, fromName, toId, toName"
                + "from " + "Connections";

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int fromId = rs.getInt("fromId");
                String fromName = rs.getString("fromName");
                int toId = rs.getInt("toId");
                String toName = rs.getString("toName");
                Directlink tempLink = new Directlink(fromName, fromId, toName, toId);
                tempLink.Addlink(tempLink);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        Directlink link = new Directlink();
        return link.returnLinks();
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
