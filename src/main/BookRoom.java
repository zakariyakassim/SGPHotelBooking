package main;

import gui.*;
import hotel.RoomType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import static main.GUI.windowSize;
import utils.DBManager;

public class BookRoom extends JPanel {

    ContentFrame gui;
    DBManager db;

    JScrollPane mainPanel = new JScrollPane();

    JPanel topBorder = new JPanel();
    JLabel labTitle = new JLabel();
    JPanel buttons = new JPanel();
    JPanel formPane = new JPanel();

    ColorButton btnBack = new ColorButton("<");
    ColorButton step1 = new ColorButton("1");
    ColorButton step2 = new ColorButton("2");
    ColorButton step3 = new ColorButton("3");
    ColorButton btnSearch = new ColorButton("Search");
    ColorButton btnMBooking = new ColorButton("Make Booking");

    Font font = new Font("Verdana", Font.PLAIN, 15);

    JTextField txtSingleRoom = new JTextField();
    JTextField txtDoubleRoom = new JTextField();
    JTextField txtTwinRoom = new JTextField();
    JTextField txtHoneymoonRoom = new JTextField();
    Border line = BorderFactory.createLineBorder(Color.BLACK);
    Border empty = new EmptyBorder(0, 5, 0, 0);
    CompoundBorder border = new CompoundBorder(line, empty);
    GridBagConstraints c = new GridBagConstraints();

    JLabel singleRoomAvailable = new JLabel();
    JLabel doubleRoomAvailable = new JLabel();
    JLabel twinRoomAvailable = new JLabel();
    JLabel honeymoonRoomAvailable = new JLabel();

    int height = 25;
    int width = 30;

    boolean bookDetails;
    boolean customerDetails;
    boolean paymentDetails;
    boolean canMakeBooking = false;

    String dateIn = null;
    String dateOut = null;

    boolean isUser;

    int countSingle;
    int countDouble;
    int countTwin;
    int countHoneymoon;

    public BookRoom(ContentFrame main, boolean user) throws ClassNotFoundException, SQLException {

        gui = main;
        isUser = user;
        db = new DBManager();

        setLayout(null);
        formPane.setLayout(new GridBagLayout());
        //buttons.setBackground(new Color(230, 230, 230));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                windowSize = gui.getContentPane().getSize();

                mainPanel.setBounds(0, 150, gui.getWidth() - 16, gui.getHeight() - 189);
                topBorder.setBounds(0, 0, windowSize.width, 40);
                buttons.setBounds(0, 60, windowSize.width, 60);


            }
        });

        bookDetails = true;
        customerDetails = false;
        paymentDetails = false;

        bookDetailsForm();

        displayButtons();

        displayBorder();

        displayContent();

    }

    private void displayContent() {

        mainPanel.getViewport().setBackground(new Color(240, 240, 240));

        mainPanel.setLayout(new ScrollPaneLayout());

        mainPanel.setBorder(new LineBorder(new Color(22, 160, 133), 3));
        //  mainPanel.setBorder(new LineBorder(new Color(240, 240, 240), 10));
        mainPanel.getVerticalScrollBar().setUI(new MyScrollBarUI(new Color(22, 160, 133)));
        mainPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.getVerticalScrollBar().setUnitIncrement(30);

        add(mainPanel);

        gui.validate();
        gui.repaint();
    }

    private void displayBorder() {

        topBorder.setBackground(new Color(22, 160, 133));

        if (bookDetails) {
            labTitle.setText("Book Details");
            step1.stayDown();
        }

        if (customerDetails) {
            labTitle.setText("Contact Details");
            step2.stayDown();
        }
        if (paymentDetails) {
            labTitle.setText("Payment Details");
            step3.stayDown();
        }
        labTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labTitle.setForeground(Color.WHITE);

        topBorder.add(labTitle);
        add(topBorder);

    }

    private void displayButtons() {

        buttons.setLayout(new GridBagLayout());

        btnBack.setBackgroundColor(new Color(22, 160, 133));
        btnBack.setHoverBackgroundColor(new Color(211, 84, 0));
        btnBack.setSize(new Dimension(43, 43));
        btnBack.setRadius(50);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                gui.displayContent(new MainMenu(gui, isUser));
            }
        });

        step1.setBackgroundColor(new Color(22, 160, 133));
        step1.setHoverBackgroundColor(new Color(211, 84, 0));
        step1.setSize(new Dimension(80, 50));
        step1.setRadius(15);
        step1.setEnabled(false);

        step2.setBackgroundColor(new Color(22, 160, 133));
        step2.setHoverBackgroundColor(new Color(211, 84, 0));
        step2.setSize(new Dimension(80, 50));
        step2.setRadius(15);
        step2.setEnabled(false);

        step2.toggleMouseOverBehaviour();
        step3.toggleMouseOverBehaviour();

        step3.setBackgroundColor(new Color(22, 160, 133));
        step3.setHoverBackgroundColor(new Color(211, 84, 0));
        step3.setSize(new Dimension(80, 50));
        step3.setRadius(15);
        step3.setEnabled(false);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 20);
        c.gridx = 1;
        c.gridy = 0;
        buttons.add(btnBack, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 20);
        c.gridx = 2;
        c.gridy = 0;
        buttons.add(step1, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 20);
        c.gridx = 3;
        c.gridy = 0;
        buttons.add(step2, c);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 20);
        c.gridx = 4;
        c.gridy = 0;
        buttons.add(step3, c);

        add(buttons);
    }

    private void bookDetailsForm() {
        bookDetails = true;
        customerDetails = false;
        paymentDetails = false;

        final Box DateInBox = new Box("Start Date:         ", "DatePicker");
        DateInBox.getDatePicker().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                dateIn = (String) DateInBox.getDatePicker().getSelectedItem();

            }
        });

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 20);
        c.gridx = 0;
        c.gridy = 0;
        formPane.add(DateInBox, c);

        final Box DateOutBox = new Box("End Date:           ", "DatePicker");
        DateOutBox.getDatePicker().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                dateOut = (String) DateInBox.getDatePicker().getSelectedItem();
            }
        });

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 20);
        c.gridx = 0;
        c.gridy = 1;
        formPane.add(DateOutBox, c);

        final Box NumberOfSingleBox = new Box("Single Rooms:   ", "TextField");

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 20);
        c.gridx = 0;
        c.gridy = 2;
        formPane.add(NumberOfSingleBox, c);

