package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.KhachHang;

public interface KhachHang_DAO extends Remote {
    public KhachHang getKhachHangByLogin(String sdt, byte[] matKhau) throws RemoteException;
    public boolean updateKhachHang(KhachHang khachHang) throws RemoteException;
    
    //nh
    public List<KhachHang> searchKhachHangs(String textSearch, String option, int numPage)throws RemoteException;
    public int getSoLuongKhachHang(String textSearch, String option)throws RemoteException;
}
