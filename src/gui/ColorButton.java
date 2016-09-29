package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

public class ColorButton extends JButton {

    Color backgroundColor = new Color(127, 140, 141);
    Color backgroundColorHover = new Color(26, 188, 156);
    Color finalColor = backgroundColor;
    Font f = new Font("Calibri", Font.BOLD, 18);
    int top = 0;
    int textTop = 2;
    String text = "Button";
    int radius = 10;
    Color fontColor = new Color(255, 255, 255);
    Dimension dimension = new Dimension(250, 40);
    Border emptyBorder = BorderFactory.createEmptyBorder();
    ImageIcon icon = new ImageIcon();
    Boolean ss = true;
    // Dimension d;
    int iconX = 0;
    int iconY = 0;
    int textY = 0;
 boolean isMouseOverEnabled=true;
    Boolean jj = true;

    public ColorButton(String TEXT) {
        text = TEXT;
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(emptyBorder);
        setPreferredSize(new Dimension(250, 43));
        setMaximumSize(new Dimension(250, 43));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
       // d = this.getSize();

        // setIcon(icon);
    }

    public void stayDown() {
        //  backgroundColor = backgroundColorHover;
        finalColor = backgroundColorHover;
        ss = false;
        top = 2;
        textTop = 1;
        repaint();
    }

    public void stayUp() {
        // backgroundColorHover = backgroundColor;
        finalColor = backgroundColor;
        ss = true;
        top = 0;
        textTop = 4;
        repaint();

    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
        jj = false;
//        iconX = (int) dimension.getWidth()/3;
//        iconY = (int) dimension.getHeight()/4;

        iconX = (int) dimension.getWidth() / 3;
        iconY = (int) dimension.getHeight() / 2 - 10;

        textY = (int) dimension.getHeight() / 4;

    }

    @Override
    public void setFont(Font FONT) {
        f = FONT;
    }

    @Override
    public void setSize(Dimension dimension) {
        this.dimension = dimension;
        setPreferredSize(dimension);
    }

    @Override
    public void setForeground(Color FONTCOLOR) {
        fontColor = FONTCOLOR;
    }

    public void setRadius(int RADIUS) {
        radius = RADIUS;
    }

    public void setBackgroundColor(Color BACKGROUNDCOLOR) {
        backgroundColor = BACKGROUNDCOLOR;
        finalColor = backgroundColor;
        addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (ss) {
                    finalColor = backgroundColor;
                    top = 0;
                    if (jj) {
                        textTop = 0;
                    } else {
                        textY = (int) dimension.getHeight() / 4;
                    }
                    iconY = (int) dimension.getHeight() / 2 - 10;
                    textTop = 4;
                    repaint();
                }
            }

        });

    }
    public void toggleMouseOverBehaviour() {
        isMouseOverEnabled = !isMouseOverEnabled;
    }
 
    public void setHoverBackgroundColor(Color BACKGROUNDCOLOR) {
        backgroundColorHover = BACKGROUNDCOLOR;

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
 if (isMouseOverEnabled) {
                finalColor = backgroundColorHover;
                top = 2;
                if (jj) {
                    textTop = 1;
                } else {
                    textY = (int) dimension.getHeight() - 20;
                }
                iconY = (int) dimension.getHeight() / 5;
                repaint();
}
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                finalColor = backgroundColor;
                top = 0;
                textTop = 4;
                repaint();
            }

        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = this.getSize();
        Graphics2D g3 = (Graphics2D) g.create();
        Graphics2D g2 = (Graphics2D) g.create();
        Graphics2D g1 = (Graphics2D) g.create();

        RenderingHints qualityHints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        qualityHints.put(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g3.setRenderingHints(qualityHints);
        g2.setRenderingHints(qualityHints);
        g1.setRenderingHints(qualityHints);

        g1.setColor(Color.LIGHT_GRAY);
        g1.fillRoundRect(0, 2, dimension.width, dimension.height - 3, radius, radius);
        // g1.setColor(new Color(211, 84, 0));

        g2.setColor(finalColor);
        g2.fillRoundRect(0, top, dimension.width, dimension.height - 3, radius, radius);

        g2.setFont(f);
        g2.setColor(fontColor);

        g3.drawImage(icon.getImage(), iconX, iconY, 56, 64, this);
        drawCenteredString(text, textTop, d.width, d.height, g2);

    }

    public void drawImageIcon() {

    }

    public void drawCenteredString(String s, int top, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        if (jj) {
            textY = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        }
        g.drawString(s, x, textY - top);
    }

}
