/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.component;

import dao.impl.HuongDanVienImpl;
import model.HuongDanVien;
import com.huyhoang.*;
import com.huyhoang.swing.event.EventPagination;

import dao.HuongDanVien_DAO;

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
public class PanelHuongDanVien extends javax.swing.JPanel {

    private HuongDanVien_DAO huongDanVienImpl;

    public PanelHuongDanVien() throws MalformedURLException, RemoteException, NotBoundException {
        initComponents();
        setPropertiesForm();

        huongDanVienImpl = (HuongDanVien_DAO) Naming.lookup("rmi://localhost:1099/huongDanVien_DAO");

        setSizeColumn();

        tblHDVHandle();

        searchHandle();

        loadDataTable(pnlPage.getCurrentIndex());
        
        btnLamMoiHandle();
    }

    private void setPropertiesForm() {
        int txtRadius = 10;
        int cmbRadius = 10;
        int btnRadius = 10;
        Color colorBtn = new Color(184, 238, 241);
        //set chiều cao  và font size của row header
        tblHDV.getTableHeader().setPreferredSize(new Dimension(getWidth(), 40));
        tblHDV.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));

        txtTimKiem.setBorderLine(true);
        txtTimKiem.setBorderRadius(txtRadius);

        cmbChonCot.addItem("Tên HDV");
        cmbChonCot.addItem("Mã HDV");
        cmbChonCot.addItem("Số điện thoại");
        cmbChonCot.addItem("Căn cước công dân");
        cmbChonCot.addItem("Email");
        
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");
        
        btnLamMoi.setBackground(colorBtn);
        btnThem.setBackground(colorBtn);

    }

    private void setSizeColumn() {
        tblHDV.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblHDV.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblHDV.getColumnModel().getColumn(2).setPreferredWidth(40);
        tblHDV.getColumnModel().getColumn(4).setPreferredWidth(40);
        tblHDV.getColumnModel().getColumn(6).setPreferredWidth(320);
    }

    private void searchHandle() {

        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ((DefaultTableModel) tblHDV.getModel()).setRowCount(0);
                try {
					loadPage();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                loadDataTable(pnlPage.getCurrentIndex());
            }
        });

        cmbGioiTinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((DefaultTableModel) tblHDV.getModel()).setRowCount(0);
                try {
					loadPage();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                loadDataTable(pnlPage.getCurrentIndex());
            }
        });

        jdcNgaySinh.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                ((DefaultTableModel) tblHDV.getModel()).setRowCount(0);
                try {
					loadPage();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                loadDataTable(pnlPage.getCurrentIndex());
            }
        });
    }

    private void loadDataTable(int numPage) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String textSearch = txtTimKiem.getText().trim();
                String option = (String) cmbChonCot.getSelectedItem();
                String gioiTinh = "";
                if (!"Giới tính".equals((String) cmbGioiTinh.getSelectedItem())) {
                    gioiTinh = (String) cmbGioiTinh.getSelectedItem();
                }
                String ngaySinh = "";

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (jdcNgaySinh.getDate() != null) {

                    ngaySinh = sdf.format(jdcNgaySinh.getDate());
                }

                List<HuongDanVien> listRow = null;
				try {
					listRow = huongDanVienImpl.searchHuongDanViens(textSearch, option, gioiTinh, ngaySinh, numPage);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                if (listRow != null) {
                    ((DefaultTableModel) tblHDV.getModel()).setRowCount(0);
                    listRow.forEach(i -> {
                        System.out.println(".run()" + i.getNgaySinh());
                        tblHDV.addRow(new HuongDanVien(i.getMaHDV(), i.getTenHDV(),
                                i.getcCCD(), i.isGioiTinh(), i.getNgaySinh(), i.getSoDienThoai(), i.getEmail(), i.getPhiHDV()).convertToRowTable());
                    });
                    tblHDV.repaint();
                    tblHDV.revalidate();
                }
            }

        }).start();

    }

    private void loadPage() throws RemoteException {
        String textSearch = txtTimKiem.getText().trim();
        String option = (String) cmbChonCot.getSelectedItem();
        String gioiTinh = (String) cmbGioiTinh.getSelectedItem();
        String ngaySinh = "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (jdcNgaySinh.getDate() != null) {

            ngaySinh = sdf.format(jdcNgaySinh.getDate());
        }

        int row = huongDanVienImpl.getSoLuongHDV(textSearch, option, gioiTinh, ngaySinh);

        int x = row % 20 == 0 ? row / 20 : (row / 20) + 1;
        if (x == 0) {
            x = 1;
        }

        pnlPage.init(x);
    }

    private void tblHDVHandle() throws RemoteException {
        pnlPage.addEventPagination(new EventPagination() {
            @Override
            public void onClick(int pageClick) {
                loadDataTable(pageClick);
            }
        });

        loadPage();
    }
    
    private void btnLamMoiHandle(){
        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         clearForm();
            }
        });
    }
    
    private void clearForm(){
        
        txtTimKiem.setText("");
        cmbChonCot.setSelectedIndex(0);
        cmbGioiTinh.setSelectedIndex(0);
        jdcNgaySinh.setDate(null);
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
        cmbGioiTinh = new javax.swing.JComboBox<>();
        jdcNgaySinh = new com.toedter.calendar.JDateChooser();
        btnThem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        pnlCenter = new com.huyhoang.swing.panel.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHDV = new gui.table2.MyTable();
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

        cmbGioiTinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giới tính" }));
        cmbGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGioiTinhActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThem.setText("Thêm");

        btnLamMoi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLamMoi.setText("Làm mới");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(cmbChonCot, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jdcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTopLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbChonCot, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));

        tblHDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã ", "Tên ", "Giới tính", "Ngày sinh", "Căn cước công dân", "Số điện thoại", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHDV.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        tblHDV.setRowHeight(40);
        tblHDV.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(tblHDV);

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

    private void cmbGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbGioiTinhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cmbChonCot;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcNgaySinh;
    private com.huyhoang.swing.panel.PanelShadow pnlCenter;
    private gui.table2.PanelPage pnlPage;
    private com.huyhoang.swing.panel.PanelShadow pnlTop;
    private gui.table2.MyTable tblHDV;
    private com.huyhoang.swing.textfield.MyTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
