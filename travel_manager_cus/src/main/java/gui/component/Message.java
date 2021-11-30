package gui.component;

import java.awt.Color;

public class Message extends com.huyhoang.swing.panel.PanelShadow {

    private boolean show;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Message() {
        initComponents();
        lblMessage.setForeground(new Color(1f, 1f, 1f, 0));
    }

    public void showMessage(String message) {
        lblMessage.setText(message);
    }

    public void show(float alpha) {
        setAlpha(alpha);
        lblMessage.setForeground(new Color(1f, 1f, 1f, alpha));
        lblMessage.repaint();
        lblMessage.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMessage = new javax.swing.JLabel();

        setBackground(new java.awt.Color(46, 119, 208));
        setAlpha(0.0F);
        setBorderRadius(20);
        setShadowOpacity(0.3F);

        lblMessage.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(255, 255, 255));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setText("Đã lưu vào yêu thích");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMessage;
    // End of variables declaration//GEN-END:variables
}
