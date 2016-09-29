package gui;

import java.awt.Color;
import java.awt.Font;
import static java.lang.Thread.sleep;
import javax.swing.JLabel;

public class Message extends Thread {

    JLabel label;
    int waitSeconds;
    String text;
    Color color;

    public Message(int waitSeconds, JLabel label, String txt, Color color) {
        this.label = label;
        this.waitSeconds = waitSeconds * 1000;
        this.text = txt;
        this.color = color;
        label.setFont(new Font("Arial", Font.PLAIN, 11));
        label.setText(text);
        label.setForeground(color);
    }

    @Override
    public void run(){
             try {
                    sleep(waitSeconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    label.setBackground(new Color(240, 240, 240));
                    label.setText(".");
                }
    }
}
