/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import com.huyhoang.swing.panel.PanelShadow;
import com.huyhoang.swing.slideshow.EventPagination;
import com.huyhoang.swing.textfield.MyTextField;

import dao.ChuyenDuLich_DAO;
import dao.LoaiChuyenDi_DAO;
import dao.impl.ChuyenDuLichImpl;
import dao.impl.LoaiChuyenDiImpl;
import model.ChuyenDuLich;
import model.DongTour;
import model.LoaiChuyenDi;
import model.PhuongTien;
import model.TrangThaiChuyenDi;

/**
 *
 * @author NGUYE
 */
public class PanelTour extends javax.swing.JPanel {

    private ChuyenDuLich_DAO chuyenDuLichImpl;
    private LoaiChuyenDi_DAO loaiChuyenDiImpl;

    private List<ChuyenDuLich> listCDL;
    private List<LoaiChuyenDi> listChuyenDis;
    private List<TrangThaiChuyenDi> listTrangThais;

    private DefaultComboBoxModel<LoaiChuyenDi> cmbLoaiChuyenDiModel = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<TrangThaiChuyenDi> cmbTrangThaiModel = new DefaultComboBoxModel<>();
    private boolean trangThaiTimKiem = false;

    public PanelTour() throws RemoteException, MalformedURLException, NotBoundException {
        initComponents();
        setPropertiesForm();
        setSizeColumn();

        chuyenDuLichImpl = (ChuyenDuLich_DAO) Naming.lookup("rmi://localhost:1099/chuyendulich_dao"); 
        loaiChuyenDiImpl = (LoaiChuyenDi_DAO) Naming.lookup("rmi://localhost:1099/loaiChuyenDi_DAO");

        loadDataForm();
        tblTourHandle();
        searchHandle();

        loadDataTable(pnlPage2.getCurrentIndex()); //load trang đầu tiên
        System.out.println("trang dau tien" + pnlPage2.getCurrentIndex());

    }

