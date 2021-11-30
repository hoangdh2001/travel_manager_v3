package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.LoaiChuyenDi;

public interface LoaiChuyenDi_DAO extends Remote { 
    public boolean addLoaiChuyenDi(LoaiChuyenDi loaiChuyenDi) throws RemoteException;
    public List<LoaiChuyenDi> getDsLoaiChuyenDi() throws RemoteException;
    //nh
    public boolean updateLoaiChuyenDi(LoaiChuyenDi loaiChuyenDi) throws RemoteException;
    public boolean deleteLoaiChuyenDi(String id) throws RemoteException;
    public LoaiChuyenDi getLoaiChuyenDi(String id) throws RemoteException;
    public List<LoaiChuyenDi> getLoaiChuyenDis() throws RemoteException;
    public List<LoaiChuyenDi> getLoaiChuyenDi(int numPage) throws RemoteException;
    public int getSoLuongLCD() throws RemoteException;
}
