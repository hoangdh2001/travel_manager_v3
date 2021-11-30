package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import dao.ChiTietThamQuan_DAO;
import dao.ChuyenDuLich_DAO;
import dao.DiaChi_DAO;
import dao.DiaDanh_Dao;
import dao.DonDatVe_DAO;
import dao.HuongDanVien_DAO;
import dao.KhachHang_DAO;
import dao.LoaiChuyenDi_DAO;
import dao.NhanVien_DAO;
import dao.impl.ChiTietThamQuanImpl;
import dao.impl.ChuyenDuLichImpl;
import dao.impl.DiaChiImpl;
import dao.impl.DiaDanhImpl;
import dao.impl.DonDatVeImpl;
import dao.impl.HuongDanVienImpl;
import dao.impl.KhachHangImpl;
import dao.impl.LoaiChuyenDiImpl;
import dao.impl.NhanVienImpl;
public class ServerApp extends javax.swing.JFrame {

    public ServerApp() {
        initComponents();
        SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			
			LocateRegistry.createRegistry(1099);
			ChuyenDuLich_DAO chuyenDuLich_DAO = new ChuyenDuLichImpl(); //Remote Object
			Naming.bind("rmi://localhost:1099/chuyendulich_dao", chuyenDuLich_DAO);
			System.out.println("chuyendulich start");
			
			ChiTietThamQuan_DAO chiTietThamQuan_DAO = new ChiTietThamQuanImpl();
			Naming.bind("rmi://localhost:1099/chiTietThamQuan_DAO", chiTietThamQuan_DAO);
			System.out.println("chiTietThamQuan start");
			
			DiaChi_DAO diaChi_DAO = new DiaChiImpl();
			Naming.bind("rmi://localhost:1099/diaChi_DAO", diaChi_DAO);
			System.out.println("diachi start");
			
			DiaDanh_Dao diaDanh_Dao = new DiaDanhImpl();
			Naming.bind("rmi://localhost:1099/diaDanh_Dao", diaDanh_Dao);
			System.out.println("diadanh start");
			
			DonDatVe_DAO donDatVe_DAO = new DonDatVeImpl();
			Naming.bind("rmi://localhost:1099/donDatVe_DAO", donDatVe_DAO);
			System.out.println("dondatve start");
			
			HuongDanVien_DAO huongDanVien_DAO = new HuongDanVienImpl();
			Naming.bind("rmi://localhost:1099/huongDanVien_DAO", huongDanVien_DAO);
			System.out.println("huongdanvien start");
			
			KhachHang_DAO khachHang_DAO = new KhachHangImpl();
			Naming.bind("rmi://localhost:1099/khachHang_DAO", khachHang_DAO);
			System.out.println("khachhang start");
			
			LoaiChuyenDi_DAO loaiChuyenDi_DAO = new LoaiChuyenDiImpl();
			Naming.bind("rmi://localhost:1099/loaiChuyenDi_DAO", loaiChuyenDi_DAO);
			System.out.println("loaichuyendi start");
			
			NhanVien_DAO nhanVien_DAO = new NhanVienImpl();
			Naming.bind("rmi://localhost:1099/nhanVien_DAO", nhanVien_DAO);
			System.out.println("nhanvien start");
			
			System.out.println("Server bound in RMIRegistry");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        pack();
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