    private void setPropertiesForm() {
        int txtRadius = 10;
        Color colorBtn = new Color(184, 238, 241);

        //set chiều cao  và font size của row header
        tblTour.getTableHeader().setPreferredSize(new Dimension(getWidth(), 40));
        tblTour.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));

        txtTimKiem.setBorderLine(true);
        txtTimKiem.setBorderRadius(txtRadius);

        cmbLoaiChuyenDi.setToolTipText("Loại chuyến di");

        jdcBatDau.setToolTipText("Ngày bắt đầu");
        jdcBatDau.setDateFormatString("dd-MM-yyyy");

        jdcKetThuc.setToolTipText("Ngày kết thúc");
        jdcKetThuc.setDateFormatString("dd-MM-yyyy");

        jdcNgayTao.setToolTipText("Ngày tạo");
        jdcNgayTao.setDateFormatString("dd-MM-yyyy");

    }

    private void setSizeColumn() {
        tblTour.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblTour.getColumnModel().getColumn(3).setPreferredWidth(20);
        tblTour.getColumnModel().getColumn(4).setPreferredWidth(80);
        tblTour.getColumnModel().getColumn(6).setPreferredWidth(30);
        tblTour.getColumnModel().getColumn(7).setPreferredWidth(100);
    }

    private void loadDataForm() throws RemoteException {

        listChuyenDis = loaiChuyenDiImpl.getLoaiChuyenDis();
        cmbLoaiChuyenDiModel.addAll(listChuyenDis);

        TrangThaiChuyenDi[] trangThais = TrangThaiChuyenDi.values();

        for (TrangThaiChuyenDi i : trangThais) {
//            cmbTrangThaiModel.addElement(i);
            cmbTrangThai.addItem(i.getTrangThai());
        }

        PhuongTien[] phuongTiens = PhuongTien.values();
        for (PhuongTien i : phuongTiens) {
            cmbPhuongTien.addItem(i.getPhuongTien());
        }
    }

    private void searchHandle() {

        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                try {
					loadPage();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                loadDataTable(pnlPage2.getCurrentIndex());
            }
        });

        cmbLoaiChuyenDi.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                try {
					loadPage();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                loadDataTable(pnlPage2.getCurrentIndex());
            }
        });

        cmbTrangThai.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
					loadPage();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                loadDataTable(pnlPage2.getCurrentIndex());
            }
        });
        cmbPhuongTien.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
					loadPage();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                loadDataTable(pnlPage2.getCurrentIndex());
            }
        });

        jdcBatDau.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
					loadPage();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                loadDataTable(pnlPage2.getCurrentIndex());
            }
        });
        jdcKetThuc.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
					loadPage();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

                loadDataTable(pnlPage2.getCurrentIndex());
            }
        });
        jdcNgayTao.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
					loadPage();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println("ngày tạo index:" + pnlPage2.getCurrentIndex());
                loadDataTable(pnlPage2.getCurrentIndex());

            }
        });

    }

    public void btnThemHandle(ActionListener actionListener) {
        btnThem.addActionListener(actionListener);
    }

    private void loadDataTable(int numPage) {
        ((DefaultTableModel) tblTour.getModel()).setRowCount(0);
        new Thread(new Runnable() {
            @Override
            public void run() {

                String textSearch = txtTimKiem.getText().trim();

                TrangThaiChuyenDi trangThai = TrangThaiChuyenDi.getValueTrangThaiChuyenDi(cmbTrangThai.getSelectedItem().toString());
                PhuongTien phuongTien = PhuongTien.getValuePhuongTien(cmbPhuongTien.getSelectedItem().toString());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String ngayBatDau = "";
                if (jdcBatDau.getDate() != null) {
                    ngayBatDau = df.format(jdcBatDau.getDate());
                }
                String ngayKetThuc = "";
                if (jdcKetThuc.getDate() != null) {
                    ngayKetThuc = df.format(jdcKetThuc.getDate());
                }
                String ngayTao = "";
                if (jdcNgayTao.getDate() != null) {
                    ngayTao = df.format(jdcNgayTao.getDate());
                }

                List<ChuyenDuLich> listRow = null;
				try {
					listRow = chuyenDuLichImpl.searchChuyenDuLichs(textSearch, cmbLoaiChuyenDiModel.getElementAt(cmbLoaiChuyenDi.getSelectedIndex()),
					        trangThai, phuongTien, ngayBatDau, ngayKetThuc, ngayTao, numPage);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                if (listRow != null) {

                    listRow.forEach(i -> {
                        tblTour.addRow(new ChuyenDuLich(i.getMaChuyen(), i.getGiaChuyenDi(),
                                i.getLoaiChuyenDi(), i.getNgayTao(), i.getNgayKetThuc(),
                                i.getNgayKhoiHanh(), i.getTrangThai(), i.getPhuongTien(),
                                DongTour.GIA_TOT, i.getMoTa(), i.getSoLuong(), i.getNhanVien(), i.getNoiKhoiHanh()).convertToRowTable());
                    });
                    tblTour.repaint();
                    tblTour.revalidate();
                }
            }

        }).start();

    }

    private void loadPage() throws RemoteException {
        String textSearch = txtTimKiem.getText().trim();
        TrangThaiChuyenDi trangThai = TrangThaiChuyenDi.getValueTrangThaiChuyenDi(cmbTrangThai.getSelectedItem().toString());
        PhuongTien phuongTien = PhuongTien.getValuePhuongTien(cmbPhuongTien.getSelectedItem().toString());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String ngayBatDau = "";
        if (jdcBatDau.getDate() != null) {
            ngayBatDau = df.format(jdcBatDau.getDate());
        }
        String ngayKetThuc = "";
        if (jdcKetThuc.getDate() != null) {
            ngayKetThuc = df.format(jdcKetThuc.getDate());
        }
        String ngayTao = "";
        if (jdcNgayTao.getDate() != null) {
            ngayTao = df.format(jdcNgayTao.getDate());
        }

        int row = 0;
		try {
			row = chuyenDuLichImpl.soLuongSearch(textSearch, cmbLoaiChuyenDiModel.getElementAt(cmbLoaiChuyenDi.getSelectedIndex()),
			        trangThai, phuongTien, ngayBatDau, ngayKetThuc, ngayTao);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("row " + row);
//        System.out.println(0 % 20);
        System.out.println(0 / 20);
//        System.out.println(10 % 20);

        System.out.println(row % 20 == 0 ? row / 20 : (row / 20) + 1);

        int x = row % 20 == 0 ? row / 20 : (row / 20) + 1;
        if (x == 0) {
            x = 1;
        }

        pnlPage2.init(x);
    }

    private void tblTourHandle() throws RemoteException {
        pnlPage2.addEventPagination(new EventPagination() {
            @Override
            public void onClick(int pageClick) {
                loadDataTable(pageClick);
            }
        });

        loadPage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new PanelShadow();
        btnThem = new javax.swing.JButton();
        txtTimKiem = new MyTextField();
        cmbLoaiChuyenDi = new javax.swing.JComboBox<>(cmbLoaiChuyenDiModel);
        cmbTrangThai = new javax.swing.JComboBox<>();
        cmbPhuongTien = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jdcBatDau = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jdcKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jdcNgayTao = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        pnlCenter = new PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTour = new gui.table2.MyTable();
        pnlPage2 = new gui.table2.PanelPage();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setBackground(new java.awt.Color(204, 255, 204));
        btnThem.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtTimKiem.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTimKiem.setPreferredSize(new java.awt.Dimension(150, 39));

        cmbLoaiChuyenDi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbLoaiChuyenDi.setMaximumSize(new java.awt.Dimension(100, 3276));
        cmbLoaiChuyenDi.setPreferredSize(new java.awt.Dimension(30, 29));
        cmbLoaiChuyenDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLoaiChuyenDiActionPerformed(evt);
            }
        });

        cmbTrangThai.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trạng thái" }));
        cmbTrangThai.setPreferredSize(new java.awt.Dimension(20, 29));

        cmbPhuongTien.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbPhuongTien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phương tiện" }));
        cmbPhuongTien.setMaximumSize(new java.awt.Dimension(100, 32767));
        cmbPhuongTien.setPreferredSize(new java.awt.Dimension(20, 29));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Ngày bắt đầu");

        jdcBatDau.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Ngày kết thúc");

        jdcKetThuc.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("Ngày tạo");

        jdcNgayTao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton1.setText("Làm mới");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addGap(52, 52, 52)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdcBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(cmbLoaiChuyenDi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdcKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(cmbTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlTopLayout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlTopLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlTopLayout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(pnlTopLayout.createSequentialGroup()
                            .addComponent(cmbPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jdcNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbLoaiChuyenDi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jdcKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTopLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jdcBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));

        tblTour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã tour", "Bắt đầu", "Kết thúc", "Số lượng", "Loại chuyến đi", "Dòng tour", "Phương tiện", "Giá", "Trạng thái", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTour.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        tblTour.setRowHeight(40);
        jScrollPane1.setViewportView(tblTour);

        pnlPage2.setOpaque(false);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(pnlPage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbLoaiChuyenDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLoaiChuyenDiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLoaiChuyenDiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox cmbLoaiChuyenDi;
    private javax.swing.JComboBox<String> cmbPhuongTien;
    private javax.swing.JComboBox<String> cmbTrangThai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcBatDau;
    private com.toedter.calendar.JDateChooser jdcKetThuc;
    private com.toedter.calendar.JDateChooser jdcNgayTao;
    private PanelShadow pnlCenter;
    private gui.table2.PanelPage pnlPage2;
    private PanelShadow pnlTop;
    private gui.table2.MyTable tblTour;
    private MyTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
