package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chuyendulich")
@NamedQueries({
    @NamedQuery(name = "getChuyenDuLichs", query = "select cd from ChuyenDuLich cd")
})
public class ChuyenDuLich implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2107688028694700843L;
	@Id
    @Column(name = "chuyen_id")
    private String maChuyen;
    @ManyToOne
    @JoinColumn(name = "loaichuyendi_id")
    private LoaiChuyenDi loaiChuyenDi;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "giachuyen", columnDefinition = "money")
    private double giaChuyenDi;
    @ManyToOne
    @JoinColumn(name = "noikhoihanh")
    private DiaChi noiKhoiHanh;
    @OneToMany(mappedBy = "chuyenDuLich", fetch = FetchType.EAGER)
    private List<ChiTietThamQuan> dsChiTietThamQuan;
    @Column(name = "ngaykhoihanh", columnDefinition = "datetime")
    private Date ngayKhoiHanh;
    @Column(name = "ngayketthuc", columnDefinition = "datetime")
    private Date ngayKetThuc;
    @Enumerated(EnumType.STRING)
    @Column(name = "trangthai")
    private TrangThaiChuyenDi trangThai;
    @Enumerated(EnumType.STRING)
    @Column(name = "phuongtien")
    private PhuongTien phuongTien;
    @Enumerated(EnumType.STRING)
    @Column(name = "dongtour")
    private DongTour dongTour;
    @Column(name = "mota", columnDefinition = "nvarchar(max)")
    private String moTa;
    @Column(name = "soluong")
    private int soLuong;
    @ManyToOne
    @JoinColumn(name = "nhanvien_id")
    private NhanVien nhanVien;

    /**
     * @param maChuyen
     * @param giaChuyenDi
     * @param loaiChuyenDi
     * @param noiKhoiHanh
     * @param ngayTao
     * @param ngayKhoiHanh
     * @param ngayKetThuc
     * @param trangThai
     * @param phuongTien
     * @param dongTour
     * @param moTa
     * @param soLuong
     * @param nhanVien
     */
    public ChuyenDuLich(String maChuyen, double giaChuyenDi, LoaiChuyenDi loaiChuyenDi, Date ngayTao, Date ngayKhoiHanh,
            Date ngayKetThuc, TrangThaiChuyenDi trangThai,
            PhuongTien phuongTien, DongTour dongTour, String moTa, int soLuong, NhanVien nhanVien, DiaChi noiKhoiHanh) {
        this.maChuyen = maChuyen;
        this.giaChuyenDi = giaChuyenDi;
        this.loaiChuyenDi = loaiChuyenDi;
        this.noiKhoiHanh = noiKhoiHanh;
        this.ngayTao = ngayTao;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.phuongTien = phuongTien;
        this.dongTour = dongTour;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.dsChiTietThamQuan = new ArrayList<>();
        this.nhanVien = nhanVien;
        this.noiKhoiHanh = noiKhoiHanh;
    }

    /**
     * @param trangThai
     */
    public ChuyenDuLich(TrangThaiChuyenDi trangThai) {
        this.trangThai = trangThai;
        this.dsChiTietThamQuan = new ArrayList<>();
    }

    /**
     *
     */
    public ChuyenDuLich() {
        this(TrangThaiChuyenDi.DANG_XU_LY);
    }

    /**
     * @param chuyenDuLich
     * @param diaDanh
     * @param anhDiaDanh
     */
    public void themChiTietThamQuan(DiaDanh diaDanh, byte[] anhDiaDanh) {
        ChiTietThamQuan chiTietThamQuan = new ChiTietThamQuan(this, diaDanh, anhDiaDanh);
        dsChiTietThamQuan.add(chiTietThamQuan);
    }

    /**
     * @return the maChuyen
     */
    public String getMaChuyen() {
        return maChuyen;
    }

    /**
     * @param maChuyen the maChuyen to set
     */
    public void setMaChuyen(String maChuyen) {
        this.maChuyen = maChuyen;
    }

    /**
     * @return the loaiChuyenDi
     */
    public LoaiChuyenDi getLoaiChuyenDi() {
        return loaiChuyenDi;
    }

    /**
     * @param loaiChuyenDi the dsLoaiChuyenDi to set
     */
    public void setLoaiChuyenDi(LoaiChuyenDi loaiChuyenDi) {
        this.loaiChuyenDi = loaiChuyenDi;
    }

    /**
     * @return the noiKhoiHanh
     */
    public DiaChi getNoiKhoiHanh() {
        return noiKhoiHanh;
    }

    /**
     * @param noiKhoiHanh
     */
    public void setNoiKhoiHanh(DiaChi noiKhoiHanh) {
        this.noiKhoiHanh = noiKhoiHanh;
    }

    /**
     * @return the nhanVien
     */
    public NhanVien getNhanVien() {
        return nhanVien;
    }

    /**
     * @param nhanVien
     */
    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    /**
     * @return ngayTao
     */
    public Date getNgayTao() {
        return ngayTao;
    }

    /**
     * @param ngayTao
     */
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    /**
     * @return the giaChuyenDi
     */
    public double getGiaChuyenDi() {
        return giaChuyenDi;
    }

    /**
     * @param giaChuyenDi the giaChuyenDi to set
     */
    public void setGiaChuyenDi(double giaChuyenDi) {
        this.giaChuyenDi = giaChuyenDi;
    }

    /**
     * @return the dsChiTietThamQuan
     */
    public List<ChiTietThamQuan> getDsChiTietThamQuan() {
        return dsChiTietThamQuan;
    }

    /**
     * @param dsChiTietThamQuan the dsChiTietThamQuan to set
     */
    public void setDsChiTietThamQuan(List<ChiTietThamQuan> dsChiTietThamQuan) {
        this.dsChiTietThamQuan = dsChiTietThamQuan;
    }

    /**
     * @return the ngayKhoiHanh
     */
    public Date getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    /**
     * @param ngayKhoiHanh the ngayKhoiHanh to set
     */
    public void setNgayKhoiHanh(Date ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    /**
     * @return the ngayKetThuc
     */
    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    /**
     * @param ngayKetThuc the ngayKetThuc to set
     */
    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    /**
     * @return the moTa
     */
    public String getMoTa() {
        return moTa;
    }

    /**
     * @param moTa the moTa to set
     */
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the trangThai
     */
    public TrangThaiChuyenDi getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai
     */
    public void setTrangThai(TrangThaiChuyenDi trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the phuongTien
     */
    public PhuongTien getPhuongTien() {
        return phuongTien;
    }

    /**
     * @param phuongTien
     */
    public void setPhuongTien(PhuongTien phuongTien) {
        this.phuongTien = phuongTien;
    }

    /**
     * @return the dongTour
     */
    public DongTour getDongTour() {
        return dongTour;
    }

    /**
     * @param dongTour
     */
    public void setDongTour(DongTour dongTour) {
        this.dongTour = dongTour;
    }

    @Override
    public String toString() {
        return "ChuyenDuLich [maChuyen=" + maChuyen + ", dsLoaiChuyenDi=" + loaiChuyenDi + ", giaChuyenDi="
                + giaChuyenDi + ", dsChiTietThamQuan=" + dsChiTietThamQuan + ", ngayKhoiHanh=" + ngayKhoiHanh
                + ", ngayKetThuc=" + ngayKetThuc + ", moTa=" + moTa + ", soLuong=" + soLuong
                + "]";
    }

    public Object[] convertToRowTable() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DecimalFormat df2 = new DecimalFormat("###,###,###");
        return new Object[]{maChuyen, sdf.format(ngayKhoiHanh), sdf.format(ngayKetThuc), soLuong,
            loaiChuyenDi.getTenLoaiChuyen(), dongTour.getDongTour(), phuongTien.getPhuongTien(), df2.format(giaChuyenDi), trangThai,
            sdf.format(ngayTao)};
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.maChuyen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChuyenDuLich other = (ChuyenDuLich) obj;
        if (!Objects.equals(this.maChuyen, other.maChuyen)) {
            return false;
        }
        return true;

    }

}
