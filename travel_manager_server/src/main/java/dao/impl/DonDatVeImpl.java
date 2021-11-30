package dao.impl;

import dao.DonDatVe_DAO;
import model.DonDatVe;
import model.TrangThaiDonDat;
import util.HibernateUtil;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DonDatVeImpl extends UnicastRemoteObject implements DonDatVe_DAO {
    private SessionFactory sessionFactory;
    public DonDatVeImpl() throws RemoteException {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }
    @Override
    public boolean addDonDatVe(DonDatVe donDatVe) throws RemoteException {
        Session session =sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        
        try {
            tr.begin();
            session.save(donDatVe);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public String getMaxID() throws RemoteException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select max(dondatve_id) from dondatve";

        try {
            tr.begin();
            String id = (String) session
                    .createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return id;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
    //nh
    @Override
    public List<DonDatVe> searchDonDatVes(String textSearch, String option, TrangThaiDonDat trangThaiDonDat, String ngayDat, int numPage) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        int soLuong = numPage * 20;
        if (soLuong < 0) {
            soLuong = Math.abs(soLuong);
        }

        String query = "";

        switch (option) {
            case "Tên khách hàng":
                query = "select kh.* from dondatve kh where  "
                        + " hoten like N'%" + textSearch + "%'"
                        + " order by hoten offset " + soLuong + " rows fetch next 20 rows only";
                break;
                
            case "Mã đơn đặt":
                query = "select kh.* from dondatve kh where  "
                        + " dondatve_id like '%" + textSearch + "%'"
                        + " order by dondatve_id offset " + soLuong + " rows fetch next 20 rows only";
                break;

                    
        }

        try {
            transaction.begin();
            List<DonDatVe> list = session.createNativeQuery(query, DonDatVe.class)
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
    public int getSoLuongDDV(String textSearch, String option, TrangThaiDonDat trangThaiDonDat, String ngayDat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
