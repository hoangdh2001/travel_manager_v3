package dao.impl;

import model.ChuyenDuLich;
import util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import dao.ChuyenDuLich_DAO;
import model.LoaiChuyenDi;
import model.PhuongTien;
import model.TrangThaiChuyenDi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChuyenDuLichImpl extends UnicastRemoteObject implements ChuyenDuLich_DAO {

    private SessionFactory sessionFactory;

    public ChuyenDuLichImpl() throws RemoteException {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
    }

    @Override
    public boolean addChuyenDuLich(ChuyenDuLich chuyenDuLich) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            session.save(chuyenDuLich);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean updateChuyenDuLich(ChuyenDuLich chuyenDuLich) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.update(chuyenDuLich);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public boolean deleteChuyenDuLich(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            session.delete(session.find(ChuyenDuLich.class, id));
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
        }
        return false;
    }

    @Override
    public ChuyenDuLich getChuyenDuLich(String id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        try {
            tr.begin();
            ChuyenDuLich chuyenDuLich = session.find(ChuyenDuLich.class, id);
            tr.commit();
            return chuyenDuLich;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<ChuyenDuLich> getDsChuyenDuLich() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        List<ChuyenDuLich> dsChuyenDuLich = new ArrayList<>();

        try {
            tr.begin();
            dsChuyenDuLich = session
                    .createNamedQuery("getChuyenDuLichs", ChuyenDuLich.class)
                    .getResultList();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
        }
        return dsChuyenDuLich;
    }

    /**
     * Lấy danh sách 5 chuyến du lịch mới nhất
     *
     * @return dsChuyenDuLich
     */
    @Override
    public List<ChuyenDuLich> getDsChuyenDuLichMoi() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select top 3 * from chuyendulich\n"
                + "where trangThai = 'CHUA_KHOI_HANH'\n"
                + "order by chuyen_id desc";
        try {
            tr.begin();
            List<ChuyenDuLich> dsChuyenDuLich = session
                    .createNativeQuery(sql, ChuyenDuLich.class)
                    .getResultList();
            tr.commit();
            return dsChuyenDuLich;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    /**
     * Lấy danh sách 5 chuyến du lịch phổ biến nhất
     *
     * @return dsChuyenDuLich
     */
    @Override
    public List<ChuyenDuLich> getDsChuyenDuLichNhieuDonDatNhat() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select cd.* from chuyendulich cd join dondatve d\n"
                + "on cd.chuyen_id = d.chuyen_id\n"
                + "where cd.trangthai = 'CHUA_KHOI_HANH'\n"
                + "group by cd.chuyen_id, cd.dongtour, cd.giachuyen, cd.loaichuyendi_id, cd.mota,\n"
                + "cd.noikhoihanh, cd.ngay_tao, cd.ngayketthuc, cd.ngaykhoihanh,\n"
                + "cd.nhanvien_id, cd.phuongtien, cd.soluong, cd.trangthai\n"
                + "having COUNT(d.dondatve_id) = (select top 3 COUNT(d.dondatve_id) as slDonDat from chuyendulich cd join dondatve d on cd.chuyen_id = d.chuyen_id)";
        try {
            tr.begin();
            List<ChuyenDuLich> dsChuyenDuLich = session
                    .createNativeQuery(sql, ChuyenDuLich.class)
                    .getResultList();
            tr.commit();
            return dsChuyenDuLich;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<ChuyenDuLich> getDsChuyenDuLichNgauNhien(String maChuyen) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "SELECT TOP 3 * FROM chuyendulich "
                + "where chuyen_id != '"+ maChuyen +"' and trangThai = 'CHUA_KHOI_HANH' "
                + "ORDER BY NEWID()";

        try {
            tr.begin();
            List<ChuyenDuLich> dsChuyenDuLich = session
                    .createNativeQuery(sql, ChuyenDuLich.class)
                    .getResultList();
            tr.commit();
            return dsChuyenDuLich;
        } catch (Exception e) {
            tr.rollback();
        }
        return null;
    }
    
    
    //nh
    @Override
    public List<ChuyenDuLich> getChuyenDuLichs() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();
        try {
            tr.begin();
            List<ChuyenDuLich> rs = session
                    .createNamedQuery("getChuyenDuLichs", ChuyenDuLich.class)
                    .getResultList();
            tr.commit();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        return null;
    }

    @Override
    public List<ChuyenDuLich> getChuyenDuLich(int numPage) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();
        String sql = "select cdl.* from chuyendulich cdl order by chuyen_id offset :x row fetch next 20 rows only";
        try {
            tr.begin();
            List<ChuyenDuLich> dsPhong = session
                    .createNativeQuery(sql, ChuyenDuLich.class)
                    .setParameter("x", numPage * 20)
                    .getResultList();

            tr.commit();
            return dsPhong;

        } catch (Exception e) {
            tr.rollback();
        }
        session.close();
        return null;
    }

    @Override
    public int getSoLuongCDL() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.getTransaction();

        String sql = "select count(*) from chuyendulich";
        try {
            tr.begin();
            int rs = (int) session.
                    createNativeQuery(sql)
                    .getSingleResult();
            tr.commit();
            return rs;
        } catch (Exception e) {
            tr.rollback();
        }
        return 0;
    }

    @Override
    public List<ChuyenDuLich> searchChuyenDuLichs(String textSearch, LoaiChuyenDi loaiChuyenDi, TrangThaiChuyenDi trangThaiCDL,
            PhuongTien phuongTien, String ngayBD, String ngayKT, String ngayTao, int numPage) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        String queryNgayThangNam = " and ( ";

        if (!ngayBD.equals("")) {
            queryNgayThangNam += " ngaykhoihanh = '" + ngayBD + "'  ";
        }

        if (!ngayKT.equals("")) {
            if (queryNgayThangNam.equals(" and ( ")) {
               queryNgayThangNam += "   ngayketthuc = '" + ngayKT + "'  ";
            } else {
               queryNgayThangNam += "  or ngayketthuc = '" + ngayKT + "'  ";
            }
            
            
        }

        if (!ngayTao.equals("")) {
            if (queryNgayThangNam.equals(" and ( ")) {
                queryNgayThangNam += "   ngay_tao = '" + ngayTao + "'  ";
            } else {
                queryNgayThangNam += "  or ngay_tao = '" + ngayTao + "'  ";
            }
        }

        if (queryNgayThangNam.equals(" and ( ")) {
            queryNgayThangNam = " ";
        } else {
            queryNgayThangNam += " ) ";

        }

        int soLuong = numPage * 20;
        System.out.println("offset DAO: " + soLuong);
        if (soLuong < 0) {
            soLuong = Math.abs(soLuong);
        }

        String query = "select cdl.* from chuyendulich cdl where  "
                + " chuyen_id like N'%" + textSearch + "%'"
                + " and loaichuyendi_id like '%" + (loaiChuyenDi == null ? "" : loaiChuyenDi.getMaLoaiChuyen()) + "%'"
                + " and trangthai like '%" + (trangThaiCDL == null ? "" : trangThaiCDL) + "%' "
                + " and phuongtien like '%" + (phuongTien == null ? "" : phuongTien) + "%' "
                + queryNgayThangNam
                + " order by chuyen_id offset " + soLuong + " rows fetch next 20 rows only";
        try {
            transaction.begin();
            List<ChuyenDuLich> list = session.createNativeQuery(query, ChuyenDuLich.class)
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
    public int soLuongSearch(String textSearch, LoaiChuyenDi loaiChuyenDi, TrangThaiChuyenDi trangThaiCDL, PhuongTien phuongTien,
            String ngayBD, String ngayKT, String ngayTao) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        String queryNgayThangNam = " and ( ";

        if (!ngayBD.equals("")) {
            queryNgayThangNam += " ngaykhoihanh = '" + ngayBD + "'  ";
        } else {
            queryNgayThangNam += " ngaykhoihanh like '%%' ";
        }
        if (!ngayKT.equals("")) {
            queryNgayThangNam += "  or ngayketthuc = '" + ngayKT + "'  ";
        }
        if (!ngayTao.equals("")) {
            queryNgayThangNam += "  or ngay_tao = '" + ngayTao + "'  ";
        }
        queryNgayThangNam += " ) ";

        String query = "select count(*) from chuyendulich where  "
                + " chuyen_id like N'%" + textSearch + "%'"
                + " and loaichuyendi_id like '%" + (loaiChuyenDi == null ? "" : loaiChuyenDi.getMaLoaiChuyen()) + "%'"
                + " and trangthai like '%" + (trangThaiCDL == null ? "" : trangThaiCDL) + "%' "
                + " and phuongtien like '%" + (phuongTien == null ? "" : phuongTien) + "%' "
                + queryNgayThangNam;

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

    @Override
    public ChuyenDuLich getLastChuyenDuLich() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            String query = "SELECT * FROM chuyendulich "
                    + " WHERE chuyen_id = (SELECT MAX(chuyen_id) FROM chuyendulich)";
            ChuyenDuLich nhanVien = session.createNativeQuery(query, ChuyenDuLich.class).getSingleResult();
            transaction.commit();

            return nhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return null;
    }
}
