package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "khachhang")
public class KhachHang implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4504179503983770156L;
	@Id
    @Column(name = "khachhang_id")
    private String maKhachHang;
    @Column(name = "hoten", columnDefinition = "nvarchar(255)", nullable = false)
    private String hoTen;
    @Column(columnDefinition = "image", nullable = false)
    private byte[] avatar;
    @Column(name = "CCCD", columnDefinition = "varchar(12)", nullable = false)
    private String cCCD;
    @Column(name = "sdt", columnDefinition = "varchar(10)", nullable = false, unique = true)
    private String soDienThoai;
    @Column(unique = true)
    private String email;
    @ManyToOne
    @JoinColumn(name = "diachi_id", nullable = false)
    private DiaChi diaChi;
    @Column(name = "matkhau", nullable = false)
    private byte[] matKhau;
    @OneToMany(mappedBy = "maChuyen", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ChuyenDuLich> chuyenDiDaThich;

    /**
     * @param maKhachHang
     * @param hoTen
     * @param avatar
     * @param cCCD
     * @param soDienThoai
     * @param email
     * @param diaChi
     * @param matKhau
     */
    public KhachHang(String maKhachHang, String hoTen, byte[] avatar, String cCCD, String soDienThoai, String email, DiaChi diaChi,
            byte[] matKhau) {
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
        this.avatar = avatar;
        this.cCCD = cCCD;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
        this.chuyenDiDaThich = new ArrayList<>();
    }

    public void themChuyenDiDaThich(ChuyenDuLich chuyenDuLich) {
        if(!chuyenDiDaThich.contains(chuyenDuLich)) {
            chuyenDiDaThich.add(chuyenDuLich);
        }
    }

    /**
     *
     */
    public KhachHang() {
        this.chuyenDiDaThich = new ArrayList<>();
    }

    /**
     * @return the maKhachHang
     */
    public String getMaKhachHang() {
        return maKhachHang;
    }

    /**
     * @param maKhachHang the maKhachHang to set
     */
    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    /**
     * @return the hoTen
     */
    public String getHoTen() {
        return hoTen;
    }

    /**
     * @return avatar
     */
    public byte[] getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    /**
     * @return the cCCD
     */
    public String getcCCD() {
        return cCCD;
    }

    /**
     * @param cCCD the cCCD to set
     */
    public void setcCCD(String cCCD) {
        this.cCCD = cCCD;
    }

    /**
     * @return the soDienThoai
     */
    public String getSoDienThoai() {
        return soDienThoai;
    }

    /**
     * @param soDienThoai the soDienThoai to set
     */
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the diaChi
     */
    public DiaChi getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }

    /**
     * @return the matKhau
     */
    public byte[] getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(byte[] matKhau) {
        this.matKhau = matKhau;
    }

    /**
     * @return the chuyenDiDaThich
     */
    public List<ChuyenDuLich> getChuyenDiDaThich() {
        return chuyenDiDaThich;
    }

    /**
     * @param chuyenDiDaThich
     */
    public void setChuyenDiDaThich(List<ChuyenDuLich> chuyenDiDaThich) {
        this.chuyenDiDaThich = chuyenDiDaThich;
    }

    @Override
    public String toString() {
        return "KhachHang [maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", cCCD=" + cCCD + ", soDienThoai="
                + soDienThoai + ", email=" + email + ", diaChi=" + diaChi + ", matKhau=" + Arrays.toString(matKhau)
                + "]";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.maKhachHang);
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
        final KhachHang other = (KhachHang) obj;
        if (!Objects.equals(this.maKhachHang, other.maKhachHang)) {
            return false;
        }
        return true;
    }

}
