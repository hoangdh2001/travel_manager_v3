package dao;
 
import java.rmi.Remote;
import java.rmi.RemoteException;

import model.DonDatVe;

public interface DonDatVe_DAO extends Remote {
    public boolean addDonDatVe(DonDatVe donDatVe) throws RemoteException;
    public String getMaxID() throws RemoteException;
}
