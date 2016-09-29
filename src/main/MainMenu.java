/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.*;
import gui.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;

/**
 *
 * @author Amelia
 */
public class MainMenu extends JPanel {

    ContentFrame gui;

    JScrollPane mainPanel = new JScrollPane();
    JPanel resultsPanel = new JPanel();

    JPanel topBorder = new JPanel();
    JLabel labTitle = new JLabel();
    JPanel buttons = new JPanel();

    ColorButton btnBookRoom = new ColorButton("Make a Booking");
    ColorButton btnCancleBooking = new ColorButton("Search Booking");
    ColorButton btnViewRooms = new ColorButton("View Rooms");
    ColorButton btnCancel = new ColorButton("<");

    GridBagConstraints c = new GridBagConstraints();

    boolean isUser;

    public MainMenu(ContentFrame main, boolean user) {

        isUser = user;
        gui = main;

        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(700, 400));

        displayBorder();

        displayButtons();

    }

    private void displayBorder() {

        labTitle.setText("Welcome guest");
        labTitle.setForeground(new Color(22, 160, 133));
        labTitle.setFont(new Font("Verdana", Font.PLAIN, 25));

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = 0;
        c.gridy = 1;
        add(labTitle, c);

    }

    private void displayButtons() {

        btnCancel.setBackgroundColor(new Color(22, 160, 133));
        btnCancel.setHoverBackgroundColor(new Color(211, 84, 0));
        btnCancel.setSize(new Dimension(43, 43));
        btnCancel.setRadius(50);
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                gui.displayContent(new StartPage(gui));
            }
        });

        btnBookRoom.setBackgroundColor(new Color(22, 160, 133));
        btnBookRoom.setHoverBackgroundColor(new Color(211, 84, 0));
        btnBookRoom.setSize(new Dimension(250, 40));
        btnBookRoom.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    gui.displayContent(new BookRoom(gui, isUser));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnCancleBooking.setBackgroundColor(new Color(22, 160, 133));
        btnCancleBooking.setHoverBackgroundColor(new Color(211, 84, 0));
        btnCancleBooking.setSize(new Dimension(250, 40));
        btnCancleBooking.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        btnViewRooms.setBackgroundColor(new Color(22, 160, 133));
        btnViewRooms.setHoverBackgroundColor(new Color(211, 84, 0));
        btnViewRooms.setSize(new Dimension(250, 40));
        btnViewRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        btnViewRooms.setVisible(isUser);

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 2;
        add(btnCancel, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 3;
        add(btnBookRoom, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 5;
        add(btnCancleBooking, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 6;
        add(btnViewRooms, c);

    }

}
