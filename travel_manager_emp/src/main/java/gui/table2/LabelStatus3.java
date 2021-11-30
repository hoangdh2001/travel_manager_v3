package gui.table2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class LabelStatus3 extends JLabel {
    
    public LabelStatus3() {
        setBorder(new EmptyBorder(2, 10, 2, 10));
        setFont(new Font("sansserif", Font.BOLD, 12));
        setHorizontalAlignment(JLabel.CENTER);
    }
    
    public LabelStatus3(String text) {
        super(text);
        setBorder(new EmptyBorder(2, 10, 2, 10));
        setFont(new Font("sansserif", Font.PLAIN, 12));
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(getFont());
        
        FontMetrics ft = g.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(getText(), g);
        
        double x2 = (width - r2.getWidth()) / 2;
        double y2 = (height - r2.getHeight()) / 2;
        
        g2.setColor(getForeground());
        g2.fillRect((int) (x2), (int) (y2 + ft.getAscent() + 3),(int) r2.getWidth(), 1);
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }
}
