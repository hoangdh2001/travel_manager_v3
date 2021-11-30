package dao.impl;

import dao.HuongDanVien_DAO;
import model.HuongDanVien;
import util.HibernateUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HuongDanVienImpl extends UnicastRemoteObject implements HuongDanVien_DAO {

    private SessionFactory sessionFactory;

    public HuongDanVienImpl() throws RemoteException {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public HuongDanVien getHuongDanVienRandom() throws RemoteException {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        String sql = "SELECT top 1 * FROM huongdanvien\n"
                + "ORDER BY NEWID()";
        try {
            tr.begin();
            HuongDanVien huongDanVien = session
                    .createNativeQuery(sql, HuongDanVien.class)
                    .getSingleResult();
            tr.commit();
            return huongDanVien;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
    //nh
    
    @Override
    public List<HuongDanVien> searchHuongDanViens(String textSearch, String option, String gioiTinh, String ngaySinh, int numPage) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        String gioiTinhString = "Nam".equals(gioiTinh) ? "0" : "1";

        String queryGioiTinh = "";
        if (!gioiTinh.equals("")) {
            queryGioiTinh = " and gioitinh = " + gioiTinhString + "  ";
        }
        String queryNgaySinh = "";
        if (!ngaySinh.equals("")) {
            queryNgaySinh = " and ngaysinh = '" + ngaySinh + "' ";
        }

        int soLuong = numPage * 20;
        if (soLuong < 0) {
            soLuong = Math.abs(soLuong);
        }

        String query = "";

        switch (option) {
            case "Tên HDV":
                query = "select kh.* from huongdanvien kh where  "
                        + " ten like N'%" + textSearch + "%'"
                        + queryGioiTinh
                        + queryNgaySinh
                        + " order by ten offset " + soLuong + " rows fetch next 20 rows only";
                break;

            case "Mã HDV":
                query = "select kh.* from huongdanvien kh where  "
                        + " huongdanvien_id like '%" + textSearch + "%'"
                        + queryGioiTinh
                        + queryNgaySinh
                        + " order by huongdanvien_id offset " + soLuong + " rows fetch next 20 rows only";
                break;

            case "Số điện thoại":
                query = "select kh.* from huongdanvien kh where  "
                        + " sdt like '%" + textSearch + "%'"
                        + queryGioiTinh
                        + queryNgaySinh
                        + " order by sdt offset " + soLuong + " rows fetch next 20 rows only";
                break;

            case "Căn cước công dân":
                query = "select kh.* from huongdanvien kh where  "
                        + " CCCD like '%" + textSearch + "%'"
                        + queryGioiTinh
                        + queryNgaySinh
                        + " order by CCCD offset " + soLuong + " rows fetch next 20 rows only";
                break;

            case "Email":
                query = "select kh.* from huongdanvien kh where  "
                        + " email like '%" + textSearch + "%'"
                        + queryGioiTinh
                        + queryNgaySinh
                        + " order by email offset " + soLuong + " rows fetch next 20 rows only";
                break;

        }

        try {
            transaction.begin();
            List<HuongDanVien> list = session.createNativeQuery(query, HuongDanVien.class)
                    .getResultList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return null;
    }

    @Override
    public int getSoLuongHDV(String textSearch, String option, String gioiTinh, String ngaySinh) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        String gioiTinhString = "Nam".equals(gioiTinh) ? "0" : "1";

        String queryGioiTinh = "";
        if (!gioiTinh.equals("")) {
            queryGioiTinh = " and gioitinh = '" + gioiTinhString + "' ";
        }
        String queryNgaySinh = "";
        if (!ngaySinh.equals("")) {
            queryNgaySinh = " and ngaysinh = '" + ngaySinh + "' ";
        }


        String query = "";

        switch (option) {
            case "Tên HDV":
                query = "select count(*) from huongdanvien  where  "
                        + " ten like N'%" + textSearch + "%'"
                        + queryGioiTinh
                        + queryNgaySinh;
                break;

            case "Mã HDV":
                query = "select count(*) from huongdanvien  where  "
                        + " huongdanvien_id like '%" + textSearch + "%'"
                        + queryGioiTinh
                        + queryNgaySinh;
                break;

            case "Số điện thoại":
                query = "select count(*) from huongdanvien where  "
                        + " sdt like '%" + textSearch + "%'"
                        + queryGioiTinh
                        + queryNgaySinh;
                break;

            case "Căn cước công dân":
                query = "select count(*) from huongdanvien  where  "
                        + " CCCD like '%" + textSearch + "%'"
                        + queryGioiTinh
                        + queryNgaySinh;
                break;

            case "Email":
                query = "select count(*) from huongdanvien  where  "
                        + " email like '%" + textSearch + "%'"
                        + queryGioiTinh
                        + queryNgaySinh;
                break;

        }

        try {
            transaction.begin();
            int list = (int) session.createNativeQuery(query).getSingleResult();
            transaction.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

        return 0;
    }
}
