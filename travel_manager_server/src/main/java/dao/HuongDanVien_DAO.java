package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.HuongDanVien;

public interface HuongDanVien_DAO extends Remote {
    public HuongDanVien getHuongDanVienRandom() throws RemoteException;

    //nh
    public List<HuongDanVien> searchHuongDanViens(String textSearch, String option, String gioiTinh, String ngaySinh, int numPage)throws RemoteException;
    public int getSoLuongHDV(String textSearch, String option, String gioiTinh, String ngaySinh)throws RemoteException;
}
