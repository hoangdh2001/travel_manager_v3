package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dondatve")
public class DonDatVe implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -765375738450899885L;
	@Id
    @Column(name = "dondatve_id")
    private String maDonDatVe;
    @Column(name = "ngaydat")
    private Date ngayDat;
    @ManyToOne
    @JoinColumn(name = "khachhang_id", nullable = false)
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "chuyen_id", nullable = false)
    private ChuyenDuLich chuyenDuLich;
    @ManyToOne
    @JoinColumn(name = "huongdanvien_id", nullable = true)
    private HuongDanVien huongDanVien;
    @Enumerated(EnumType.STRING)
    private TrangThaiDonDat trangThai;
    @Column(name = "soluong")
    private int soLuong;
    @Column(name = "thanhtien", columnDefinition = "money")
    private double thanhTien;
    /**
     * @param maDonDatVe
     * @param khachHang
     * @param chuyenDuLich 
     */
    public DonDatVe(String maDonDatVe, KhachHang khachHang, ChuyenDuLich chuyenDuLich) {
        this.maDonDatVe = maDonDatVe;
        this.ngayDat = new Date();
        this.trangThai = TrangThaiDonDat.DANG_XU_LY;
        this.khachHang = khachHang;
        this.chuyenDuLich = chuyenDuLich;
        this.thanhTien = getThanhTien();
    }
    
    /**
     *
     */
    public DonDatVe() {
        this.ngayDat = new Date();
        this.trangThai = TrangThaiDonDat.DANG_XU_LY;
        this.thanhTien = getThanhTien();
    }

    /**
     * @return the maDonDatVe
     */
    public String getMaDonDatVe() {
        return maDonDatVe;
    }

    /**
     * @param maDonDatVe the maDonDatVe to set
     */
    public void setMaDonDatVe(String maDonDatVe) {
        this.maDonDatVe = maDonDatVe;
    }

    /**
     * @return the ngayDat
     */
    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * @param ngayDat the ngayDat to set
     */
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    /**
     * @return the khachHang
     */
    public KhachHang getKhachHang() {
        return khachHang;
    }

    /**
     * @param khachHang the khachHang to set
     */
    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    /**
     * @return the chuyenDuLich
     */
    public ChuyenDuLich getChuyenDuLich() {
        return chuyenDuLich;
    }

    /**
     * @param chuyenDuLich the chuyenDuLich to set
     */
    public void setChuyenDuLich(ChuyenDuLich chuyenDuLich) {
        this.chuyenDuLich = chuyenDuLich;
    }

    /**
     * @return the huongDanVien
     */
    public HuongDanVien getHuongDanVien() {
        return huongDanVien;
    }

    /**
     * @param huongDanVien the huongDanVien to set
     */
    public void setHuongDanVien(HuongDanVien huongDanVien) {
        this.huongDanVien = huongDanVien;
    }

    /**
     * @return the trangThai
     */
    public TrangThaiDonDat getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(TrangThaiDonDat trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }
    /**
     * @param soLuong 
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    /**
     * @return the thanhTien
     */
    public double getThanhTien() {
        return thanhTien = chuyenDuLich.getGiaChuyenDi() * soLuong + (huongDanVien == null ? 0:huongDanVien.getPhiHDV());
    }

    @Override
    public String toString() {
        return "DonDatVe [maDonDatVe=" + maDonDatVe + ", ngayDat=" + ngayDat + ", khachHang=" + khachHang
                + ", chuyenDuLich=" + chuyenDuLich + ", huongDanVien=" + huongDanVien
                + ", trangThai=" + trangThai + ", soLuong=" + soLuong + ", thanhTien="
                + thanhTien + "]";
    }
}
