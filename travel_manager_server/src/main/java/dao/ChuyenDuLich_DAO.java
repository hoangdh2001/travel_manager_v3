package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.ChuyenDuLich;
import model.LoaiChuyenDi;
import model.PhuongTien;
import model.TrangThaiChuyenDi;

public interface ChuyenDuLich_DAO extends Remote {
    public boolean addChuyenDuLich(ChuyenDuLich chuyenDuLich) throws RemoteException;
    public boolean updateChuyenDuLich(ChuyenDuLich chuyenDuLich) throws RemoteException;
    public boolean deleteChuyenDuLich(String id) throws RemoteException;
    public ChuyenDuLich getChuyenDuLich(String id) throws RemoteException;
    
    public List<ChuyenDuLich> getDsChuyenDuLich() throws RemoteException;
    public List<ChuyenDuLich> getDsChuyenDuLichMoi() throws RemoteException;
    public List<ChuyenDuLich> getDsChuyenDuLichNhieuDonDatNhat() throws RemoteException;
    public List<ChuyenDuLich> getDsChuyenDuLichNgauNhien(String maChuyen) throws RemoteException;
    
    //nh
    public List<ChuyenDuLich> getChuyenDuLichs() throws RemoteException;
    public List<ChuyenDuLich> getChuyenDuLich(int numPage) throws RemoteException;
    public int getSoLuongCDL() throws RemoteException;
    public List<ChuyenDuLich> searchChuyenDuLichs(String textSearch, LoaiChuyenDi loaiChuyenDi,
            TrangThaiChuyenDi trangThaiCDL, PhuongTien phuongTien,
            String ngayBD, String ngayKT, String ngayTao, int numPage) throws RemoteException;
    public int soLuongSearch(String textSearch, LoaiChuyenDi loaiChuyenDi, TrangThaiChuyenDi trangThaiCDL, PhuongTien phuongTien,
            String ngayBD, String ngayKT, String ngayTao) throws RemoteException;
    public ChuyenDuLich getLastChuyenDuLich() throws RemoteException;
}
