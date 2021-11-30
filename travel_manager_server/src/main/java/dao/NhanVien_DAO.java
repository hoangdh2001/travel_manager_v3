/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.NhanVien;

/**
 *
 * @author NGUYE
 */
public interface NhanVien_DAO extends Remote {

    public boolean addNhanVien(NhanVien diaDanh) throws RemoteException;

    public boolean updateNhanVien(NhanVien diaDanh) throws RemoteException;

    public boolean deleteNhanVien(String id) throws RemoteException;

    public NhanVien getNhanVien(String id) throws RemoteException;

    public List<NhanVien> getNhanViens() throws RemoteException;

    public List<NhanVien> getNhanVien(int numPage) throws RemoteException;

    public int getSoLuongNhanVien() throws RemoteException;

//    private SessionFactory sessionFactory;
//
//    public NhanVienDAO() {
//        sessionFactory = HibernateUtil.getInstance().getSessionFactory() throws RemoteException;
//    }
//
//    @Override
//    public boolean addNhanVien(NhanVien diaDanh) {
//        throw new UnsupportedOperationException("Not supported yet.") throws RemoteException; //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean updateNhanVien(NhanVien diaDanh) {
//        throw new UnsupportedOperationException("Not supported yet.") throws RemoteException; //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean deleteNhanVien(String id) {
//        throw new UnsupportedOperationException("Not supported yet.") throws RemoteException; //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public NhanVien getNhanVien(String id) {
//       Session session = sessionFactory.openSession() throws RemoteException;
//        Transaction transaction = session.getTransaction() throws RemoteException;
//
//        try {
//            transaction.begin() throws RemoteException;
//            NhanVien rs = session.find(NhanVien.class, id) throws RemoteException;
//            transaction.commit() throws RemoteException;
//
//            return rs;
//        } catch (Exception e) {
//            System.err.println(e) throws RemoteException;
//            transaction.rollback() throws RemoteException;
//        }
//        return null;
//    }
//
//    @Override
//    public List<NhanVien> getNhanViens() {
//        throw new UnsupportedOperationException("Not supported yet.") throws RemoteException; //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<NhanVien> getNhanVien(int numPage) {
//        throw new UnsupportedOperationException("Not supported yet.") throws RemoteException; //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public int getSoLuongNhanVien() {
//        throw new UnsupportedOperationException("Not supported yet.") throws RemoteException; //To change body of generated methods, choose Tools | Templates.
//    }
}
