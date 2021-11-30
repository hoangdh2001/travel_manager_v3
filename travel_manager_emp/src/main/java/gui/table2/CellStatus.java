package gui.table2;

import model.TrangThaiChuyenDi;
import model.TrangThaiDonDat;

public class CellStatus extends javax.swing.JPanel {

    public CellStatus(Object o) {
        initComponents();
        if (o instanceof TrangThaiChuyenDi) {
            TrangThaiChuyenDi trangThai = (TrangThaiChuyenDi) o;
            lblStatus.setText(trangThai.getTrangThai());
            lblStatus.setBackground(trangThai.getMauTrangThai());
//            lblStatus.setForeground(trangThai.getMauTrangThai());
        } else if (o instanceof TrangThaiDonDat) {
            TrangThaiDonDat trangThai = (TrangThaiDonDat) o;
            lblStatus.setText(trangThai.getTrangThai());
            lblStatus.setBackground(trangThai.getMauTrangThai());
//            lblStatus.setForeground(trangThai.getMauTrangThai());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new gui.table2.LabelStatus4();

        lblStatus.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.table2.LabelStatus4 lblStatus;
    // End of variables declaration//GEN-END:variables
}
