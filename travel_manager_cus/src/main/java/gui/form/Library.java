package gui.form;

import model.ChuyenDuLich;
import com.huyhoang.swing.event.EventTour;

import gui.MainFrame;
import gui.component.BoxTour;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Library extends javax.swing.JPanel {
    private EventTour event;
    
    public void addEventTour(EventTour event) {
        this.event = event;
    }
    public Library() {
        initComponents();
        buildDisplay();
    }
    
    private void buildDisplay() {
        createMap();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        map1 = new gui.component.Map();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(57, 20, 0, 20));
        setOpaque(false);

        map1.setTitle("Thư viện");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Map map1;
    // End of variables declaration//GEN-END:variables
}
