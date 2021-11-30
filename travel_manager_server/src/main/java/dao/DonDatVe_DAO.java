package dao;
 
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.DonDatVe;
import model.TrangThaiDonDat;

public interface DonDatVe_DAO extends Remote {
    public boolean addDonDatVe(DonDatVe donDatVe) throws RemoteException;
    public String getMaxID() throws RemoteException;
    
    //nh
    public List<DonDatVe> searchDonDatVes(String textSearch, String option, TrangThaiDonDat trangThaiDonDat, String ngayDat, int numPage)throws RemoteException;
    public int getSoLuongDDV(String textSearch, String option, TrangThaiDonDat trangThaiDonDat, String ngayDat)throws RemoteException;
}
