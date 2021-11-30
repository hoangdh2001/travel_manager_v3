package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "diadanh")
@NamedQueries({
    @NamedQuery(name = "getDiaDanhs", query = "select x from DiaDanh x")
})
public class DiaDanh implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4291773128276849480L;
	@Id
    @Column(name = "diadanh_id")
    private String maDiaDanh;
    @Column(name = "tendiadanh", columnDefinition = "nvarchar(255)", nullable = false)
    private String tenDiaDanh;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tinh;

    /**
     * @param maDiaDanh
     * @param tenDiaDanh
     * @param tinh
     */
    public DiaDanh(String maDiaDanh, String tenDiaDanh, String tinh) {
        this.maDiaDanh = maDiaDanh;
        this.tenDiaDanh = tenDiaDanh;
        this.tinh = tinh;
    }

    /**
     *
     */
    public DiaDanh() {
    }

    /**
     * @return the maDiaDanh
     */
    public String getMaDiaDanh() {
        return maDiaDanh;
    }

    /**
     * @param maDiaDanh the maDiaDanh to set
     */
    public void setMaDiaDanh(String maDiaDanh) {
        this.maDiaDanh = maDiaDanh;
    }

    /**
     * @return the tenDiaDanh
     */
    public String getTenDiaDanh() {
        return tenDiaDanh;
    }

    /**
     * @param tenDiaDanh the tenDiaDanh to set
     */
    public void setTenDiaDanh(String tenDiaDanh) {
        this.tenDiaDanh = tenDiaDanh;
    }

    /**
     * @return the tinh
     */
    public String getTinh() {
        return tinh;
    }

    /**
     * @param tinh the tinh to set
     */
    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    @Override
    public String toString() {
        return "DiaDanh [maDiaDanh=" + maDiaDanh + ", tenDiaDanh=" + tenDiaDanh + ", tinh=" + tinh + "]";
    }
    
    public Object[] convertToRowTable() {
        return new Object[]{maDiaDanh, tenDiaDanh, tinh};
    }
}
