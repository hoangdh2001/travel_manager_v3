package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.ChuyenDuLich_DAO;
import model.ChuyenDuLich;
import model.KhachHang;
import model.LoaiChuyenDi;
import model.PhuongTien;
import model.TrangThaiChuyenDi;
import util.HibernateUtil;

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
			dsChuyenDuLich = session.createNamedQuery("getChuyenDuLichs", ChuyenDuLich.class).getResultList();
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

		String sql = "select top 3 * from chuyendulich\n" + "where trangThai = 'CHUA_KHOI_HANH'\n"
				+ "order by chuyen_id desc";
		try {
			tr.begin();
			List<ChuyenDuLich> dsChuyenDuLich = session.createNativeQuery(sql, ChuyenDuLich.class).getResultList();
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

		String sql = " select cd.* from chuyendulich cd join dondatve d\r\n"
				+ "on cd.chuyen_id = d.chuyen_id\r\n"
				+ "where cd.trangthai = 'CHUA_KHOI_HANH'\r\n"
				+ "group by cd.chuyen_id, cd.dongtour, cd.giachuyen, cd.loaichuyendi_id, cd.mota,\r\n"
				+ "cd.noikhoihanh, cd.ngay_tao, cd.ngayketthuc, cd.ngaykhoihanh,\r\n"
				+ "cd.nhanvien_id, cd.phuongtien, cd.soluong, cd.trangthai\r\n"
				+ "having COUNT(d.dondatve_id) = all (select top 3 COUNT(d.dondatve_id) as slDonDat from chuyendulich cd join dondatve d on cd.chuyen_id = d.chuyen_id group by cd.chuyen_id)\r\n";
		try {
			tr.begin();
			List<ChuyenDuLich> dsChuyenDuLich = session.createNativeQuery(sql, ChuyenDuLich.class).getResultList();
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

		String sql = "SELECT TOP 3 * FROM chuyendulich " + "where chuyen_id != '" + maChuyen
				+ "' and trangThai = 'CHUA_KHOI_HANH' " + "ORDER BY NEWID()";

		try {
			tr.begin();
			List<ChuyenDuLich> dsChuyenDuLich = session.createNativeQuery(sql, ChuyenDuLich.class).getResultList();
			tr.commit();
			return dsChuyenDuLich;
		} catch (Exception e) {
			tr.rollback();
		}
		return null;
	}

	// nh
	@Override
	public List<ChuyenDuLich> getChuyenDuLichs() {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			List<ChuyenDuLich> rs = session.createNamedQuery("getChuyenDuLichs", ChuyenDuLich.class).getResultList();
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
			List<ChuyenDuLich> dsPhong = session.createNativeQuery(sql, ChuyenDuLich.class)
					.setParameter("x", numPage * 20).getResultList();

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
			int rs = (int) session.createNativeQuery(sql).getSingleResult();
			tr.commit();
			return rs;
		} catch (Exception e) {
			tr.rollback();
		}
		return 0;
	}

	@Override
	public List<ChuyenDuLich> searchChuyenDuLichs(String textSearch, LoaiChuyenDi loaiChuyenDi,
			TrangThaiChuyenDi trangThaiCDL, PhuongTien phuongTien, String ngayBD, String ngayKT, String ngayTao,
			int numPage) {
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

		int soLuong = numPage * 20;
		System.out.println("offset DAO: " + soLuong);
		if (soLuong < 0) {
			soLuong = Math.abs(soLuong);
		}

		String query = "select cdl.* from chuyendulich cdl where  " + " chuyen_id like N'%" + textSearch + "%'"
				+ " and loaichuyendi_id like '%" + (loaiChuyenDi == null ? "" : loaiChuyenDi.getMaLoaiChuyen()) + "%'"
				+ " and trangthai like '%" + (trangThaiCDL == null ? "" : trangThaiCDL) + "%' "
				+ " and phuongtien like '%" + (phuongTien == null ? "" : phuongTien) + "%' " + queryNgayThangNam
				+ " order by chuyen_id offset " + soLuong + " rows fetch next 20 rows only";
		try {
			transaction.begin();
			List<ChuyenDuLich> list = session.createNativeQuery(query, ChuyenDuLich.class).getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		return null;
	}

	@Override
	public int soLuongSearch(String textSearch, LoaiChuyenDi loaiChuyenDi, TrangThaiChuyenDi trangThaiCDL,
			PhuongTien phuongTien, String ngayBD, String ngayKT, String ngayTao) {
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

		String query = "select count(*) from chuyendulich where  " + " chuyen_id like N'%" + textSearch + "%'"
				+ " and loaichuyendi_id like '%" + (loaiChuyenDi == null ? "" : loaiChuyenDi.getMaLoaiChuyen()) + "%'"
				+ " and trangthai like '%" + (trangThaiCDL == null ? "" : trangThaiCDL) + "%' "
				+ " and phuongtien like '%" + (phuongTien == null ? "" : phuongTien) + "%' " + queryNgayThangNam;

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

	@Override
	public List<ChuyenDuLich> getDsChuyenDuLichByLoaiChuyen(LoaiChuyenDi loaiChuyenDi) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		
		String sql = "select * from chuyendulich "
				+ "where loaichuyendi_id = :x and trangthai = 'CHUA_KHOI_HANH'";
		
		try {
			tr.begin();
			List<ChuyenDuLich> dsChuyenDuLich = session
					.createNativeQuery(sql, ChuyenDuLich.class)
					.setParameter("x", loaiChuyenDi.getMaLoaiChuyen())
					.getResultList();
			tr.commit();
			return dsChuyenDuLich;
		} catch (Exception e) {
			tr.rollback();
		}
		return null;
	}
	
	@Override
    public List<ChuyenDuLich> searchChuyenDuLich(String diaChi, String tinh, String ngayKhoiHanh, int startDay, int endDay) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select distinct c.* from chuyendulich c inner join chitietthamquan ct "
                + "on c.chuyen_id = ct.chuyen_id inner join diadanh d "
                + "on d.diadanh_id = ct.diadanh_id "
                + "where noiKhoiHanh = N'"+ diaChi +"' and tinh = N'"+ tinh +"' "
                + "and ngaykhoihanh = '"+ ngayKhoiHanh +"' and (DATEDIFF(d, ngayKhoiHanh, ngayKetThuc) >= "+ startDay +" and DATEDIFF(d, ngayKhoiHanh, ngayKetThuc) <= "+ endDay +") "
                + "and trangthai = 'CHUA_KHOI_HANH'";
        
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
    public List<ChuyenDuLich> getDsChuyenDuLichDaDat(KhachHang khachHang) throws RemoteException {
        Session session = sessionFactory.openSession();
        Transaction tr = session.getTransaction();

        String sql = "select c.* from KhachHang kh inner join dondatve d "
                + "on kh.khachhang_id = d.khachhang_id inner join chuyendulich c "
                + "on c.chuyen_id = d.chuyen_id "
                + "where kh.khachhang_id = N'"+ khachHang.getMaKhachHang() +"'";
        
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
}
