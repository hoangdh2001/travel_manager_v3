package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.DiaDanh;

public interface DiaDanh_Dao extends Remote {
    
    public boolean addDiaDanh(DiaDanh diaDanh) throws RemoteException;

    public boolean updateDiaDanh(DiaDanh diaDanh) throws RemoteException;

    public boolean deleteDiaDanh(String id) throws RemoteException;

    public DiaDanh getDiaDanh(String id) throws RemoteException;

    public List<DiaDanh> getDiaDanhs() throws RemoteException;

    public List<DiaDanh> getDiaDanh(int numPage) throws RemoteException;

    public int getSoLuongDiaDanh() throws RemoteException;

    public List<String> getTinhThanhDiaDanhs() throws RemoteException;

    public List<DiaDanh> searchDiaDanhs(String textSearch, String tinhThanh, int numPage) throws RemoteException;

    public int getSoLuongSearch(String textSearch, String tinhThanh) throws RemoteException;

}
