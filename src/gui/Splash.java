package gui;

import java.awt.*;
import javax.swing.*;

public class Splash extends JPanel {

    JLabel icon = new JLabel();

    public Splash() {
        setLayout(new GridBagLayout());
        setBackground(new Color(22, 160, 133));
        icon.setIcon(new ImageIcon("logo.png"));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 1;
        add(icon, c);

    }
}
