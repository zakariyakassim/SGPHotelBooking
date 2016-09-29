package main;

import gui.DatePicker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Box extends JPanel {

    DatePicker datepicker;
    JLabel label = new JLabel();
    JTextField textfield;
    GridBagConstraints c = new GridBagConstraints();

    Dimension dimension = new Dimension(150, 30);

    Border line = BorderFactory.createLineBorder(Color.WHITE);
    Border empty = new EmptyBorder(0, 20, 0, 0);
    CompoundBorder border = new CompoundBorder(line, empty);

    public Box(String text, String type) {
        label.setText(text);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setForeground(Color.white);
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        add(label, c);

        setLayout(new GridBagLayout());

        setBackground(new Color(22, 160, 133));
        setPreferredSize(new Dimension(500, 60));

        if (type.equals("DatePicker")) {
            addDatePicker();
        }
        if (type.equals("TextField")) {
            addTextField();
        }

        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {

            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                // setBackground(new Color(26, 188, 156));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                // setBackground(new Color(22, 160, 133));
            }
        });

    }

    public void addDatePicker() {
        datepicker = new DatePicker();
        datepicker.setEditable(true);
        datepicker.setBorder(border);
        datepicker.setPreferredSize(dimension);

        datepicker.getEditor().getEditorComponent().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setBackground(new Color(26, 188, 156));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(new Color(22, 160, 133));
            }
        });

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 50, 0, 0);
        c.gridx = 1;
        c.gridy = 0;
        add(datepicker, c);

    }

    public void addTextField() {
        textfield = new JTextField();
        textfield.setBorder(border);
        textfield.setPreferredSize(dimension);
        c.anchor = GridBagConstraints.CENTER;

        textfield.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                setBackground(new Color(26, 188, 156));
            }

            @Override
            public void focusLost(FocusEvent e) {
                setBackground(new Color(22, 160, 133));
            }
        });

        c.insets = new Insets(0, 50, 0, 0);
        c.gridx = 1;
        c.gridy = 0;
        add(textfield, c);
    }

    public DatePicker getDatePicker() {
        return datepicker;
    }

    public JTextField getTextField() {
        return textfield;
    }

}
