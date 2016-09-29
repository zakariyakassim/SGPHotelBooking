package main;

import java.awt.*;
import gui.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import org.jdesktop.xswingx.PromptSupport;
import utils.DBManager;

public class SignUpPage extends JPanel {

    ContentFrame gui;
    DBManager db;
    JScrollPane contentPane = new JScrollPane();

    PlaceHolder placeholder;

    JTextField txtFirstName = new JTextField();
    JTextField txtLastName = new JTextField();
    JTextField txtBirthDate = new JTextField();
    JTextField txtEmail = new JTextField();
    JTextField txtConfirmEmail = new JTextField();

    JTextField txtUserName = new JTextField();
    JPasswordField txtPassword = new JPasswordField();
    JPasswordField txtConfirmPassword = new JPasswordField();

    ColorButton btnPerformSignUp = new ColorButton("Sign up");
    ColorButton btnCancel = new ColorButton("<");
    JLabel lblMessage = new JLabel(".");

    Border line = BorderFactory.createLineBorder(Color.WHITE);
    Border empty = new EmptyBorder(0, 20, 0, 0);
    CompoundBorder border = new CompoundBorder(line, empty);
    Font font = new Font("Verdana", Font.PLAIN, 15);

    GridBagConstraints c = new GridBagConstraints();
    int insets = 10;

    int height = 40;
    int width = 250;

    public SignUpPage(ContentFrame main) throws ClassNotFoundException, SQLException {

        gui = main;

        db = new DBManager();

        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(700, 400));

        displayContent();
        displayButtons();

    }

    private void displayContent() {
        lblMessage.setBackground(new Color(240, 240, 240));
        lblMessage.setFont(new Font("Arial", Font.PLAIN, 11));

        txtFirstName.setPreferredSize(new Dimension(width, height));
        txtFirstName.setFont(font);
        txtFirstName.setForeground(Color.GRAY);
        txtFirstName.setBorder(border);

        txtLastName.setPreferredSize(new Dimension(width, height));
        txtLastName.setFont(font);
        txtLastName.setForeground(Color.GRAY);
        txtLastName.setBorder(border);

        txtBirthDate.setPreferredSize(new Dimension(width, height));
        txtBirthDate.setFont(font);
        txtBirthDate.setForeground(Color.GRAY);
        txtBirthDate.setBorder(border);

        txtEmail.setPreferredSize(new Dimension(width, height));
        txtEmail.setFont(font);
        txtEmail.setForeground(Color.GRAY);
        txtEmail.setBorder(border);

        txtConfirmEmail.setPreferredSize(new Dimension(width, height));
        txtConfirmEmail.setFont(font);
        txtConfirmEmail.setForeground(Color.GRAY);
        txtConfirmEmail.setBorder(border);

        txtUserName.setPreferredSize(new Dimension(width, height));
        txtUserName.setFont(font);
        txtUserName.setForeground(Color.GRAY);
        txtUserName.setBorder(border);

        txtPassword.setPreferredSize(new Dimension(width, height));
        txtPassword.setFont(font);
        txtPassword.setForeground(Color.GRAY);
        txtPassword.setBorder(border);

        txtConfirmPassword.setPreferredSize(new Dimension(width, height));
        txtConfirmPassword.setFont(font);
        txtConfirmPassword.setForeground(Color.GRAY);
        txtConfirmPassword.setBorder(border);

        PromptSupport.setPrompt("First name", txtFirstName);
        PromptSupport.setPrompt("Last name", txtLastName);
        PromptSupport.setPrompt("dd.mm.yyyy", txtBirthDate);
        PromptSupport.setPrompt("Email", txtEmail);
        PromptSupport.setPrompt("Confirm email", txtConfirmEmail);
        PromptSupport.setPrompt("Username", txtUserName);
        PromptSupport.setPrompt("Password", txtPassword);
        PromptSupport.setPrompt("Confirm password", txtConfirmPassword);

        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtFirstName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtLastName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtBirthDate);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtConfirmEmail);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtUserName);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtPassword);
        PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtConfirmPassword);

        PromptSupport.setForeground(Variables.textinputcolor, txtFirstName);
        PromptSupport.setForeground(Variables.textinputcolor, txtLastName);
        PromptSupport.setForeground(Variables.textinputcolor, txtBirthDate);
        PromptSupport.setForeground(Variables.textinputcolor, txtEmail);
        PromptSupport.setForeground(Variables.textinputcolor, txtConfirmEmail);
        PromptSupport.setForeground(Variables.textinputcolor, txtUserName);
        PromptSupport.setForeground(Variables.textinputcolor, txtPassword);
        PromptSupport.setForeground(Variables.textinputcolor, txtConfirmPassword);

        setOpaque(false);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 2;
        add(txtFirstName, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 3;
        add(txtLastName, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 4;
        add(txtBirthDate, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 5;
        add(txtEmail, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 6;
        add(txtConfirmEmail, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 7;
        add(txtUserName, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 8;
        add(txtPassword, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 9;
        add(txtConfirmPassword, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 12;
        add(lblMessage, c);
    }

    private void displayButtons() {

        btnPerformSignUp.setBackgroundColor(new Color(22, 160, 133));
        btnPerformSignUp.setHoverBackgroundColor(new Color(211, 84, 0));
        btnPerformSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if ((txtFirstName.getText().length() == 0) || (txtLastName.getText().length() == 0) || (txtBirthDate.getText().length() == 0) || (txtEmail.getText().length() == 0) || (txtConfirmEmail.getText().length() == 0) || (txtUserName.getText().length() == 0) || (txtPassword.getText().length() == 0) || (txtConfirmPassword.getText().length() == 0)) {

                    new Message(3, lblMessage, "Please fill in all the boxes", Color.RED).start();
                } else {

                    try {
                        performSignUp();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                       new Message(3, lblMessage, "The date need to be in the fallow format: dd.mm.yyyy", Color.RED).start();
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
                try {
                    gui.displayContent(new LoginPage(gui));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        setOpaque(false);

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(40, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 1;
        add(btnCancel, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 0, insets, 0);
        c.gridx = 0;
        c.gridy = 11;
        add(btnPerformSignUp, c);
    }

    public void performSignUp() throws ParseException, ClassNotFoundException {

        if (txtEmail.getText().equals(txtConfirmEmail.getText())) {
            if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
                try {
                    java.util.Date parsed = new SimpleDateFormat("dd.mm.yyyy").parse(txtBirthDate.getText());
                    java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());

                    if (db.createUser(txtUserName.getText(), txtPassword.getText(),
                            txtFirstName.getText(), txtLastName.getText(), sqlDate, txtEmail.getText())) {

                        gui.displayContent(new ConfirmRegistration(getName(), gui));
                    } else {
                        new Message(3, lblMessage, "User name already exist", Color.RED).start();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                new Message(3, lblMessage, "Passwords do not match", Color.RED).start();
            }
        } else {
            new Message(3, lblMessage, "Emails do not match", Color.RED).start();
        }

    }

    @Override
    public String getName() {
        return "Welcome " + txtFirstName.getText() + " " + txtLastName.getText();
    }

}
