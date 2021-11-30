package dao.impl;

import dao.HuongDanVien_DAO;
import model.HuongDanVien;
import util.HibernateUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HuongDanVienImpl extends UnicastRemoteObject implements HuongDanVien_DAO {

    private SessionFactory sessionFactory;

    public HuongDanVienImpl() throws RemoteException {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public HuongDanVien getHuongDanVienRandom() throws RemoteException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "SELECT top 1 * FROM huongdanvien\n"
                + "ORDER BY NEWID()";
        try {
            tr.begin();
            HuongDanVien huongDanVien = session
                    .createNativeQuery(sql, HuongDanVien.class)
                    .getSingleResult();
            tr.commit();
            return huongDanVien;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

}
