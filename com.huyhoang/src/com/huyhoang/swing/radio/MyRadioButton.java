package com.huyhoang.swing.radio;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JRadioButton;

public class MyRadioButton extends JRadioButton {

    private Color dotColor = new Color(69, 124, 235);
    private int dotSize = 16;
    private int index;

    public Color getDotColor() {
        return dotColor;
    }

    public void setDotColor(Color dotColor) {
        this.dotColor = dotColor;
    }

    public int getDotSize() {
        return dotSize;
    }

    public void setDotSize(int dotSize) throws Exception {
        if (dotSize <= 16) {
            this.dotSize = dotSize;
        } else {
            throw new Exception("Size nhỏ hơn hoặc = 14");
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public MyRadioButton() {
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int ly = (getHeight() - 16) / 2;
        if (isSelected()) {
            if (isEnabled()) {
                g2.setColor(dotColor);
            } else {
                g2.setColor(Color.GRAY);
            }

            g2.drawOval(1, ly, 16, 16);
            if (isEnabled()) {
                g2.setColor(dotColor);
            } else {
                g2.setColor(new Color(0, 0, 0, 0));
            }
            g2.fillOval(1, ly, dotSize, dotSize);
        } else {
            g2.setColor(dotColor);
            g2.drawOval(1, ly, 16, 16);
        }
    }
}
