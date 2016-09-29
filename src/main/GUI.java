package main;

import gui.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.*;
import javax.swing.*;

public class GUI extends ContentFrame {

    ContentFrame myself = this;

    static Dimension windowSize;
    JScrollPane contentPane = new JScrollPane();
    Splash splash = new Splash();

    public GUI() {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                windowSize = getContentPane().getSize();
                
              //  BookRoom.formPane.setPreferredSize(windowSize);
                
                

            }
        });

        setIconImage(new ImageIcon("logo.png").getImage());
        setLocation(100, 100);

        contentPane.getViewport().setBackground(new Color(240, 240, 240));

        contentPane.setLayout(new ScrollPaneLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 400));
        add(contentPane);

        contentPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.getVerticalScrollBar().setUnitIncrement(30);

        contentPane.setBorder(BorderFactory.createEmptyBorder());
        contentPane.getVerticalScrollBar().setUI(new MyScrollBarUI(new Color(22, 160, 133)));

        contentPane.getViewport().add(splash, null);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    displayContent(new StartPage(myself));
                }

            }
        }).start();

    }

    @Override
    public void displayContent(Component content) {

        contentPane.getViewport().removeAll();
        contentPane.getViewport().add(content);
        contentPane.getViewport().validate();
        contentPane.getViewport().repaint();
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GUI frame = new GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
