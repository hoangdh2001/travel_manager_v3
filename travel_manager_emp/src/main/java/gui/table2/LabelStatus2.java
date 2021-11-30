package gui.table2;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class LabelStatus2 extends JLabel {
    private Color colorStatus = new Color(253, 187, 65);

    public Color getColorStatus() {
        return colorStatus;
    }

    public void setColorStatus(Color colorStatus) {
        this.colorStatus = colorStatus;
    }
    
    public LabelStatus2() {
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(2, 10, 2, 10));
        setFont(new Font("sansserif", Font.BOLD, 12));
        setHorizontalAlignment(JLabel.CENTER);
    }
    
    public LabelStatus2(String text, Color colorStatus) {
        super(text);
        this.colorStatus = colorStatus;
        setForeground(Color.BLACK);
        setBorder(new EmptyBorder(2, 10, 2, 10));
        setFont(new Font("sansserif", Font.PLAIN, 12));
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorStatus);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.drawRect(0, 0, getWidth() - 2, getHeight() - 2);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }
}
