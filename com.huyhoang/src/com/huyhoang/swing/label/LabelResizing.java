package com.huyhoang.swing.label;

import javax.swing.*;
import java.awt.*;

public class LabelResizing extends JLabel {
    public static final int MIN_FONT_SIZE=3;
    public static final int MAX_FONT_SIZE=240;
    int currFontSize = 0;

    public LabelResizing() {
        
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        repaint();
        revalidate();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        currFontSize = this.getFont().getSize();
        Rectangle r  = getBounds();
        r.x          = 0;    
        r.y          = 0;    
        int fontSize = Math.max(MIN_FONT_SIZE, currFontSize);
        Font f       = getFont();

        Rectangle r1 = new Rectangle(getTextSize(getFont(), g2));
        while (!r.contains(r1)) {
               fontSize --;
            if (fontSize <= MIN_FONT_SIZE) 
                break;
            r1 = new Rectangle(getTextSize(f.deriveFont(f.getStyle(), fontSize), g2));
        }    

        Rectangle r2 = new Rectangle();
        while (fontSize < MAX_FONT_SIZE) {
            r2.setSize(getTextSize(f.deriveFont(f.getStyle(),fontSize+1), g2));
            if (!r.contains(r2)) {
                break;
            }
            fontSize++;
        }
        setFont(f.deriveFont(f.getStyle(),fontSize));
        super.paintComponent(g);
    }
    
    private Dimension getTextSize(Font f, Graphics2D g) {
        Dimension size  = new Dimension();
        FontMetrics fm  = g.getFontMetrics(f);
        size.width      = fm.stringWidth(getText());
        size.height     = fm.getHeight();
        return size;
    }
}
