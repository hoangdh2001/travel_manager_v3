package gui.component;

import com.huyhoang.swing.scrollbar.ScrollPaneCustom;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Main extends javax.swing.JLayeredPane {
    private MigLayout layout;
    public Content getContent() {
        return content;
    }

    public Header getHeader() {
        return header;
    }

    public ScrollPaneCustom getScrollPaneCustom1() {
        return scrollPaneCustom1;
    }
    
    private void createPanelMessage() {
        pnlMessage.setLayout(layout = new MigLayout());
    }
    
    public void showMessage(String text) {
        Message ms = new Message();
        ms.showMessage(text);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    pnlMessage.add(ms, "pos 0.5al 40", 0); // Chèn thêm message vào panel login
                    ms.setVisible(true);
                    pnlMessage.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                if (ms.isShow()) {
                    ms.show(1f - fraction);
                } else {
                    ms.show(fraction);
                }
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    pnlMessage.remove(ms);
                    pnlMessage.repaint();
                    pnlMessage.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator2 = new Animator(300, target);
        animator2.setResolution(0);
        animator2.setAcceleration(0.5f);
        animator2.setDeceleration(0.5f);
        animator2.start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                animator2.start();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }).start();
    }
    
    public Main() {
        initComponents();
        bg2.setVisible(true);
        createPanelMessage();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg3 = new javax.swing.JPanel();
        header = new gui.component.Header();
        pnlMessage = new javax.swing.JPanel();
        bg2 = new com.huyhoang.swing.panel.LayerPaneGradient();
        scrollPaneCustom1 = new com.huyhoang.swing.scrollbar.ScrollPaneCustom();
        content = new gui.component.Content();
        layerPaneGradient1 = new com.huyhoang.swing.panel.LayerPaneGradient();

        setLayout(new java.awt.CardLayout());

        bg3.setOpaque(false);

        pnlMessage.setOpaque(false);

        javax.swing.GroupLayout pnlMessageLayout = new javax.swing.GroupLayout(pnlMessage);
        pnlMessage.setLayout(pnlMessageLayout);
        pnlMessageLayout.setHorizontalGroup(
            pnlMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlMessageLayout.setVerticalGroup(
            pnlMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout bg3Layout = new javax.swing.GroupLayout(bg3);
        bg3.setLayout(bg3Layout);
        bg3Layout.setHorizontalGroup(
            bg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
            .addComponent(pnlMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        bg3Layout.setVerticalGroup(
            bg3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg3Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 549, Short.MAX_VALUE)
                .addComponent(pnlMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setLayer(bg3, javax.swing.JLayeredPane.POPUP_LAYER);
        add(bg3, "card4");

        bg2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        bg2.setLayout(new java.awt.BorderLayout());

        scrollPaneCustom1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneCustom1.setViewportView(content);

        bg2.add(scrollPaneCustom1, java.awt.BorderLayout.CENTER);

        add(bg2, "card3");
        add(layerPaneGradient1, "card4");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.huyhoang.swing.panel.LayerPaneGradient bg2;
    private javax.swing.JPanel bg3;
    private gui.component.Content content;
    private gui.component.Header header;
    private com.huyhoang.swing.panel.LayerPaneGradient layerPaneGradient1;
    private javax.swing.JPanel pnlMessage;
    private com.huyhoang.swing.scrollbar.ScrollPaneCustom scrollPaneCustom1;
    // End of variables declaration//GEN-END:variables
}
