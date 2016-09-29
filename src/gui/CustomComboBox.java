/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class CustomComboBox extends JPanel implements ListCellRenderer {
//JLabel label = new JLabel();

    Label label = new Label();
    Color color = new Color(255, 0, 0);
    
    public CustomComboBox() {
        setOpaque(false);
        setLayout(new BorderLayout());
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.fill = GridBagConstraints.CENTER;
//        constraints.weightx = 1.0;
//        constraints.insets = new Insets(2, 2, 2, 2);

        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);

        add(label, BorderLayout.CENTER);
      //  setBackground(Color.LIGHT_GRAY);
        

    }

    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        
        list.setOpaque(false);
        
        if (isSelected) {
          //  label.setBackground(new Color(22, 160, 133));
            label.setFont(new Font("Arial", Font.BOLD, 15));
            label.setForeground(new Color(22, 160, 133));

        } else {
            label.setFont(new Font("Arial", Font.PLAIN, 15));
            label.setForeground(new Color(22, 160, 133));
        }
        label.setOpaque(false);
        label.setText(String.valueOf(value));

        return this;
    }

    private class Label extends JLabel {

        Font font = new Font("Arial", Font.PLAIN, 15);

        public Label() {
            setFont(font);
            setForeground(new Color(22, 160, 133));

            setPreferredSize(new Dimension(50, 40));

        }
    }
}
