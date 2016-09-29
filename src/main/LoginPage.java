package main;

import java.awt.*;
import gui.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import org.jdesktop.xswingx.PromptSupport;
import utils.DBManager;

public class LoginPage extends JPanel {

    ContentFrame gui;

    JScrollPane contentPane = new JScrollPane();

    PlaceHolder placeholder;
    JTextField txtUserName = new JTextField();
    JPasswordField txtPassword = new JPasswordField();
    JLabel labMessage = new JLabel(".");

    ColorButton btnPerformLogin = new ColorButton("Login");
    ColorButton btnCancel = new ColorButton("<");
    ColorButton btnSignUp = new ColorButton("Sign up");

    GridBagConstraints c = new GridBagConstraints();

    DBManager db;

    int height = 40;
    int width = 250;

    public LoginPage(ContentFrame main) throws ClassNotFoundException, SQLException {

        gui = main;

       // db = new DBManager();

        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(700, 400));

        dipslayTextFields();
        displayButtons();

    }

    private void dipslayTextFields() {

        labMessage.setBackground(new Color(240, 240, 240));
        Font font = new Font("Verdana", Font.PLAIN, 15);

        Border line = BorderFactory.createLineBorder(Color.WHITE);
        Border empty = new EmptyBorder(0, 20, 0, 0);
        CompoundBorder border = new CompoundBorder(line, empty);

        txtUserName.setPreferredSize(new Dimension(width, height));
        txtUserName.setFont(font);
        txtUserName.setForeground(Variables.textinputcolor);
        txtUserName.setBorder(border);

        txtPassword.setPreferredSize(new Dimension(width, height));
        txtPassword.setFont(font);
        txtPassword.setForeground(Variables.textinputcolor);
        txtPassword.setBorder(border);

        PromptSupport.setPrompt("Username", txtUserName);
        PromptSupport.setPrompt("Password", txtPassword);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtUserName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtPassword);
        PromptSupport.setForeground(Variables.textinputcolor, txtUserName);
        PromptSupport.setForeground(Variables.textinputcolor, txtPassword);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 2;
        add(txtUserName, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 3;
        add(txtPassword, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 6;
        add(labMessage, c);

    }

    private void displayButtons() {

        btnPerformLogin.setBackgroundColor(new Color(22, 160, 133));
        btnPerformLogin.setHoverBackgroundColor(new Color(211, 84, 0));
        btnPerformLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    performLogin();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        txtUserName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ((txtPassword.getText().length() == 0)) {
                    txtPassword.requestFocus();
                } else {
                    try {
                        performLogin();
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });

        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    performLogin();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        btnSignUp.setBackgroundColor(new Color(22, 160, 133));
        btnSignUp.setHoverBackgroundColor(new Color(211, 84, 0));
        btnSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String setPassword = "123456";
                JPasswordField pf = new JPasswordField();
                int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (okCxl == JOptionPane.OK_OPTION) {
                    String password = new String(pf.getPassword());
                    if (password.equals(setPassword)) {
                        try {
                            gui.displayContent(new SignUpPage(gui));
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        new Message(3, labMessage, "The Password did not match", Color.RED).start();
                    }
                }
            }
        });

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

        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 1;
        add(btnCancel, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 4;
        add(btnPerformLogin, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 10, 0);
        c.gridx = 0;
        c.gridy = 5;
        add(btnSignUp, c);

    }

    private void performLogin() throws SQLException {

        if (db.loginUser(txtUserName.getText(), txtPassword.getText())) {

            contentPane.getViewport().removeAll();

            gui.displayContent(new MainMenu(gui, true));
            new Message(3, labMessage, "Username and Password has been found", Color.GREEN).start();
        } else {
            new Message(3, labMessage, "Invalid Username or Password", Color.RED).start();
        }

    }

}
