package dao.impl;

import dao.DiaChi_DAO;
import model.DiaChi;
import util.HibernateUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DiaChiImpl extends UnicastRemoteObject implements DiaChi_DAO {
    private SessionFactory sessionFactory;

    public DiaChiImpl() throws RemoteException {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }
    
    @Override
    public List<String> getAllTinhThanh() throws RemoteException {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select distinct tinhthanh from diachi";
        try {
            tr.begin();
            List<String> dsTinhThanh = session
                    .createNativeQuery(sql)
                    .getResultList();
            tr.commit();
            return dsTinhThanh;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
    //nh
    @Override
    public DiaChi getDiaChi(String id) throws RemoteException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "select * from diachi where diachi_id like '" + id + "' ";
            DiaChi rs = (DiaChi) session.createNativeQuery(query, DiaChi.class).getSingleResult();
            transaction.commit();

            return rs;
        } catch (Exception e) {
            System.err.println(e);
            transaction.rollback();
        }
        return null;
    }


    @Override
    public List<String> getQuanHuyenTheoTinhThanh(String tinhThanh) throws RemoteException {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT  DISTINCT(quanhuyen)  FROM diachi WHERE tinhthanh LIKE N'%" + tinhThanh + "%'";
            List<String> quanHuyens = session.createNativeQuery(query).getResultList();

            transaction.commit();

            return quanHuyens;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return null;
    }

    @Override
    public List<String> getPhuongXaTheoQHTH(String quanHuyen, String tinhThanh) throws RemoteException {
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT DISTINCT(xaphuong) FROM diachi "
                    + " WHERE tinhthanh LIKE N'%" + tinhThanh + "%' AND quanhuyen LIKE N'%" + quanHuyen + "%'";
            List<String> xaPhuongs = session.createNativeQuery(query).getResultList();

            transaction.commit();
            return xaPhuongs;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return null;
    }

    @Override
    public String getMaDiaChiByTinh(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getTinhByMaDiaChi(String id) throws RemoteException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "select tinhthanh from diachi where diachi_id = '" + id + "' ";
            String diaDanh = (String) session.createNativeQuery(query).getSingleResult();
            transaction.commit();

            return diaDanh;
        } catch (Exception e) {
            System.err.println(e);
            transaction.rollback();
        }
        return null;
    }
    
}
