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
import java.util.ArrayList;

/**
 *
 * @author Ilianna
 */
public class JWindow extends JFrame implements ActionListener {

    private JFrame mainFrame;
    private Cities cities = new Cities();
    private JComboBox<String> dropmenu;
    private JButton okButton;
    private String value;
    private String value2;
    private JTextArea textArea;
    private JComboBox<String> dropmenu2;
    private boolean check;
    private Links link = new Links();
    private JButton backButton = new JButton("Back");
    private JButton dbButton = new JButton("<html>Database<br />(Default)</html>)");
    private JButton fButton = new JButton("File");
    private JButton linkButton = new JButton("Link Search");
    private JButton infoButton = new JButton("City Search");  
    private JButton qB = new JButton("Load From...");
    
    public void test() {

        mainFrame = new JFrame("S-Cities");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(null);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        
        
        
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                check = true;
                dropmenu2.setVisible(false);
                textArea.setVisible(false);
                okButton.setBounds(250, 50, 60, 30);
                dropmenu.setBounds(70, 50, 180, 30);
                dropmenu.setVisible(true);
                okButton.setVisible(true);  
            }
        }
        );
        
        linkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                check = false;
                textArea.setVisible(false);
                dropmenu.setBounds(45, 50, 125, 30);
                dropmenu.setVisible(true);
                dropmenu2.setBounds(170, 50, 125, 30);
                dropmenu2.setVisible(true);
                okButton.setBounds(295, 50, 60, 30);
                okButton.setVisible(true);
            }
        });
        
        infoButton.setBounds(80, 10, 110, 30);
        linkButton.setBounds(190, 10, 110, 30);

        
        String[] choices = new String[cities.getCityList().size()];
        for (int i = 0; i < cities.getCityList().size(); i++) {
            choices[i] = cities.getCity(i).getName();
        }
        
        dropmenu = new JComboBox<String>(choices);
        dropmenu2 = new JComboBox<String>(choices);
        
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                value = dropmenu.getSelectedItem().toString();
                if (check) {
                    cities = cities.getCity(value);
                    Coordinates c = cities.getCoordinate();
                    textArea.setText("Name: " + value + "\n\nID: " + cities.getId() + "\n\nCoordinates:\ntype: " + c.getType() + "\nx: " + c.getX() + "\ny: " + c.getY() + "\n\nScore: " + cities.getScore() + "\n\nDistance: " + cities.getDistance());
                } else {
                    value2 = dropmenu2.getSelectedItem().toString();
                    if(value == value2){
                        textArea.setText("Both inputs are the same city!");
                    }else if (link.dlinked(value, value2)) {
                        textArea.setText(value+" and "+value2+" connect directly.");
                    } else {
                        textArea.setText("Unfortunately, "+value+" and "+value2+" don't connect.");
                    }
                }
                textArea.setVisible(true);
            }
        });
        
        qB.setBounds(250, 315, 120, 30);
        qB.setVisible(true);
        qB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                okButton.setVisible(false);
                infoButton.setVisible(false);
                linkButton.setVisible(false);
                dropmenu.setVisible(false);
                dropmenu2.setVisible(false);
                qB.setVisible(false);
                
                textArea.setVisible(true);
                textArea.setBounds(80, 50, 220, 30);
                textArea.setText("Choose where to load information from:");
                backButton.setBounds(290, 315, 80, 30);
                backButton.setVisible(true);
                dbButton.setBounds(120, 90, 80, 50);
                dbButton.setVisible(true);
                fButton.setBounds(200, 90, 80, 50);
                fButton.setVisible(true);
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                backButton.setVisible(false);
                dbButton.setVisible(false);
                fButton.setVisible(false);
                textArea.setVisible(false);
                textArea.setBounds(10, 90, 360, 210);
                infoButton.setVisible(true);
                linkButton.setVisible(true);
                qB.setVisible(true);
            }
        });
        
        
        
        textArea = new JTextArea();
        textArea.setBounds(10, 90, 360, 210);
        textArea.setEditable(false);
        textArea.setVisible(false);
        
        
        mainFrame.add(infoButton);
        mainFrame.add(linkButton);
        mainFrame.add(okButton);
        mainFrame.add(dropmenu);
        mainFrame.add(textArea);
        mainFrame.add(dropmenu2);
        mainFrame.add(qB);
        mainFrame.add(backButton);
        mainFrame.add(dbButton);
        mainFrame.add(fButton);

        mainFrame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("clicked");
    }
}
