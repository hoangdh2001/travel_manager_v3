package com.huyhoang.swing.scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(15, 15));
        setForeground(new Color(91, 91, 91));
        setUnitIncrement(30);
        setOpaque(false);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(new Color(139, 139, 139));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(new Color(91, 91, 91));
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setForeground(new Color(183, 183, 183));
            }
        });
    }
}
