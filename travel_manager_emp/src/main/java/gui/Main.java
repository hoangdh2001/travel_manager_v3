package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import com.formdev.flatlaf.FlatLightLaf;
import com.huyhoang.swing.event.EventMenuSelected;
import com.huyhoang.swing.panel.ComponentResizer;
import com.huyhoang.swing.panel.LayerPaneShadow;

import gui.component.Menu;
import gui.component.PanelDiaDanh;
import gui.component.PanelDonDatVe;
import gui.component.PanelHuongDanVien;
import gui.component.PanelKhachHang;
import gui.component.PanelTour;
import gui.dialog.DialogTour;

public class Main extends javax.swing.JFrame {
    
    private boolean show;
    private Animator start;
    private Menu menu;
    
    public Main() {
        initComponents();
        buildDisplay();
        resized();
    }
    
    private void buildDisplay() {
        start();
        buildMenu();
    }

    private void buildMenu() {
        
        menu1.initMenu(new EventMenuSelected() {
            @Override
            public void menuSelected(int index) {
                System.out.println("main " + index);
                
                switch (index) {
                    case 0:
					PanelTour panelTour = null;
					try {
						panelTour = new PanelTour();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                        panelTour.btnThemHandle(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                DialogTour dialogTour = null;
								try {
									dialogTour = new DialogTour(Main.this);
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (MalformedURLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (NotBoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
                                dialogTour.setVisible(true);
                            }
                        });
                        content1.showForm(panelTour);
                        break;
                    
                    case 1:
					PanelKhachHang panelKhachHang = null;
					try {
						panelKhachHang = new PanelKhachHang();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                        content1.showForm(panelKhachHang);
                        break;
                    case 2:
                        PanelDonDatVe panelDonDat = new PanelDonDatVe();
                        content1.showForm(panelDonDat);
                        break;
                    case 3:
                        PanelHuongDanVien panelHDV = new PanelHuongDanVien();
                        content1.showForm(panelHDV);
                        break;
                    case 4:
					PanelDiaDanh panelDiaDanh = null;
					try {
						panelDiaDanh = new PanelDiaDanh();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                        content1.showForm(panelDiaDanh);
                        break;
                }
            }
        });
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
        start = new Animator(300, target);
        start.setResolution(0);
        start.setAcceleration(0.5f);
    }
    
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
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
    
    private void resized() {
        ComponentResizer com = new ComponentResizer();
        com.registerComponent(this);
        com.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        com.setSnapSize(new Dimension(10, 10));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new LayerPaneShadow();
        menu1 = new gui.component.Menu();
        header1 = new gui.component.Header();
        content1 = new gui.component.Content();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName(""); // NOI18N
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bg.setShadowOpacity(0.3F);
        bg.setShadowSize(10);

        bg.setLayer(menu1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(header1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        bg.setLayer(content1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, 1202, Short.MAX_VALUE)
                    .addComponent(content1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content1, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE))
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(bg, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1460, 881));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	SecurityManager securityManager = System.getSecurityManager();
        		if(securityManager == null) {
        			System.setProperty("java.security.policy", "policy/policy.policy");
        			System.setSecurityManager(new SecurityManager());
        		}
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LayerPaneShadow bg;
    private gui.component.Content content1;
    private gui.component.Header header1;
    private gui.component.Menu menu1;
    // End of variables declaration//GEN-END:variables
}
