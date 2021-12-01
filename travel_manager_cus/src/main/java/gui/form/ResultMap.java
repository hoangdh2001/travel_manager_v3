package gui.form;

import gui.component.BoxTour;
import dao.ChuyenDuLich_DAO;
import dao.impl.ChuyenDuLichImpl;
import model.ChuyenDuLich;
import model.LoaiChuyenDi;
import com.huyhoang.swing.event.EventTour;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ResultMap extends javax.swing.JPanel {

    private ChuyenDuLich_DAO chuyenDuLich_DAO;
    private LoaiChuyenDi loaiChuyenDi;
    private EventTour event;

    public void addEventTour(EventTour event) {
        this.event = event;
    }

    public ResultMap(LoaiChuyenDi loaiChuyenDi) throws MalformedURLException, RemoteException, NotBoundException {
        this.loaiChuyenDi = loaiChuyenDi;
        this.chuyenDuLich_DAO = (ChuyenDuLich_DAO) Naming.lookup("rmi://localhost:1099/chuyendulich_dao");
        initComponents();
        buildDisplay();
    }

    private void buildDisplay() {
    	map1.setTitle(loaiChuyenDi.getTenLoaiChuyen() + " hàng đầu");
        createMap();
    }

    private void createMap() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ChuyenDuLich> dsChuyenDuLich = null;
				try {
					dsChuyenDuLich = chuyenDuLich_DAO.getDsChuyenDuLichByLoaiChuyen(loaiChuyenDi);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                if (dsChuyenDuLich != null) {
                    for (ChuyenDuLich chuyenDuLich : dsChuyenDuLich) {
                        BoxTour boxTour = new BoxTour();
                        boxTour.setChuyenDuLich(chuyenDuLich);
                        boxTour.addEventBoxTour(new MouseAdapter() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        map1 = new gui.component.Map();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(57, 20, 1, 20));
        setOpaque(false);
        
        map1.setTitle("Loại chuyến hàng đầu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Map map1;
    // End of variables declaration//GEN-END:variables
}
