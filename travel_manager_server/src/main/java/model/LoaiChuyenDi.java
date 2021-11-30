package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "loaichuyendi")
@NamedQueries({
        @NamedQuery(name = "getDsLoaiChuyenDi", query = "select l from LoaiChuyenDi l")
})
public class LoaiChuyenDi implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1774046342591741309L;
	@Id
    @Column(name = "loaichuyen_id")
    private String maLoaiChuyen;
    @Column(name = "tenloai", columnDefinition = "nvarchar(255)", nullable = false)
    private String tenLoaiChuyen;
    @Column(name = "mauloai", nullable = false)
    private String mauLoai;

    /**
     * @param maLoaiChuyen
     * @param tenLoaiChuyen
     * @param mauLoai
     */
    public LoaiChuyenDi(String maLoaiChuyen, String tenLoaiChuyen, String mauLoai) {
        this.maLoaiChuyen = maLoaiChuyen;
        this.tenLoaiChuyen = tenLoaiChuyen;
        this.mauLoai = mauLoai;
    }

    /**
     *
     */
    public LoaiChuyenDi() {
    }

    /**
     * @return the maLoaiChuyen
     */
    public String getMaLoaiChuyen() {
        return maLoaiChuyen;
    }

    /**
     * @param maLoaiChuyen
     */
    public void setMaLoaiChuyen(String maLoaiChuyen) {
        this.maLoaiChuyen = maLoaiChuyen;
    }

    /**
     * @return tenLoaiChuyen
     */
    public String getTenLoaiChuyen() {
        return tenLoaiChuyen;
    }

    /**
     * @param tenLoaiChuyen
     */
    public void setTenLoaiChuyen(String tenLoaiChuyen) {
        this.tenLoaiChuyen = tenLoaiChuyen;
    }

    /**
     * @return the mauLoai
     */
    public String getMauLoai() {
        return mauLoai;
    }

    /**
     * @param mauLoai
     */
    public void setMauLoai(String mauLoai) {
        this.mauLoai = mauLoai;
    }

    @Override
    public String toString() {
        return tenLoaiChuyen;
    }
}
