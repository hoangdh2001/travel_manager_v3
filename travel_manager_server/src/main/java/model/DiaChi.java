package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diachi")
public class DiaChi implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -867626146873062672L;
	@Id
    @Column(name = "diachi_id")
    private String maDiaChi;
    @Column(name = "phuongxa", columnDefinition = "nvarchar(255)")
    private String phuongXa;
    @Column(name = "quanhuyen", columnDefinition = "nvarchar(255)")
    private String quanHuyen;
    @Column(name = "tinhthanh", columnDefinition = "nvarchar(255)")
    private String tinhThanh;

    /**
     * @param maDiaChi
     * @param phuongXa
     * @param quanHuyen
     * @param tinhThanh
     */
    public DiaChi(String maDiaChi, String phuongXa, String quanHuyen, String tinhThanh) {
        this.maDiaChi = maDiaChi;
        this.phuongXa = phuongXa;
        this.quanHuyen = quanHuyen;
        this.tinhThanh = tinhThanh;
    }

    /**
     *
     */
    public DiaChi() {
    }

    /**
     * @return the maDiaChi
     */
    public String getMaDiaChi() {
        return maDiaChi;
    }

    /**
     * @param maDiaChi the maDiaChi to set
     */
    public void setMaDiaChi(String maDiaChi) {
        this.maDiaChi = maDiaChi;
    }

    /**
     * @return the phuongXa
     */
    public String getPhuongXa() {
        return phuongXa;
    }

    /**
     * @param phuongXa the phuongXa to set
     */
    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    /**
     * @return the quanHuyen
     */
    public String getQuanHuyen() {
        return quanHuyen;
    }

    /**
     * @param quanHuyen the quanHuyen to set
     */
    public void setQuanHuyen(String quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    /**
     * @return the tinhThanh
     */
    public String getTinhThanh() {
        return tinhThanh;
    }

    /**
     * @param tinhThanh the tinhThanh to set
     */
    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    @Override
    public String toString() {
        return "DiaChi [maDiaChi=" + maDiaChi + ", phuongXa=" + phuongXa + ", quanHuyen=" + quanHuyen + ", tinhThanh="
                + tinhThanh + "]";
    }

}
