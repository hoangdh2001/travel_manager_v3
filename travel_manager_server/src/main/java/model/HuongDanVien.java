package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "huongdanvien")
public class HuongDanVien implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2650284837914766431L;
	@Id
    @Column(name = "huongdanvien_id")
    private String maHDV;
    @Column(name = "ten", columnDefinition = "nvarchar(255)", nullable = false)
    private String tenHDV;
    @Column(name = "CCCD", columnDefinition = "varchar(12)", nullable = false)
    private String cCCD;
    @Column(name = "gioitinh")
    private boolean gioiTinh;
    @Column(name = "ngaysinh", columnDefinition = "date")
    private String ngaySinh;
    @Column(name = "sdt", columnDefinition = "varchar(10)", nullable = false, unique = true)
    private String soDienThoai;
    private String email;
    @Column(name = "phi", columnDefinition = "money", nullable = false)
    private double phiHDV;

    /**
     * @param maHDV
     * @param tenHDV
     * @param cCCD
     * @param gioiTinh
     * @param ngaySinh
     * @param soDienThoai
     * @param email
     * @param phiHDV
     */
    public HuongDanVien(String maHDV, String tenHDV, String cCCD, boolean gioiTinh, String ngaySinh, String soDienThoai,
            String email, double phiHDV) {
        this.maHDV = maHDV;
        this.tenHDV = tenHDV;
        this.cCCD = cCCD;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.phiHDV = phiHDV;
    }

    /**
     *
     */
    public HuongDanVien() {
    }

    /**
     * @return the maHDV
     */
    public String getMaHDV() {
        return maHDV;
    }

    /**
     * @param maHDV the maHDV to set
     */
    public void setMaHDV(String maHDV) {
        this.maHDV = maHDV;
    }

    /**
     * @return the tenHDV
     */
    public String getTenHDV() {
        return tenHDV;
    }

    /**
     * @param tenHDV the tenHDV to set
     */
    public void setTenHDV(String tenHDV) {
        this.tenHDV = tenHDV;
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
     * @return the gioiTinh
     */
    public boolean isGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the ngaySinh
     */
    public String getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
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
     * @return the phiHDV
     */
    public double getPhiHDV() {
        return phiHDV;
    }

    /**
     * @param phiHDV the phiHDV to set
     */
    public void setPhiHDV(double phiHDV) {
        this.phiHDV = phiHDV;
    }

    @Override
    public String toString() {
        return "HuongDanVien [maHDV=" + maHDV + ", tenHDV=" + tenHDV + ", cCCD=" + cCCD + ", gioiTinh=" + gioiTinh
                + ", ngaySinh=" + ngaySinh + ", soDienThoai=" + soDienThoai + ", email=" + email + ", phiHDV=" + phiHDV
                + "]";
    }
}
