package dao.impl;

import dao.KhachHang_DAO;
import model.KhachHang;
import util.HibernateUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class KhachHangImpl extends UnicastRemoteObject implements KhachHang_DAO {
    private SessionFactory sessionFactory;

    public KhachHangImpl() throws RemoteException {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }
    
    @Override
    public KhachHang getKhachHangByLogin(String sdt, byte[] matKhau) throws RemoteException {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        String sql = "select * from khachhang "
                + "where sdt = '" + sdt + "' "
                + "and matKhau = :x";
        try {
            tr.begin();
            KhachHang khachHang = session
                    .createNativeQuery(sql, KhachHang.class)
                    .setParameter("x", matKhau)
                    .getSingleResult();
            tr.commit();
            return khachHang;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public boolean updateKhachHang(KhachHang khachHang) throws RemoteException {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.update(khachHang);
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }
    
    //nh
    @Override
    public List<KhachHang> searchKhachHangs(String textSearch, String option, int numPage) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        int soLuong = numPage * 20;
        if (soLuong < 0) {
            soLuong = Math.abs(soLuong);
        }

        String query = "";

        switch (option) {
            case "Tên khách hàng":
                query = "select kh.* from khachhang kh where  "
                        + " hoten like N'%" + textSearch + "%'"
                        + " order by hoten offset " + soLuong + " rows fetch next 20 rows only";
                break;
                
            case "Mã khách hàng":
                query = "select kh.* from khachhang kh where  "
                        + " khachhang_id like '%" + textSearch + "%'"
                        + " order by khachhang_id offset " + soLuong + " rows fetch next 20 rows only";
                break;

            case "Số điện thoại":
                query = "select kh.* from khachhang kh where  "
                        + " sdt like '%" + textSearch + "%'"
                        + " order by sdt offset " + soLuong + " rows fetch next 20 rows only";
                break;
                
            case "Căn cước công dân":
                query = "select kh.* from khachhang kh where  "
                        + " CCCD like '%" + textSearch + "%'"
                        + " order by CCCD offset " + soLuong + " rows fetch next 20 rows only";
                break;
           
            case "Email":
                query = "select kh.* from khachhang kh where  "
                        + " email like '%" + textSearch + "%'"
                        + " order by email offset " + soLuong + " rows fetch next 20 rows only";
                break;
                    
        }

        try {
            transaction.begin();
            List<KhachHang> list = session.createNativeQuery(query, KhachHang.class)
                    .getResultList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return null;
    }

    @Override
    public int getSoLuongKhachHang(String textSearch, String option) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        String query = "";

        switch (option) {
            case "Tên khách hàng":
                query = "select count(*) from khachhang kh where  "
                        + " hoten like N'%" + textSearch + "%'";
                break;
                
            case "Mã khách hàng":
                query = "select count(*) from khachhang kh where  "
                        + " khachhang_id like '%" + textSearch + "%'";
                break;

            case "Số điện thoại":
                query = "select count(*) from khachhang kh where  "
                        + " sdt like '%" + textSearch + "%'";
                break;
                
            case "Căn cước công dân":
                query = "select count(*) from khachhang kh where  "
                        + " CCCD like '%" + textSearch + "%'";
                break;
           
            case "Email":
                query = "select count(*) from khachhang kh where  "
                        + " email like '%" + textSearch + "%'";
                break;
        }

        try {
            transaction.begin();
            int list = (int) session.createNativeQuery(query).getSingleResult();
            transaction.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return 0;
    }
}
