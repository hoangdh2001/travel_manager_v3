package model;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chitietthamquan")
@IdClass(ChiTietThamQuan_PK.class)
public class ChiTietThamQuan implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1627122878645052802L;
	@Id
    @ManyToOne
    @JoinColumn(name = "chuyen_id")
    private ChuyenDuLich chuyenDuLich;
    @Id
    @ManyToOne
    @JoinColumn(name = "diadanh_id")
    private DiaDanh diaDanh;
    @Column(name = "anhdiadanh", columnDefinition = "image", nullable = false)
    private byte[] anhDiaDanh;

    /**
     * @param chuyenDuLich
     * @param diaDanh
     * @param anhDiaDanh
     */
    public ChiTietThamQuan(ChuyenDuLich chuyenDuLich, DiaDanh diaDanh, byte[] anhDiaDanh) {
        this.chuyenDuLich = chuyenDuLich;
        this.diaDanh = diaDanh;
        this.anhDiaDanh = anhDiaDanh;
    }

    /**
     *
     */
    public ChiTietThamQuan() {
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
     * @return the diaDanh
     */
    public DiaDanh getDiaDanh() {
        return diaDanh;
    }

    /**
     * @param diaDanh the diaDanh to set
     */
    public void setDiaDanh(DiaDanh diaDanh) {
        this.diaDanh = diaDanh;
    }

    /**
     * @return the anhDiaDanh
     */
    public byte[] getAnhDiaDanh() {
        return anhDiaDanh;
    }

    /**
     * @param anhDiaDanh the anhDiaDanh to set
     */
    public void setAnhDiaDanh(byte[] anhDiaDanh) {
        this.anhDiaDanh = anhDiaDanh;
    }

    @Override
    public String toString() {
        return "ChiTietThamQuan [chuyenDuLich=" + chuyenDuLich + ", diaDanh=" + diaDanh + ", anhDiaDanh="
                + Arrays.toString(anhDiaDanh) + "]";
    }
}
