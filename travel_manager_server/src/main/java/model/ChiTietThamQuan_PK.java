package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class ChiTietThamQuan_PK implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5518980984269155135L;
	private String chuyenDuLich;
    private String diaDanh;

    public ChiTietThamQuan_PK() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final ChiTietThamQuan_PK other = (ChiTietThamQuan_PK) obj;
        if (!Objects.equals(this.chuyenDuLich, other.chuyenDuLich)) {
            return false;
        }
        if (!Objects.equals(this.diaDanh, other.diaDanh)) {
            return false;
        }
        return true;
    }

}