//        JLabel noSingle = new JLabel("Number of Single Rooms:");
//        txtSingleRoom.setForeground(Color.GRAY);
//        txtSingleRoom.setBorder(border);
//        txtSingleRoom.setBounds(165, 52, width, height);
//        noSingle.setBounds(10, 50, 150, 30);
//        formPane.add(txtSingleRoom);
//        formPane.add(noSingle);
//
//        JLabel noDouble = new JLabel("Number of Double Rooms:");
//        txtDoubleRoom.setForeground(Color.GRAY);
//        txtDoubleRoom.setBorder(border);
//        txtDoubleRoom.setBounds(165, 102, width, height);
//        noDouble.setBounds(10, 100, 150, 30);
//        formPane.add(txtDoubleRoom);
//        formPane.add(noDouble);
//
//        JLabel noTwin = new JLabel("Number of Twin Rooms:");
//        txtTwinRoom.setForeground(Color.GRAY);
//        txtTwinRoom.setBorder(border);
//        txtTwinRoom.setBounds(165, 152, width, height);
//        noTwin.setBounds(10, 150, 150, 30);
//        formPane.add(txtTwinRoom);
//        formPane.add(noTwin);
//
//        JLabel noHonnymoon = new JLabel("Number of Honnymoon Rooms:");
//        txtHoneymoonRoom.setForeground(Color.GRAY);
//        txtHoneymoonRoom.setBorder(border);
//        txtHoneymoonRoom.setBounds(165, 202, width, height);
//        noHonnymoon.setBounds(10, 200, 150, 30);
//        formPane.add(txtHoneymoonRoom);
//        formPane.add(noHonnymoon);
//        btnSearch.setBackgroundColor(new Color(22, 160, 133));
//        btnSearch.setHoverBackgroundColor(new Color(211, 84, 0));
//        btnSearch.setSize(new Dimension(250, 40));
//        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//                try {
//                    checkResults();
//                } catch (SQLException ex) {
//                    Logger.getLogger(BookRoom.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (ParseException ex) {
//                    Logger.getLogger(BookRoom.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//
//        btnSearch.setBounds(10, 250, 200, 30);
//        formPane.add(btnSearch);
//
//        btnMBooking.setBackgroundColor(new Color(22, 160, 133));
//        btnMBooking.setHoverBackgroundColor(new Color(211, 84, 0));
//        btnMBooking.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (!canMakeBooking) {
//                    JOptionPane.showMessageDialog(gui, "The rooms are not available. Please modify your criterias.");
//                }
//                customerDetailsForm();
//            }
//        });
//        btnMBooking.setBounds(250, 250, 200, 30);
//
//        singleRoomAvailable.setBounds(300, 50, 300, 30);
//        formPane.add(singleRoomAvailable);
//
//        doubleRoomAvailable.setBounds(300, 100, 300, 30);
//        formPane.add(doubleRoomAvailable);
//
//        twinRoomAvailable.setBounds(300, 150, 300, 30);
//        formPane.add(twinRoomAvailable);
//
//        honeymoonRoomAvailable.setBounds(300, 200, 300, 30);
//        formPane.add(honeymoonRoomAvailable);
//
//        formPane.add(btnMBooking);
        mainPanel.getViewport().add(formPane);

        gui.validate();
        gui.repaint();

    }

    private void customerDetailsForm() {
        bookDetails = false;
        customerDetails = true;
        paymentDetails = false;

        formPane.removeAll();
        revalidate();
        repaint();

        displayContent();
        displayBorder();
        displayButtons();

        step1.toggleMouseOverBehaviour();
        step3.toggleMouseOverBehaviour();

    }

    private void checkResults() throws SQLException, ParseException {

        boolean checkSingle;
        boolean checkDouble;
        boolean checkTwin;
        boolean checkHoneymoon;

        System.out.println(dateIn);
        java.util.Date parsedIn = new SimpleDateFormat("MMM d, yyyy").parse(dateIn);
        java.sql.Date sqlDateIn = new java.sql.Date(parsedIn.getTime());
        java.util.Date parsedOut = new SimpleDateFormat("MMM d, yyyy").parse(dateOut);
        java.sql.Date sqlDateOut = new java.sql.Date(parsedOut.getTime());

        countSingle = db.getRoomCountByAvailability(sqlDateIn, sqlDateOut, RoomType.SINGLE);
        countDouble = db.getRoomCountByAvailability(sqlDateIn, sqlDateOut, RoomType.DOUBLE);
        countTwin = db.getRoomCountByAvailability(sqlDateIn, sqlDateOut, RoomType.TWIN);
        countHoneymoon = db.getRoomCountByAvailability(sqlDateIn, sqlDateOut, RoomType.HONEYMOON);

        if (txtSingleRoom.getText().equals("") || Integer.parseInt(txtSingleRoom.getText()) <= countSingle) {
            checkSingle = true;
            singleRoomAvailable.setText("Available");
            singleRoomAvailable.setForeground(Color.green);
        } else {
            checkSingle = false;
            singleRoomAvailable.setText("Not Available. We have only " + countSingle + " rooms.");
            singleRoomAvailable.setForeground(Color.red);
        }

        if (txtDoubleRoom.getText().equals("") || Integer.parseInt(txtDoubleRoom.getText()) <= countDouble) {
            checkDouble = true;
            doubleRoomAvailable.setText("Available");
            doubleRoomAvailable.setForeground(Color.green);
        } else {
            checkDouble = false;
            doubleRoomAvailable.setText("Not Available. We have only " + countDouble + " rooms.");
            doubleRoomAvailable.setForeground(Color.red);
        }

        if (txtTwinRoom.getText().equals("") || Integer.parseInt(txtTwinRoom.getText()) <= countTwin) {
            checkTwin = true;
            twinRoomAvailable.setText("Available");
            twinRoomAvailable.setForeground(Color.green);
        } else {
            checkTwin = false;
            twinRoomAvailable.setText("Not Available. We have only " + countTwin + " rooms.");
            twinRoomAvailable.setForeground(Color.red);
        }

        if (txtHoneymoonRoom.getText().equals("") || Integer.parseInt(txtHoneymoonRoom.getText()) <= countHoneymoon) {
            checkHoneymoon = true;
            honeymoonRoomAvailable.setText("Available");
            honeymoonRoomAvailable.setForeground(Color.green);
        } else {
            checkHoneymoon = false;
            honeymoonRoomAvailable.setText("Not Available. We have only " + countHoneymoon + " rooms.");
            honeymoonRoomAvailable.setForeground(Color.red);
        }

        canMakeBooking = (checkSingle && checkDouble && checkTwin && checkHoneymoon);
        System.out.println(canMakeBooking);
    }

}
