package gui;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dao.ChuyenDuLich_DAO;
import model.ChuyenDuLich;

public class Client {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			ChuyenDuLich_DAO chuyenDuLich_DAO = (ChuyenDuLich_DAO) Naming.lookup("rmi://localhost:1099/ChuyenDuLich");
			
			for (ChuyenDuLich chuyenDuLich : chuyenDuLich_DAO.getDsChuyenDuLich()) {
				System.out.println(chuyenDuLich);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
