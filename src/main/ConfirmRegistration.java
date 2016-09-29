/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

 import gui.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Amelia
 */
public class ConfirmRegistration extends JPanel {

    ContentFrame gui;
    JScrollPane contentPane = new JScrollPane();

    ColorButton btnLogin = new ColorButton("Login");
    ColorButton btnMainMenu = new ColorButton("Main menu");
    JLabel lblWelcome = new JLabel();
    String name;
    GridBagConstraints c = new GridBagConstraints();
    Font font = new Font("Verdana", Font.PLAIN, 25);

    public ConfirmRegistration(String name, ContentFrame main) throws ClassNotFoundException, SQLException {
        
        gui = main;
        this.name = name;
        setLayout(new GridBagLayout());
        
        displayContent();
        displayButtons();
        
    }

    private void displayContent() throws ClassNotFoundException, SQLException {
        setOpaque(false);

        lblWelcome.setText(name);
        lblWelcome.setForeground(new Color(22, 160, 133));
        lblWelcome.setFont(font);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 1;
        add(lblWelcome, c);
    }

    private void displayButtons() {

        btnLogin.setBackgroundColor(new Color(22, 160, 133));
        btnLogin.setHoverBackgroundColor(new Color(211, 84, 0));
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    gui.displayContent(new LoginPage(gui));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ConfirmRegistration.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ConfirmRegistration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        btnMainMenu.setBackgroundColor(new Color(22, 160, 133));
        btnMainMenu.setHoverBackgroundColor(new Color(211, 84, 0));
        btnMainMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.displayContent(new StartPage(gui));
            }
        });

        setOpaque(false);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 5, 0);
        c.gridx = 0;
        c.gridy = 2;
        add(btnLogin, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 5, 0);
        c.gridx = 0;
        c.gridy = 3;
        add(btnMainMenu, c);
    }

}
