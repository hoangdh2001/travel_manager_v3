package util;

import model.ChiTietThamQuan;
import model.ChiTietThamQuan_PK;
import model.ChuyenDuLich;
import model.DiaChi;
import model.DiaDanh;
import model.DonDatVe;
import model.DongTour;
import model.HuongDanVien;
import model.KhachHang;
import model.LoaiChuyenDi;
import model.NhanVien;
import model.PhuongTien;
import model.TrangThaiChuyenDi;
import model.TrangThaiDonDat;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private SessionFactory sessionFactory;
    private static HibernateUtil instance = null;
    private HibernateUtil() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(ChiTietThamQuan.class)
                .addAnnotatedClass(ChiTietThamQuan_PK.class)
                .addAnnotatedClass(ChuyenDuLich.class)
                .addAnnotatedClass(DiaChi.class)
                .addAnnotatedClass(DiaDanh.class)
                .addAnnotatedClass(DonDatVe.class)
                .addAnnotatedClass(DongTour.class)
                .addAnnotatedClass(HuongDanVien.class)
                .addAnnotatedClass(KhachHang.class)
                .addAnnotatedClass(LoaiChuyenDi.class)
                .addAnnotatedClass(NhanVien.class)
                .addAnnotatedClass(PhuongTien.class)
                .addAnnotatedClass(TrangThaiChuyenDi.class)
                .addAnnotatedClass(TrangThaiDonDat.class)
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    
    public synchronized static HibernateUtil getInstance() {
        if(instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
