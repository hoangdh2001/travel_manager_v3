/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.ChiTietThamQuan;

/**
 *
 * @author NGUYE
 */
public interface ChiTietThamQuan_DAO extends Remote {

	public boolean addChiTietThamQuan(ChiTietThamQuan chiTietThamQuan) throws RemoteException;

	public boolean updateChiTietThamQuan(ChiTietThamQuan chiTietThamQuan) throws RemoteException;

	public boolean deleteChiTietThamQuan(String id) throws RemoteException;

	public ChiTietThamQuan getChiTietThamQuan(String id) throws RemoteException;

	public List<ChiTietThamQuan> getChiTietThamQuans() throws RemoteException;

	public List<ChiTietThamQuan> getChiTietThamQuan(int numPage) throws RemoteException;

	public int getSoLuongChiTietThamQuan() throws RemoteException;

}
