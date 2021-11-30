package gui.component;

import model.DonDatVe;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class Chat extends javax.swing.JPanel {
    private DonDatVe donDatVe;
    private JLabel thanhTien;
    private final  DecimalFormat df = new DecimalFormat("#,##0đ");
    public void addActionMinimize(ActionListener evt) {
        btnMi.addActionListener(evt);
    }

    public void addActionMaximize(ActionListener evt) {
        btnRe.addActionListener(evt);
    }

    public void addActionClose(ActionListener evt) {
        btnClose.addActionListener(evt);
    }
    
    public void showDonDat(DonDatVe donDatVe) {
        this.donDatVe = donDatVe;
        jPanel2.removeAll();
        createPnlForm();
        loadData();
        jPanel2.repaint();
        jPanel2.revalidate();
    }
    
    public void loadData() {
        thanhTien.setText(donDatVe.getSoLuong() + " X " + df.format(donDatVe.getChuyenDuLich().getGiaChuyenDi()));
    }

    public JPanel getPnlTop() {
        return pnlTop;
    }
    
    public Chat() {
        initComponents();
    }
    
    private void createPnlForm() {
        JPanel don = new JPanel();
        don.setLayout(new MigLayout("fillx, wrap", "[]push[right]", "5[]5"));
        don.setOpaque(false);
        JLabel khoan = new JLabel("Người");
        khoan.setForeground(Color.WHITE);
        khoan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        don.add(khoan);
        
        thanhTien = new JLabel();
        thanhTien.setForeground(Color.WHITE);
        thanhTien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        don.add(thanhTien);
        jPanel2.add(don);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnMi = new com.huyhoang.swing.button.Button();
        btnClose = new com.huyhoang.swing.button.Button();
        btnRe = new com.huyhoang.swing.button.Button();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(40, 40, 40));

        pnlTop.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(19, 19, 19));

        btnMi.setBackground(new java.awt.Color(19, 19, 19));
        btnMi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mi.png"))); // NOI18N
        btnMi.setToolTipText("Minimize");

        btnClose.setBackground(new java.awt.Color(19, 19, 19));
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        btnClose.setToolTipText("Close");
        btnClose.setOverColor(new java.awt.Color(205, 26, 43));

        btnRe.setBackground(new java.awt.Color(19, 19, 19));
        btnRe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/max.png"))); // NOI18N
        btnRe.setToolTipText("Maximize");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnMi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnRe, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                .addGap(0, 155, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.huyhoang.swing.button.Button btnClose;
    private com.huyhoang.swing.button.Button btnMi;
    private com.huyhoang.swing.button.Button btnRe;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
}
