package gui.form;

import dao.DiaChi_DAO;
import dao.LoaiChuyenDi_DAO;
import dao.impl.DiaChiImpl;
import dao.impl.LoaiChuyenDiImpl;
import gui.component.BoxType;
import model.LoaiChuyenDi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class Search extends javax.swing.JPanel {

    private LoaiChuyenDi_DAO loaiChuyenDi_DAO;
    private DiaChi_DAO diaChi_DAO;

    public Search() throws RemoteException, MalformedURLException, NotBoundException {
        this.loaiChuyenDi_DAO = (LoaiChuyenDi_DAO) Naming.lookup("rmi://localhost:1099/loaiChuyenDi_DAO");
        this.diaChi_DAO = (DiaChi_DAO) Naming.lookup("rmi://localhost:1099/diaChi_DAO");
        initComponents();
        buildDisplay();
    }

    private void buildDisplay() {
        loadDataForm();
        createMap();
    }

    private void loadDataForm() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DefaultComboBoxModel<String> cmbDiemDenModel = new DefaultComboBoxModel<>();
                try {
					cmbDiemDenModel.addAll(diaChi_DAO.getAllTinhThanh());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                cmbDiemDen.addItem("Hãy chọn điểm đến");
                cmbDiemDen.setModel(cmbDiemDenModel);
            }
        }).start();

        for (int i = 1; i <= 31; i++) {
            cmbNgay.addItem(i);
        }

        for (int i = 1; i <= 12; i++) {
            cmbThang.addItem(i);
        }

        cmbNgay.setSelectedIndex(new Date().getDay());
        cmbThang.setSelectedIndex(new Date().getMonth());
    }

    private void createMap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LoaiChuyenDi> dsChuyenDi = null;
				try {
					dsChuyenDi = loaiChuyenDi_DAO.getDsLoaiChuyenDi();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                if (dsChuyenDi != null) {
                    for (LoaiChuyenDi loaiChuyenDi : dsChuyenDi) {
                        BoxType boxType = new BoxType(loaiChuyenDi);
                        map1.addBox(boxType, 200, 200);
                    }
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        map1 = new gui.component.Map();
        jPanel1 = new javax.swing.JPanel();
        panelRoundLine1 = new com.huyhoang.swing.panel.PanelRoundLine();
        cmbDiemDi = new com.huyhoang.swing.combobox.Combobox<>();
        jLabel1 = new javax.swing.JLabel();
        panelRoundLine2 = new com.huyhoang.swing.panel.PanelRoundLine();
        cmbDiemDen = new com.huyhoang.swing.combobox.Combobox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panelRoundLine3 = new com.huyhoang.swing.panel.PanelRoundLine();
        jLabel5 = new javax.swing.JLabel();
        cmbNgay = new com.huyhoang.swing.combobox.Combobox<>();
        cmbThang = new com.huyhoang.swing.combobox.Combobox<>();
        cmbNam = new com.huyhoang.swing.combobox.Combobox<>();
        panelRoundLine4 = new com.huyhoang.swing.panel.PanelRoundLine();
        jLabel7 = new javax.swing.JLabel();
        cmbSoNgay = new com.huyhoang.swing.combobox.Combobox();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(57, 20, 0, 20));
        setOpaque(false);

        map1.setTitle("Duyệt tìm tất cả");

        jPanel1.setOpaque(false);

        panelRoundLine1.setBackground(new java.awt.Color(255, 255, 255));
        panelRoundLine1.setBorderColor(new java.awt.Color(40, 40, 40));
        panelRoundLine1.setBorderRadius(20);

        cmbDiemDi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thành phố Hồ Chí Minh", "Thủ đô Hà Nội", "Thành phố Đà Nẵng" }));
        cmbDiemDi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbDiemDi.setLabeText("Điểm đi");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/location.png"))); // NOI18N

        javax.swing.GroupLayout panelRoundLine1Layout = new javax.swing.GroupLayout(panelRoundLine1);
        panelRoundLine1.setLayout(panelRoundLine1Layout);
        panelRoundLine1Layout.setHorizontalGroup(
            panelRoundLine1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRoundLine1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDiemDi, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRoundLine1Layout.setVerticalGroup(
            panelRoundLine1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLine1Layout.createSequentialGroup()
                .addGroup(panelRoundLine1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoundLine1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmbDiemDi, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRoundLine1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );

        panelRoundLine2.setBackground(new java.awt.Color(255, 255, 255));
        panelRoundLine2.setBorderColor(new java.awt.Color(40, 40, 40));
        panelRoundLine2.setBorderRadius(20);

        cmbDiemDen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hãy chọn điểm đến" }));
        cmbDiemDen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbDiemDen.setLabeText("Điểm đến");
        cmbDiemDen.setLightWeightPopupEnabled(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/location.png"))); // NOI18N

        javax.swing.GroupLayout panelRoundLine2Layout = new javax.swing.GroupLayout(panelRoundLine2);
        panelRoundLine2.setLayout(panelRoundLine2Layout);
        panelRoundLine2Layout.setHorizontalGroup(
            panelRoundLine2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRoundLine2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDiemDen, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRoundLine2Layout.setVerticalGroup(
            panelRoundLine2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLine2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbDiemDen, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRoundLine2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18))
        );

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/direction.png"))); // NOI18N

        panelRoundLine3.setBackground(new java.awt.Color(255, 255, 255));
        panelRoundLine3.setBorderColor(new java.awt.Color(40, 40, 40));
        panelRoundLine3.setBorderRadius(20);
        panelRoundLine3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/calendar.png"))); // NOI18N

        cmbNgay.setLabeText("Ngày");

        cmbThang.setLabeText("Tháng");

        cmbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022" }));
        cmbNam.setLabeText("Năm");

        javax.swing.GroupLayout panelRoundLine3Layout = new javax.swing.GroupLayout(panelRoundLine3);
        panelRoundLine3.setLayout(panelRoundLine3Layout);
        panelRoundLine3Layout.setHorizontalGroup(
            panelRoundLine3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLine3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRoundLine3Layout.setVerticalGroup(
            panelRoundLine3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLine3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRoundLine3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRoundLine3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelRoundLine4.setBackground(new java.awt.Color(255, 255, 255));
        panelRoundLine4.setBorderColor(new java.awt.Color(40, 40, 40));
        panelRoundLine4.setBorderRadius(20);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/calendar.png"))); // NOI18N

        cmbSoNgay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1-3 ngày", "4-7 ngày", "8-14 ngày", "Trên 14 ngày" }));
        cmbSoNgay.setLabeText("Số ngày");

        javax.swing.GroupLayout panelRoundLine4Layout = new javax.swing.GroupLayout(panelRoundLine4);
        panelRoundLine4.setLayout(panelRoundLine4Layout);
        panelRoundLine4Layout.setHorizontalGroup(
            panelRoundLine4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLine4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRoundLine4Layout.setVerticalGroup(
            panelRoundLine4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundLine4Layout.createSequentialGroup()
                .addGroup(panelRoundLine4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoundLine4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel7))
                    .addGroup(panelRoundLine4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmbSoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelRoundLine3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelRoundLine4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelRoundLine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelRoundLine2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelRoundLine1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelRoundLine2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelRoundLine3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRoundLine4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.huyhoang.swing.combobox.Combobox<String> cmbDiemDen;
    private com.huyhoang.swing.combobox.Combobox<String> cmbDiemDi;
    private com.huyhoang.swing.combobox.Combobox<String> cmbNam;
    private com.huyhoang.swing.combobox.Combobox<Integer> cmbNgay;
    private com.huyhoang.swing.combobox.Combobox cmbSoNgay;
    private com.huyhoang.swing.combobox.Combobox<Integer> cmbThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private gui.component.Map map1;
    private com.huyhoang.swing.panel.PanelRoundLine panelRoundLine1;
    private com.huyhoang.swing.panel.PanelRoundLine panelRoundLine2;
    private com.huyhoang.swing.panel.PanelRoundLine panelRoundLine3;
    private com.huyhoang.swing.panel.PanelRoundLine panelRoundLine4;
    // End of variables declaration//GEN-END:variables
}
