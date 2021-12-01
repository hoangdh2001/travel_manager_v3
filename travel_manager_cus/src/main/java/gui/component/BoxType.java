package gui.component;

import model.LoaiChuyenDi;
import java.awt.Color;
import java.awt.event.MouseListener;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class BoxType extends com.huyhoang.swing.panel.PanelTransparent {
    private boolean over;
    private Animator animator;
    private LoaiChuyenDi loaiChuyenDi;
    
    public void addEventBoxType(MouseListener mouseListener) {
    	bg.addMouseListener(mouseListener);
    }
    
    public BoxType(LoaiChuyenDi loaiChuyenDi) {
        this.loaiChuyenDi = loaiChuyenDi;
        initComponents();
        buildDisplay();
    }
    
    private void buildDisplay() {
        loadData();
        overTransparent();
    }
    
    private void loadData() {
        wrapLabel1.setText(loaiChuyenDi.getTenLoaiChuyen());
        wrapLabel1.repaint();
        wrapLabel1.revalidate();
        setBackground(Color.decode(loaiChuyenDi.getMauLoai()));
        repaint();
        revalidate();
    }
    
    private void overTransparent() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (over) {
                    bg.setAlpha(0.2f * fraction);
                } else {
                    bg.setAlpha(0.2f * (1f - fraction));
                }
            }
        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }
    
    public void refreshBox() {
        over = false;
        if (animator.isRunning()) {
            animator.stop();
        }
        animator.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        bg = new com.huyhoang.swing.panel.LayerPaneTransparent();
        wrapLabel1 = new com.huyhoang.swing.label.WrapLabel();

        setBackground(new java.awt.Color(39, 133, 106));
        setBorderRadius(20);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bg.setBorderRadius(20);
        bg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bgMouseExited(evt);
            }
        });

        wrapLabel1.setForeground(new java.awt.Color(255, 255, 255));
        wrapLabel1.setText("Du lịch");
        wrapLabel1.setVAlignStyle(0.0F);
        wrapLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        bg.setLayer(wrapLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wrapLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wrapLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        

    private void bgMouseEntered(java.awt.event.MouseEvent evt) {                                
        over = true;
        if (animator.isRunning()) {
            animator.stop();
        }
        animator.start();
    }                               

    private void bgMouseExited(java.awt.event.MouseEvent evt) {                               
        refreshBox();
    } 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.huyhoang.swing.panel.LayerPaneTransparent bg;
    private com.huyhoang.swing.label.WrapLabel wrapLabel1;
    // End of variables declaration//GEN-END:variables
}
