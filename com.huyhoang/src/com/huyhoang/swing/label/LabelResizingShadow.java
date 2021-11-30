package com.huyhoang.swing.label;

import com.huyhoang.swing.graphics.ShadowRenderer;
import com.huyhoang.swing.graphics.ShadowType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class LabelResizingShadow extends JLabel {
    
    public static final int MIN_FONT_SIZE=3;
    public static final int MAX_FONT_SIZE=240;
    int currFontSize = 0;
    
    private int shadowSize = 5;
    private float shadowOpacity = 0.8f;
    private Color shadowColor = Color.BLACK;
    private ShadowType shadowType = ShadowType.CENTER;

    public int getShadowSize() {
        return shadowSize;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
    }

    public float getShadowOpacity() {
        return shadowOpacity;
    }

    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    public ShadowType getShadowType() {
        return shadowType;
    }

    public void setShadowType(ShadowType shadowType) {
        this.shadowType = shadowType;
    }

    public LabelResizingShadow() {
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        int width = getWidth();
        int height = getHeight();
        int size = shadowSize * 2;
        int x = 0;
        int y = 0;
        if (shadowType == ShadowType.TOP) {
            x = shadowSize;
            y = size;
        } else if (shadowType == ShadowType.BOT) {
            x = shadowSize;
            y = 0;
        } else if (shadowType == ShadowType.TOP_LEFT) {
            x = size;
            y = size;
        } else if (shadowType == ShadowType.TOP_RIGHT) {
            x = 0;
            y = size;
        } else if (shadowType == ShadowType.BOT_LEFT) {
            x = size;
            y = 0;
        } else if (shadowType == ShadowType.BOT_RIGHT) {
            x = 0;
            y = 0;
        } else {
            //  Center
            x = shadowSize;
            y = shadowSize;
        }
        
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setFont(getFont());
        // lấy font
        FontMetrics ft = g.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(getText(), g);
        
        // vị trí text sẽ hiển thị
        double y2 = (height - r2.getHeight()) / 2;
        g.setColor(getForeground());
        g.drawString(getText(), 1, (int) (y2 + ft.getAscent() - 5));
        // bóng text
        ShadowRenderer renderer = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
        g2.drawImage(renderer.createShadow(img), 0, 0, null);
        g2.drawImage(img, x, y, null);
    }

    @Override
    public void paint(Graphics g) {
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
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Dimension getTextSize(Font f, Graphics2D g) {
        Dimension size  = new Dimension();
        FontMetrics fm  = g.getFontMetrics(f);
        size.width      = fm.stringWidth(getText());
        size.height     = fm.getHeight();
        return size;
    }
}
