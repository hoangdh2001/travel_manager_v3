package gui.form;

import gui.MainFrame;
import gui.component.BoxTour;
import dao.ChuyenDuLich_DAO;
import dao.impl.ChuyenDuLichImpl;
import model.ChuyenDuLich;
import com.huyhoang.swing.event.EventTour;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Library extends javax.swing.JPanel {
    private EventTour event;
    private ChuyenDuLich_DAO chuyenDuLich_DAO;
    
    public void addEventTour(EventTour event) {
        this.event = event;
    }
    public Library() throws RemoteException, MalformedURLException, NotBoundException {
        this.chuyenDuLich_DAO = (ChuyenDuLich_DAO) Naming.lookup("rmi://localhost:1099/chuyendulich_dao");
        initComponents();
        buildDisplay();
    }
    
    private void buildDisplay() {
        createMap();
        createMap2();
    }
    
    private void createMap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ChuyenDuLich> dsChuyenDuLich = MainFrame.khachHang.getChuyenDiDaThich();
                if (dsChuyenDuLich != null && dsChuyenDuLich.size() > 0) {
                    for (ChuyenDuLich chuyenDuLich : dsChuyenDuLich) {
                        BoxTour boxTour = new BoxTour();
                        boxTour.setChuyenDuLich(chuyenDuLich);
                        boxTour.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                event.openTour(chuyenDuLich);
                            }
                        });
                        map1.addBox(boxTour, 200, 280);
                    }
                }
            }
        }).start();
    }
    
    private void createMap2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ChuyenDuLich> dsChuyenDuLich = null;
				try {
					dsChuyenDuLich = chuyenDuLich_DAO.getDsChuyenDuLichDaDat(MainFrame.khachHang);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                if (dsChuyenDuLich != null && dsChuyenDuLich.size() > 0) {
                    for (ChuyenDuLich chuyenDuLich : dsChuyenDuLich) {
                        BoxTour boxTour = new BoxTour();
                        boxTour.setChuyenDuLich(chuyenDuLich);
                        boxTour.addEventBoxTour(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                event.openTour(chuyenDuLich);
                            }
                        });
                        map2.addBox(boxTour, 200, 280);
                    }
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        map1 = new gui.component.Map();
        map2 = new gui.component.Map();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(57, 20, 0, 20));
        setOpaque(false);

        map1.setName(""); // NOI18N
        map1.setTitle("Tour đã thích");

        map2.setName(""); // NOI18N
        map2.setTitle("Tour đã đặt");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
            .addComponent(map2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(map2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private gui.component.Map map1;
    private gui.component.Map map2;
    // End of variables declaration                   
}
