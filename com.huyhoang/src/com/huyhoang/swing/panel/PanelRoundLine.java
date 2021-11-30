package com.huyhoang.swing.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelRoundLine extends JPanel {
    
    private Color borderColor = new Color(255, 199, 9);
    private int borderRadius = 5;
    private int borderSize = 3;

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        
    }

    public int getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
        setBorder(new EmptyBorder(borderSize, borderSize, borderSize, borderSize));
        repaint();
    }

    public PanelRoundLine() {
        setOpaque(false);
        setBorder(new EmptyBorder(borderSize, borderSize, borderSize, borderSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
        g2.setColor(getBackground());
        g2.fillRoundRect(borderSize, borderSize, getWidth() - (borderSize * 2), getHeight() - (borderSize * 2), borderRadius, borderRadius);
        super.paintComponent(g);
    }
}
