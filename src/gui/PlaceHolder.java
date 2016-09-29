package gui;

import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class PlaceHolder {
    
    JTextField textfield = new JTextField();
    String placeholder;
    
    public PlaceHolder(String PLACEHOLDER, JTextField TEXTFIELD) {
        
        textfield = TEXTFIELD;
        placeholder = PLACEHOLDER;
        
        textfield.setText(placeholder);
    }
    
    public void mouseevent() {
        textfield.addMouseListener(new java.awt.event.MouseAdapter() {
            
         
            
            @Override
            public void mouseEntered(MouseEvent evt) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                textfield.setText(placeholder);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                    textfield.setText("");
            }
            
        });
    }
    
}
