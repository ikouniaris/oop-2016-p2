/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Basics.Cities;
import Basics.Coordinates;
import Basics.Links;
import Storage.Database;
import java.util.ArrayList;
import Storage.FileUtilities;
import java.io.IOException;
import Storage.DBHasDataException;
import java.io.FileNotFoundException;
import Basics.IndirectCon;
/**
 *
 * @author Ilianna
 */
public class JWindow extends JFrame implements ActionListener {

    private JFrame mainFrame;
    private Cities cities = new Cities();
    private JComboBox<String> dropmenu;
    private JButton okButton, dbButton, fileButton;
    private String value;
    private String value2;
    private JTextArea textArea;
    private JComboBox<String> dropmenu2;
    private int check;
    private int use;
    private Links link = new Links();
    private String[] choices;
    public void test() {

        mainFrame = new JFrame("TITLE");

        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(null);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        JButton infoButton = new JButton("City Search");
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                check = 0;
                dropmenu2.setVisible(false);
                textArea.setVisible(false);
                okButton.setBounds(250, 100, 60, 30);
                dropmenu.setBounds(70, 100, 180, 30);
                dropmenu.setVisible(true);
                okButton.setVisible(true);
                fileButton.setVisible(false);
                dbButton.setVisible(false);
            }
        }
        );

        JButton linkButton = new JButton("Link Search");
        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                check = 1;
                textArea.setVisible(false);
                dropmenu.setBounds(45, 100, 125, 30);
                dropmenu.setVisible(true);
                dropmenu2.setBounds(170, 100, 125, 30);
                dropmenu2.setVisible(true);
                okButton.setBounds(295, 100, 60, 30);
                okButton.setVisible(true);
                fileButton.setVisible(false);
                dbButton.setVisible(false);
            }
        });

        JButton loadButton = new JButton("Load Data");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                check = 2;
                dropmenu.setVisible(false);
                dropmenu2.setVisible(false);
                textArea.setVisible(false);
                okButton.setBounds(295, 100, 60, 30);
                fileButton.setBounds(45, 100, 125, 30);
                dbButton.setBounds(170, 100, 125, 30);
                okButton.setVisible(true);
                fileButton.setVisible(true);
                dbButton.setVisible(true);
            }
        }
        );

        JButton saveButton = new JButton("Save Data");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                check = 3;
                dropmenu.setVisible(false);
                dropmenu2.setVisible(false);
                textArea.setVisible(false);
                okButton.setBounds(295, 100, 60, 30);
                fileButton.setBounds(45, 100, 125, 30);
                dbButton.setBounds(170, 100, 125, 30);
                okButton.setVisible(true);
                fileButton.setVisible(true);
                dbButton.setVisible(true);
            }
        }
        );

        infoButton.setBounds(80, 10, 110, 30);
        linkButton.setBounds(190, 10, 110, 30);
        saveButton.setBounds(80, 60, 110, 30);
        loadButton.setBounds(190, 60, 110, 30);

        loadChoices();

        dbButton = new JButton("Database");
        dbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                use = 0;
            }

        });

        fileButton = new JButton("File");
        fileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                use = 1;
            }

        });

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if (check == 0) {
                    value = dropmenu.getSelectedItem().toString();
                    cities = cities.getCity(value);
                    Coordinates c = cities.getCoordinate();
                    textArea.setText("Name: " + value + "\n\nID: " + cities.getId() + "\n\nCoordinates:\ntype: " + c.getType() + "\nx: " + c.getX() + "\ny: " + c.getY() + "\n\nScore: " + cities.getScore() + "\n\nDistance: " + cities.getDistance());
                } else if (check == 1) {
                    value = dropmenu.getSelectedItem().toString();
                    value2 = dropmenu2.getSelectedItem().toString();
                    if (value == value2) {
                        textArea.setText("Both inputs are the same city!");
                    } else if (link.dlinked(value, value2)==0) {
                        textArea.setText(value + " and " + value2 + " connect directly.");
                    } else if (link.dlinked(value, value2)==1) {
                       textArea.setText(value + " and " + value2 + " connect indirectly.");
                    } else {
                        textArea.setText("Looking for path, please wait.");
                        textArea.setVisible(true);
                        Cities city=new Cities();
                        Links link=new Links();
                        new IndirectCon(link.returnLinks()).findIndLinks(city.getIdByName(value),city.getIdByName(value2),0);
                        if (link.dlinked(value, value2)==1) {
                       textArea.setText(value + " and " + value2 + " connect indirectly.");
                        } else {
                            textArea.setText("Unfortunately, "+ value + " and " + value2 + " don't connect.");
                        }
                    }
                } else if (check == 2) {
                    if (use == 0) {
                        textArea.setText("Loading, please wait.");
                        textArea.setVisible(true);
                        Database dtbs = new Database();
                        dtbs.Connect();
                        dtbs.readCitiesFromDB();
                        dtbs.readLinksFromDB();
                        dtbs.closeConnection();
                        loadChoices();
                        textArea.setText("Loading finished.");
                    } else {
                        textArea.setText("Loading, please wait.");
                        textArea.setVisible(true);
                        FileUtilities fl = new FileUtilities();
                        try {
                            fl.LoadCities("Cities.txt");
                            fl.LoadLinks("Links.txt");
                            loadChoices();
                            textArea.setText("Loading finished.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    if (use==0){
                        
                        Cities city=new Cities();
                        Links link=new Links();
                        Database dtbs = new Database();
                        dtbs.Connect();
                        try {
                           dtbs.writeCitiesToDB(city.getCityList());
                             dtbs.writeLinksToDB(link.returnLinks());
                             dtbs.closeConnection();
                             textArea.setText("Saving finished.");
                             } catch (DBHasDataException e) {
                                 e.printStackTrace();
                             }
                    } else {
                        
                        Cities city=new Cities();
                        Links link=new Links();
                        FileUtilities fl = new FileUtilities();
                        try {
                        fl.SaveCities("Cities.txt", city.getCityList(), false);
                        fl.SaveLinks("Links.txt", link.returnLinks(), false);
                        textArea.setText("Saving finished.");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                      
                    }

                }
                textArea.setVisible(true);
            }
        });
        okButton.setVisible(false);

        textArea = new JTextArea();
        textArea.setBounds(10, 140, 360, 210);
        textArea.setEditable(false);
        textArea.setVisible(false);

        mainFrame.add(infoButton);
        mainFrame.add(linkButton);
        mainFrame.add(saveButton);
        mainFrame.add(loadButton);
        mainFrame.add(okButton);
        mainFrame.add(dropmenu);
        mainFrame.add(textArea);
        mainFrame.add(dropmenu2);
        mainFrame.add(fileButton);
        mainFrame.add(dbButton);
        mainFrame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("clicked");
    }
    
    public void loadChoices(){
         choices = new String[cities.getCityList().size()];
        for (int i = 0; i < cities.getCityList().size(); i++) {
            choices[i] = cities.getCity(i).getName();
        }

        dropmenu = new JComboBox<String>(choices);
        dropmenu2 = new JComboBox<String>(choices);
        mainFrame.add(dropmenu);
        mainFrame.add(dropmenu2);
    }
}
