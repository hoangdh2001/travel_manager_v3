package com.huyhoang.swing.slideshow;

import javax.swing.ImageIcon;

public class Slide extends javax.swing.JPanel {

    public Slide(String path) {
        initComponents();
        pictureBox1.setImage(new ImageIcon(getClass().getResource(path)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pictureBox1 = new com.huyhoang.swing.image.PictureBox();

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/icon/slide1.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.huyhoang.swing.image.PictureBox pictureBox1;
    // End of variables declaration//GEN-END:variables
}
