package com.huyhoang.swing.scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarRoundedCustom extends JScrollBar {

    public ScrollBarRoundedCustom() {
        setUI(new ModernScrollBarRoundedUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(180, 180, 180));
        setBackground(Color.WHITE);
        setUnitIncrement(20);
    }
}
