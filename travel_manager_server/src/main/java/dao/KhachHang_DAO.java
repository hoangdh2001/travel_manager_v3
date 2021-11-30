package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.KhachHang;

public interface KhachHang_DAO extends Remote {
    public KhachHang getKhachHangByLogin(String sdt, byte[] matKhau) throws RemoteException;
    public boolean updateKhachHang(KhachHang khachHang) throws RemoteException;
}
