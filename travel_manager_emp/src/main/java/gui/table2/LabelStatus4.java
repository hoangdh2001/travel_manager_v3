package gui.table2;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;

public class LabelStatus4 extends JLabel {
    public LabelStatus4() {
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D r2 = fm.getStringBounds(getText(), g);
        
        double x = (width - r2.getWidth()) / 2;
        double y = (height - r2.getHeight()) / 2;
        g2.setColor(getBackground());
        g2.fillOval((int) x - 15, (int) (y + fm.getAscent() / 2) - 2, 10, 10);
        g2.setColor(getForeground());
        g2.drawString(getText(), (int) x, (int) (y + fm.getAscent())); 
    }
}
