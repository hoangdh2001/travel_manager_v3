package gui.component;

import com.huyhoang.swing.button.Button;
import com.huyhoang.swing.button.ButtonBadges;
import com.huyhoang.swing.button.PopupMenu;
import com.huyhoang.swing.event.EventMenuSelected;

import gui.MainFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

public class Header extends com.huyhoang.swing.panel.PanelTransparent {

    private PopupMenu popupMenu;
    private EventMenuSelected event;
    private boolean transparent;
    
    public void addEventBack(ActionListener event) {
        btnBack.addActionListener(event);
    }
    
    public void addEventNext(ActionListener event) {
        btnNext.addActionListener(event);
    }
    
    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
    }

    public ButtonBadges getBtnBack() {
        return btnBack;
    }

    public void setBtnBack(ButtonBadges btnBack) {
        this.btnBack = btnBack;
    }

    public ButtonBadges getBtnNext() {
        return btnNext;
    }

    public void setBtnNext(ButtonBadges btnNext) {
        this.btnNext = btnNext;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public Header() {
        initComponents();
        buildDisplay();
    }

    private void buildDisplay() {
        createPopupMenu();
        createButtonInfo();
    }
    
    private void createButtonInfo() {
        buttonInfo1.setPrefixIcon(new ImageIcon(MainFrame.khachHang.getAvatar()));
        buttonInfo1.setText(MainFrame.khachHang.getHoTen());
    }

    private void createPopupMenu() {
        popupMenu = new PopupMenu();
        popupMenu.setBackground(new Color(40, 40, 40));
        addItem("Hồ sơ", 0);
        addItem("Cài đặt", 1);
        popupMenu.addSeperator();
        addItem("Đăng xuất", 2);
    }

    private void addItem(String text, int index) {
        Button item = new Button();
        item.setText(text);
        item.setBackground(new Color(40, 40, 40));
        item.setForeground(Color.WHITE);
        item.setBorderRadius(5);
        item.setHorizontalAlignment(Button.LEADING);
        item.addActionListener((ActionEvent arg0) -> {
            event.menuSelected(index);
            popupMenu.setVisible(false);
            item.setBackground(item.getBackgroundColor());
        });
        popupMenu.addItem(item);
    }

    public void display() {
        transparent = true;
        Animator animator = new Animator(200);
        animator.addTarget(new PropertySetter(this, "alpha", 0.0f));
        animator.setAcceleration(0.2f);
        animator.setDeceleration(0.4f);
        animator.start();
    }

    public void hidden() {
        transparent = false;
        Animator animator = new Animator(200);
        animator.addTarget(new PropertySetter(this, "alpha", 1.0f));
        animator.setAcceleration(0.2f);
        animator.setDeceleration(0.4f);
        animator.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonInfo1 = new com.huyhoang.swing.button.ButtonInfo();
        btnNext = new com.huyhoang.swing.button.ButtonBadges();
        btnBack = new com.huyhoang.swing.button.ButtonBadges();

        setBackground(new java.awt.Color(0, 0, 0));

        buttonInfo1.setBackground(new java.awt.Color(0, 0, 0));
        buttonInfo1.setForeground(new java.awt.Color(255, 255, 255));
        buttonInfo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/expand_bottom.png"))); // NOI18N
        buttonInfo1.setText("Đỗ Huy Hoàng");
        buttonInfo1.setBorderRadius(37);
        buttonInfo1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonInfo1.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar.jpg"))); // NOI18N
        buttonInfo1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/expand_top.png"))); // NOI18N
        buttonInfo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInfo1ActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(10, 10, 10));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/next_arrow.png"))); // NOI18N
        btnNext.setEnabled(false);

        btnBack.setBackground(new java.awt.Color(10, 10, 10));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/back.png"))); // NOI18N
        btnBack.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 611, Short.MAX_VALUE)
                .addComponent(buttonInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnNext, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonInfo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInfo1ActionPerformed
        popupMenu.setVisible(true);
        popupMenu.show(buttonInfo1, 0, buttonInfo1.getHeight() + 3);
        popupMenu.setPopupSize(200, (int) popupMenu.getPreferredSize().getHeight());
    }//GEN-LAST:event_buttonInfo1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.huyhoang.swing.button.ButtonBadges btnBack;
    private com.huyhoang.swing.button.ButtonBadges btnNext;
    private com.huyhoang.swing.button.ButtonInfo buttonInfo1;
    // End of variables declaration//GEN-END:variables

}
