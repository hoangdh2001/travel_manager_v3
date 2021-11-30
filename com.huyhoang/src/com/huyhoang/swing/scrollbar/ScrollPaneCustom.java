package com.huyhoang.swing.scrollbar;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class ScrollPaneCustom extends JScrollPane {
    public ScrollPaneCustom() {
        setOpaque(false);
        setViewport(new ViewPortCustom());
        setBorder(null);
        setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }
    
    private class ViewPortCustom extends JViewport {
        public ViewPortCustom() {
            setOpaque(false);
            setViewportBorder(BorderFactory.createEmptyBorder());
        }
        
    }
}
