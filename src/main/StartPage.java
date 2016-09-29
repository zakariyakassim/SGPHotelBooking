package main;

import gui.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class StartPage extends JPanel {

    ContentFrame gui;

    ColorButton btnLogin = new ColorButton("Login");
    ColorButton btnGuest = new ColorButton("Guest");
    ColorButton btnExit = new ColorButton("Exit");

    GridBagConstraints c = new GridBagConstraints();

    public StartPage(ContentFrame main) {
        gui = main;

        setLayout(new GridBagLayout());
        displayButtons();

    }

    private void displayButtons() {

        btnLogin.setBackgroundColor(new Color(22, 160, 133));
        btnLogin.setHoverBackgroundColor(new Color(211, 84, 0));
        btnLogin.setSize(new Dimension(150, 150));
        btnLogin.setIcon(new ImageIcon("login.png"));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    gui.displayContent(new LoginPage(gui));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(StartPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(StartPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btnGuest.setBackgroundColor(new Color(22, 160, 133));
        btnGuest.setHoverBackgroundColor(new Color(211, 84, 0));
        btnGuest.setSize(new Dimension(150, 150));
        btnGuest.setIcon(new ImageIcon("guest.png"));
        btnGuest.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.displayContent(new MainMenu(gui, false));
            }
        });

        btnExit.setBackgroundColor(new Color(22, 160, 133));
        btnExit.setHoverBackgroundColor(new Color(211, 84, 0));
        btnExit.setSize(new Dimension(150, 150));
        btnExit.stayDown();
       // btnExit.setIcon(new ImageIcon("exit.png"));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to close it? ", "", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {

                    System.exit(0);
                }
            }
        });

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 0;
        add(btnLogin, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 20, 0, 0);
        c.gridx = 2;
        c.gridy = 0;
        add(btnGuest, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 20, 0, 0);
        c.gridx = 3;
        c.gridy = 0;
        add(btnExit, c);
    }
}
