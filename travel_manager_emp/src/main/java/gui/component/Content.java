package gui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLayeredPane;

public class Content extends JLayeredPane {

    public Content() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));
    }

    public void showForm(Component form) {
        removeAll();
        add(form);
        repaint();
        revalidate();
    }
    
}
