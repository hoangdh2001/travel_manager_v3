package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.HuongDanVien;

public interface HuongDanVien_DAO extends Remote {
    public HuongDanVien getHuongDanVienRandom() throws RemoteException;
}
