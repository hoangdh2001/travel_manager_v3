package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.DiaChi;

public interface DiaChi_DAO extends Remote {
    public List<String> getAllTinhThanh() throws RemoteException;
    
    //nh
    public DiaChi getDiaChi(String id) throws RemoteException;
    /**
     * lấy quận/huyện dựa vào tỉnh thành
     *
     * @param tinhThanh
     * @return
     */
    public List<String> getQuanHuyenTheoTinhThanh(String tinhThanh) throws RemoteException;

    /**
     * lấy phường/xã dựa vào quận/huyện và tỉnh thành
     *
     * @param quanHuyen
     * @param tinhThanh
     * @return
     */
    public List<String> getPhuongXaTheoQHTH(String quanHuyen, String tinhThanh) throws RemoteException;
    
    /**
     *  lấy mã địa chỉ dựa vào tên tỉnh/ thành phố
     * @param name
     * @return 
     */
    public String getMaDiaChiByTinh(String name) throws RemoteException;
    
    /**
     *  lấy tên tỉnh thành phố dựa vào mã
     * @param id
     * @return 
     */
    public String getTinhByMaDiaChi(String id) throws RemoteException;
}
