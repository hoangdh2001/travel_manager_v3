/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.component;

import dao.impl.DonDatVeImpl;
import model.DonDatVe;
import model.TrangThaiDonDat;
import com.huyhoang.swing.event.EventPagination;

import dao.DonDatVe_DAO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGUYE
 */
public class PanelDonDatVe extends javax.swing.JPanel {

    private DonDatVe_DAO datVeImpl;
    public PanelDonDatVe() throws MalformedURLException, RemoteException, NotBoundException {
        initComponents();
        setPropertiesForm();
        setSizeColumn();
        
        datVeImpl = (DonDatVe_DAO)Naming.lookup("rmi://localhost:1099/donDatVe_DAO");

        loadDataForm();

//        tblDonDatVeHandle();
//
//        searchHandle();

//        loadDataTable(pnlPage.getCurrentIndex());

        btnLamMoiHandle();
    }

    private void setPropertiesForm() {
        int txtRadius = 10;

        Color colorBtn = new Color(184, 238, 241);

        txtTimKiem.setBorderLine(true);
        txtTimKiem.setBorderRadius(txtRadius);
        //set chiều cao  và font size của row header
        tblDonDatVe.getTableHeader().setPreferredSize(new Dimension(getWidth(), 40));
        tblDonDatVe.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));

        cmbChonCot.addItem("Tên khách hàng");
        cmbChonCot.addItem("Mã đơn đặt");

        cmbTrangThai.addItem("Trang thai 1");
        cmbTrangThai.addItem("Trang thai 2");

        btnLamMoi.setBackground(colorBtn);

    }

    private void setSizeColumn() {
        tblDonDatVe.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblDonDatVe.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblDonDatVe.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblDonDatVe.getColumnModel().getColumn(4).setPreferredWidth(40);
        tblDonDatVe.getColumnModel().getColumn(6).setPreferredWidth(320);
    }

    private void loadDataForm() {

        TrangThaiDonDat[] trangThais = TrangThaiDonDat.values();

        for (TrangThaiDonDat i : trangThais) {
            cmbTrangThai.addItem(i.getTrangThai());
        }

    }

//    private void searchHandle() {
//
//        txtTimKiem.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                ((DefaultTableModel) tblDonDatVe.getModel()).setRowCount(0);
//                loadPage();
//
//                loadDataTable(pnlPage.getCurrentIndex());
//            }
//        });
//
//        cmbChonCot.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ((DefaultTableModel) tblDonDatVe.getModel()).setRowCount(0);
//                loadPage();
//
//                loadDataTable(pnlPage.getCurrentIndex());
//            }
//        });
//        cmbTrangThai.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ((DefaultTableModel) tblDonDatVe.getModel()).setRowCount(0);
//                loadPage();
//
//                loadDataTable(pnlPage.getCurrentIndex());
//            }
//        });
//
//        jdcNgayDat.addPropertyChangeListener(new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                ((DefaultTableModel) tblDonDatVe.getModel()).setRowCount(0);
//                loadPage();
//                loadDataTable(pnlPage.getCurrentIndex());
//            }
//        });
//    }

    private void loadDataTable(int numPage) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String textSearch = txtTimKiem.getText().trim();
                String option = (String) cmbChonCot.getSelectedItem();

//                TrangThaiChuyenDi trangThai = TrangThaiChuyenDi.getValueTrangThaiChuyenDi(cmbTrangThai.getSelectedItem().toString());
//                PhuongTien phuongTien = PhuongTien.getValuePhuongTien(cmbPhuongTien.getSelectedItem().toString());
                TrangThaiDonDat trangThai = TrangThaiDonDat.getValueTrangThaiDonDat(cmbTrangThai.getSelectedItem().toString());
                String ngayDat = "";

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (jdcNgayDat.getDate() != null) {

                    ngayDat = sdf.format(jdcNgayDat.getDate());
                }

                List<DonDatVe> listRow;
				try {
					listRow = datVeImpl.searchDonDatVes(textSearch, option, trangThai, ngayDat, numPage);
					if (listRow != null) {
                    ((DefaultTableModel) tblDonDatVe.getModel()).setRowCount(0);
                    listRow.forEach(i -> {
//                        tblDonDatVe.addRow(new DonDatVe(i.getNgayDat(), i.getKhachHang(), i.getChuyenDuLich()).convertToRowTable());
                    });
                    tblDonDatVe.repaint();
                    tblDonDatVe.revalidate();
                }
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }

        }).start();

    }

//    private void loadPage() {
//        String textSearch = txtTimKiem.getText().trim();
//        String option = (String) cmbChonCot.getSelectedItem();
//        String gioiTinh = (String) cmbGioiTinh.getSelectedItem();
//        String ngaySinh = "";
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        if (jdcNgaySinh.getDate() != null) {
//
//            ngaySinh = sdf.format(jdcNgaySinh.getDate());
//        }
//
//        int row = datVeImpl.getSoLuongDDV(textSearch, option, TrangThaiDonDat.HOAN_THANH, ngaySinh)
//
//        int x = row % 20 == 0 ? row / 20 : (row / 20) + 1;
//        if (x == 0) {
//            x = 1;
//        }
//
//        pnlPage.init(x);
//    }

//    private void tblDonDatVeHandle() {
//        pnlPage.addEventPagination(new EventPagination() {
//            @Override
//            public void onClick(int pageClick) {
//                loadDataTable(pageClick);
//            }
//        });
//
//        loadPage();
//    }

    private void btnLamMoiHandle() {
        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
    }

    private void clearForm() {

        txtTimKiem.setText("");
        cmbChonCot.setSelectedIndex(0);
        cmbTrangThai.setSelectedIndex(1);
        jdcNgayDat.setDate(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new com.huyhoang.swing.panel.PanelShadow();
        txtTimKiem = new com.huyhoang.swing.textfield.MyTextField();
        cmbChonCot = new javax.swing.JComboBox<>();
        cmbTrangThai = new javax.swing.JComboBox<>();
        jdcNgayDat = new com.toedter.calendar.JDateChooser();
        btnLamMoi = new javax.swing.JButton();
        pnlCenter = new com.huyhoang.swing.panel.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonDatVe = new gui.table2.MyTable();
        pnlPage = new gui.table2.PanelPage();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbChonCot.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbChonCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbChonCotActionPerformed(evt);
            }
        });

        cmbTrangThai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTrangThaiActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLamMoi.setText("Làm mới");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbChonCot, 0, 135, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jdcNgayDat, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(cmbTrangThai, 0, 135, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jdcNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTopLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbChonCot, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));

        tblDonDatVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đơn đặt", "Khách hàng", "Ngày đặt", "Số lượng", "Trạng thái", "Chuyến đi", "Hướng dẫn viên", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDonDatVe.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tblDonDatVe.setRowHeight(40);
        jScrollPane1.setViewportView(tblDonDatVe);
        if (tblDonDatVe.getColumnModel().getColumnCount() > 0) {
            tblDonDatVe.getColumnModel().getColumn(6).setHeaderValue("Hướng dẫn viên");
        }

        pnlPage.setOpaque(false);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(pnlPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbChonCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbChonCotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbChonCotActionPerformed

    private void cmbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JComboBox<String> cmbChonCot;
    private javax.swing.JComboBox<String> cmbTrangThai;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcNgayDat;
    private com.huyhoang.swing.panel.PanelShadow pnlCenter;
    private gui.table2.PanelPage pnlPage;
    private com.huyhoang.swing.panel.PanelShadow pnlTop;
    private gui.table2.MyTable tblDonDatVe;
    private com.huyhoang.swing.textfield.MyTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
