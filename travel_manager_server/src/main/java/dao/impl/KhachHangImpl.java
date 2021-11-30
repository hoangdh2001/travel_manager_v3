package dao.impl;

import dao.KhachHang_DAO;
import model.KhachHang;
import util.HibernateUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
}
