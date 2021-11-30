/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.KhachHang_DAO;
import gui.component.Message;
import model.KhachHang;
import com.huyhoang.swing.button.ButtonBadges;
import com.huyhoang.swing.button.SwitchButton;
import com.huyhoang.swing.label.WrapLabel;
import com.huyhoang.swing.textfield.MyPasswordField;
import com.huyhoang.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Login extends javax.swing.JFrame {
    
    private int xx;
    private int yy;
    private boolean show;
    private Animator start;
    private ButtonBadges buttonBadges1;
    private ButtonBadges buttonBadges2;
    private javax.swing.JLabel jLabel1;
    private com.huyhoang.swing.button.SwitchButton switchButton1;
    private com.huyhoang.swing.textfield.MyPasswordField txtMatKhau;
    private com.huyhoang.swing.textfield.MyTextField txtSdt;
    private com.huyhoang.swing.label.WrapLabel wrapLabel1;
    private KhachHang_DAO khachHang_DAO;
    private KhachHang khachHang;
    
    public Login() throws RemoteException, MalformedURLException, NotBoundException {
        this.khachHang_DAO = (KhachHang_DAO) Naming.lookup("rmi://localhost:1099/khachHang_DAO");
        initComponents();
        loading.setVisible(false);
        jPanel1.setVisible(true);
        buildDisplay();
    }
    
    private void setTextWhenBack() {
        txtSdt.selectAll();
        txtSdt.requestFocus();
        txtMatKhau.setText("");
    }
    
    private void buildDisplay() {
        start();
        jPanel1.setLayout(new MigLayout("wrap", "push[center]push[center]push", "push[center]10[center]10[center]10[center]20[center]10[center]push"));
        createLogin();
    }
    
    private void createLogin() {
        txtSdt = new MyTextField();
        wrapLabel1 = new WrapLabel();
        jLabel1 = new JLabel();
        switchButton1 = new SwitchButton();
        buttonBadges1 = new ButtonBadges();
        buttonBadges2 = new ButtonBadges();
        txtMatKhau = new MyPasswordField();
        
        wrapLabel1.setForeground(new Color(255, 255, 255));
        wrapLabel1.setText("Đăng nhập để tiếp tục");
        wrapLabel1.setHAlignStyle(0.5F);
        wrapLabel1.setVAlignStyle(0.0F);
        wrapLabel1.setFont(new Font("Segoe UI", 1, 30)); // NOI18N
        jPanel1.add(wrapLabel1, "w 70%, h 100!, span 2");
        
        txtSdt.setForeground(new Color(255, 255, 255));
        txtSdt.setBackgroundColor(new Color(51, 51, 51));
        txtSdt.setCaretColor(new Color(255, 255, 255));
        txtSdt.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        txtSdt.setHint("Email hoặc số điện thoại");
        txtSdt.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/letter.png"))); // NOI18N
        jPanel1.add(txtSdt, "w 100%, span 2");
        
        txtMatKhau.setBackground(new Color(51, 51, 51));
        txtMatKhau.setForeground(new Color(255, 255, 255));
        txtMatKhau.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        txtMatKhau.setHint("Mật khẩu");
        txtMatKhau.setCaretColor(new Color(255, 255, 255));
        txtMatKhau.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtMatKhau.setSelectionColor(new Color(38, 117, 191));
        jPanel1.add(txtMatKhau, "w 100%, span 2");
        
        jLabel1.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new Color(200, 200, 200));
        jLabel1.setText("Nhớ mật khẩu");
        jPanel1.add(jLabel1);
        
        switchButton1.setBackground(new Color(29, 185, 84));
        jPanel1.add(switchButton1);
        
        buttonBadges1.setText("Đăng nhập");
        buttonBadges1.setFont(new Font("SansSerif", 1, 18)); // NOI18N
        buttonBadges1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String sdt = txtSdt.getText().trim();
                byte[] matKhau = String.valueOf(txtMatKhau.getPassword()).getBytes();
                if(sdt.length() <= 0) {
                    setTextWhenBack();
                    showMessage("Nhập email hoặc số điện thoại!");
                    return;
                }
                if(matKhau.length <= 0) {
                    setTextWhenBack();
                    showMessage("Nhập mật khẩu!");
                    return;
                }
                loading.setVisible(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            try {
								khachHang = khachHang_DAO.getKhachHangByLogin(sdt, matKhau);
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                            if(khachHang != null) {
                                new MainFrame(khachHang).setVisible(true);
                                dispose();
                                loading.setVisible(false);
                            } else {
                                loading.setVisible(false);
                                setTextWhenBack();
                                showMessage("Sai mật khẩu");
                            }
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NotBoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }
                }).start();
            }
        });
        jPanel1.add(buttonBadges1, "w 80%, h 40!, span 2");
        
        buttonBadges2.setText("Thoát");
        buttonBadges2.setFont(new Font("SansSerif", 1, 18)); // NOI18N
        buttonBadges2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                close();
            }
        });
        jPanel1.add(buttonBadges2, "w 80%, h 40!, span 2");
        
    }
    
    public void showMessage(String text) {
        Message ms = new Message();
        ms.showMessage(text);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    jPanel1.add(ms, "pos 0.5al 0", 0); // ChÃ¨n thÃªm message vÃ o panel login
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
                    System.exit(0);
                }
            }
        };
        start = new Animator(200, target);
        start.setResolution(0);
        start.setAcceleration(0.5f);
    }
    
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        setTextWhenBack();
        show = b;
        if (show) {
            start.start();
        }
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

        bg = new com.huyhoang.swing.panel.LayerPaneShadow();
        jPanel1 = new javax.swing.JPanel();
        loading = new gui.component.Loading();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(19, 19, 19));
        bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bg.setBorderRadius(20);
        bg.setShadowOpacity(0.3F);
        bg.setShadowSize(10);
        bg.setLayout(new java.awt.CardLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 40, 40, 40));
        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        bg.add(jPanel1, "card2");

        loading.setBackground(new java.awt.Color(255, 255, 255));
        loading.setAlpha(0.3F);
        loading.setBorderRadius(20);
        bg.setLayer(loading, javax.swing.JLayeredPane.POPUP_LAYER);
        bg.add(loading, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.huyhoang.swing.panel.LayerPaneShadow bg;
    private javax.swing.JPanel jPanel1;
    private gui.component.Loading loading;
    // End of variables declaration//GEN-END:variables
}
