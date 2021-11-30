package gui.dialog;

import dao.DonDatVe_DAO;
import dao.impl.DonDatVeImpl;
import dao.impl.HuongDanVienImpl;
import gui.MainFrame;
import gui.component.Message;
import model.ChiTietThamQuan;
import model.ChuyenDuLich;
import model.DonDatVe;
import model.HuongDanVien;
import com.huyhoang.swing.autoid.AutoID;
import com.huyhoang.swing.button.ButtonBadges;
import com.huyhoang.swing.event.EventSwitchSelected;
import com.huyhoang.swing.image.PictureBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class DialogBookTour extends javax.swing.JDialog {
    
    private boolean show;
    private Animator start;
    private DonDatVe_DAO donDatVe_DAO;
    private DonDatVe donDatVe;
    private HuongDanVien huongDanVien;
    private boolean them;
    private final DecimalFormat df = new DecimalFormat("#,##0đ‘");

    public boolean isThem() {
        return them;
    }

    public DialogBookTour(java.awt.Frame parent, ChuyenDuLich chuyenDuLich) throws RemoteException, MalformedURLException, NotBoundException {
        super(parent, true);
        donDatVe_DAO = (DonDatVe_DAO) Naming.lookup("rmi://localhost:1099/donDatVe_DAO");
        huongDanVien = new HuongDanVienImpl().getHuongDanVienRandom();
        String maxID = donDatVe_DAO.getMaxID();
        String maDon;
        if (maxID == null) {
            maDon = "HD0000001";
        } else {
            maDon = AutoID.generateId(maxID, "HD");
        }
        this.donDatVe = new DonDatVe(maDon, MainFrame.khachHang, chuyenDuLich);
        donDatVe.setSoLuong(1);
        initComponents();
        buildDisplay();
    }
    
    private void buildDisplay() {
        start();
        createSlide();
        createPanelMessage();
        loadDataForm();
    }
    
    private void loadDataForm() {
        if(donDatVe.getHuongDanVien() != null) {
            jLabel5.setText(df.format(donDatVe.getHuongDanVien().getPhiHDV()));
        } else {
            jLabel5.setText("0đ‘");
        }
        
        jLabel7.setText(donDatVe.getSoLuong() + "X" + df.format(donDatVe.getChuyenDuLich().getGiaChuyenDi()));
        jLabel9.setText(df.format(donDatVe.getThanhTien()));
    }
    
    private void createSlide() {
        String diaDanh = "[";
        String tinh = "Du lịch ";
        List<ChiTietThamQuan> dsChiTietThamQuan = donDatVe.getChuyenDuLich().getDsChiTietThamQuan();
        if (dsChiTietThamQuan.size() > 0) {
            for (int i = 0; i < dsChiTietThamQuan.size(); i++) {
                slideShowTransparent1.addImage(new PictureBox(new ImageIcon(dsChiTietThamQuan.get(i).getAnhDiaDanh())));
                if (i == (dsChiTietThamQuan.size() - 1)) {
                    diaDanh = diaDanh + dsChiTietThamQuan.get(i).getDiaDanh().getTenDiaDanh() + "]";
                    tinh = tinh + dsChiTietThamQuan.get(i).getDiaDanh().getTinh();
                } else {
                    diaDanh = diaDanh + dsChiTietThamQuan.get(i).getDiaDanh().getTenDiaDanh() + " - ";
                    tinh = tinh + dsChiTietThamQuan.get(i).getDiaDanh().getTinh() + " - ";
                }
            }
        }
        slideShowTransparent1.start();
        tenTour.setText(tinh);
        tenDiaDanh.setText(diaDanh);
        
        switchButton1.addEventSelected(new EventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                if(selected) {
                    donDatVe.setHuongDanVien(huongDanVien);
                    loadDataForm();
                } else {
                    donDatVe.setHuongDanVien(null);
                    loadDataForm();
                }
            }
        });
    }
    
    private void createPanelMessage() {
        jPanel1.setLayout(new MigLayout("fillx, insets 0", "[fill]"));
        ButtonBadges btnDat = new ButtonBadges();
        btnDat.setText("Đặt ngay");
        btnDat.setForeground(Color.WHITE);
        btnDat.setFont(new Font("Segoe ui", Font.BOLD, 14));
        btnDat.setBackground(new Color(29, 185, 84));
        btnDat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
					them = donDatVe_DAO.addDonDatVe(donDatVe);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                dispose();
            }
        });
        jPanel1.add(btnDat, "w 120!, h 40!, right");
    }
    
    public void showMessage(String text) {
        Message ms = new Message();
        ms.showMessage(text);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    jPanel1.add(ms, "pos 0.5al 0 n n, h 40!", 0); // ChÃ¨n thÃªm message vÃ o panel login
                    ms.setVisible(true);
                    jPanel1.repaint();
                }
            }
            
            @Override
            public void timingEvent(float fraction) {
                if (ms.isShow()) {
                    ms.show(1f - fraction);
                } else {
                    ms.show(fraction);
                }
            }
            
            @Override
            public void end() {
                if (ms.isShow()) {
                    jPanel1.remove(ms);
                    jPanel1.repaint();
                    jPanel1.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator2 = new Animator(300, target);
        animator2.setResolution(0);
        animator2.setAcceleration(0.5f);
        animator2.setDeceleration(0.5f);
        animator2.start();
        
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                animator2.start();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }).start();
    }
    
    private void start() {
        setBackground(new Color(0, 0, 0, 0));
        setOpacity(0);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    setOpacity(fraction);
                } else {
                    setOpacity(1f - fraction);
                }
            }
            
            @Override
            public void end() {
                if (!show) {
                    dispose();
                }
            }
        };
        start = new Animator(200, target);
        start.setResolution(0);
        start.setAcceleration(0.5f);
    }
    
    @Override
    public void setVisible(boolean b) {
        show = b;
        if (show) {
            start.start();
        }
        super.setVisible(b);
    }
    
    private void close() {
        if (start.isRunning()) {
            start.stop();
        }
        show = false;
        start.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layerPaneShadow1 = new com.huyhoang.swing.panel.LayerPaneShadow();
        jLabel1 = new javax.swing.JLabel();
        switchButton1 = new com.huyhoang.swing.button.SwitchButton();
        buttonBadges1 = new com.huyhoang.swing.button.ButtonBadges();
        jLabel2 = new javax.swing.JLabel();
        buttonBadges2 = new com.huyhoang.swing.button.ButtonBadges();
        jLabel3 = new javax.swing.JLabel();
        slideShowTransparent1 = new com.huyhoang.swing.slideshow.SlideShowTransparent();
        tenTour = new com.huyhoang.swing.label.WrapLabel();
        tenDiaDanh = new com.huyhoang.swing.label.WrapLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        button1 = new com.huyhoang.swing.button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        layerPaneShadow1.setBackground(new java.awt.Color(19, 19, 19));
        layerPaneShadow1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 20, 10));
        layerPaneShadow1.setBorderRadius(10);
        layerPaneShadow1.setShadowOpacity(0.3F);
        layerPaneShadow1.setShadowSize(10);
        layerPaneShadow1.setShadowType(com.huyhoang.swing.graphics.ShadowType.BOT);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SÃ´Ì� lÆ°Æ¡Ì£ng");

        switchButton1.setBackground(new java.awt.Color(29, 185, 84));

        buttonBadges1.setBackground(new java.awt.Color(29, 185, 84));
        buttonBadges1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus.png"))); // NOI18N
        buttonBadges1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBadges1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("1");

        buttonBadges2.setBackground(new java.awt.Color(29, 185, 84));
        buttonBadges2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/minus.png"))); // NOI18N
        buttonBadges2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBadges2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Hướng dẫn viên");

        tenTour.setForeground(new java.awt.Color(255, 255, 255));
        tenTour.setText("Du lịch");
        tenTour.setToolTipText("");
        tenTour.setVAlignStyle(0.0F);
        tenTour.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        tenDiaDanh.setForeground(new java.awt.Color(255, 255, 255));
        tenDiaDanh.setText("[địa danh]");
        tenDiaDanh.setToolTipText("");
        tenDiaDanh.setVAlignStyle(0.0F);
        tenDiaDanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Phí hướng dẫn viên");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("0đ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Người");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("5 X 2,000,00đ‘");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tổng cộng");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("10,000,000đ");

        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );

        layerPaneShadow1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(switchButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(buttonBadges1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(buttonBadges2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(slideShowTransparent1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(tenTour, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(tenDiaDanh, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layerPaneShadow1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layerPaneShadow1Layout = new javax.swing.GroupLayout(layerPaneShadow1);
        layerPaneShadow1.setLayout(layerPaneShadow1Layout);
        layerPaneShadow1Layout.setHorizontalGroup(
            layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerPaneShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layerPaneShadow1Layout.createSequentialGroup()
                        .addGroup(layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(switchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layerPaneShadow1Layout.createSequentialGroup()
                                .addComponent(buttonBadges2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layerPaneShadow1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layerPaneShadow1Layout.createSequentialGroup()
                        .addComponent(slideShowTransparent1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tenTour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tenDiaDanh, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(jSeparator1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layerPaneShadow1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(layerPaneShadow1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)))
                .addContainerGap())
        );
        layerPaneShadow1Layout.setVerticalGroup(
            layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerPaneShadow1Layout.createSequentialGroup()
                .addGroup(layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layerPaneShadow1Layout.createSequentialGroup()
                        .addComponent(slideShowTransparent1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layerPaneShadow1Layout.createSequentialGroup()
                        .addComponent(tenTour, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tenDiaDanh, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGroup(layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonBadges1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonBadges2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(switchButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layerPaneShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        button1.setAlpha(0.0F);
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layerPaneShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(layerPaneShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        close();
    }//GEN-LAST:event_button1ActionPerformed

    private void buttonBadges1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBadges1ActionPerformed
        int soLuong = Integer.valueOf(jLabel2.getText().trim()) + 1;
        if (soLuong <= donDatVe.getChuyenDuLich().getSoLuong()) {
            jLabel2.setText(String.valueOf(soLuong));
            jLabel2.repaint();
            donDatVe.setSoLuong(soLuong);
            loadDataForm();
        } else {
            showMessage("SÃ´Ì� lÆ°Æ¡Ì£ng tÃ´Ì�i Ä‘a chiÌ‰ " + donDatVe.getChuyenDuLich().getSoLuong());
        }
    }//GEN-LAST:event_buttonBadges1ActionPerformed

    private void buttonBadges2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBadges2ActionPerformed
        int soLuong = Integer.valueOf(jLabel2.getText().trim()) - 1;
        if (soLuong > 0) {
            jLabel2.setText(String.valueOf(soLuong));
            jLabel2.repaint();
            donDatVe.setSoLuong(soLuong);
            loadDataForm();
        } else {
            showMessage("YÃªu cÃ¢Ì€u tÃ´Ì‰i thiÃªÌ‰u 1 ngÆ°Æ¡Ì€i");
        }
    }//GEN-LAST:event_buttonBadges2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.huyhoang.swing.button.Button button1;
    private com.huyhoang.swing.button.ButtonBadges buttonBadges1;
    private com.huyhoang.swing.button.ButtonBadges buttonBadges2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private com.huyhoang.swing.panel.LayerPaneShadow layerPaneShadow1;
    private com.huyhoang.swing.slideshow.SlideShowTransparent slideShowTransparent1;
    private com.huyhoang.swing.button.SwitchButton switchButton1;
    private com.huyhoang.swing.label.WrapLabel tenDiaDanh;
    private com.huyhoang.swing.label.WrapLabel tenTour;
    // End of variables declaration//GEN-END:variables
}
