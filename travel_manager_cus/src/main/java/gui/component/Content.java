package gui.component;

import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JPanel;

public class Content extends JPanel {

    public Content() {
        setOpaque(false);
        setLayout(new CardLayout());
    }

    public void showForm(Component form) {
        removeAll();
        add(form);
        repaint();
        revalidate();
    }
    
}
