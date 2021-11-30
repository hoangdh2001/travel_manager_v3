package model;

import java.awt.Color;
import java.io.Serializable;

public enum TrangThaiChuyenDi implements Serializable {
    DANG_XU_LY("Đang xử lý", new Color(255, 184, 108)),
    CHUA_KHOI_HANH("Chưa khởi hành", new Color(152, 247, 111)),
    SAP_BAT_DAU("Sắp bắt đầu", new Color(111, 247, 243)),
    HUY("Hủy", new Color(253, 89, 89)),
    DANG_KHOI_HANH("Đang khởi hành", new Color(250, 237, 126 )),
    SAP_KET_THUC("Sắp kết thúc", new Color(224, 250, 126 )),
    KET_THUC("Kết thúc", new Color(250, 66, 66 ));
    private final String trangThai;
    private final Color mauTrangThai;

    /**
     * @param trangThai
     * @param mauTrangThai
     */
    private TrangThaiChuyenDi(String trangThai, Color mauTrangThai) {
        this.trangThai = trangThai;
        this.mauTrangThai = mauTrangThai;

    }
    
    public  static  TrangThaiChuyenDi getValueTrangThaiChuyenDi(String trangThai){
        for (TrangThaiChuyenDi i : TrangThaiChuyenDi.values()) {
            if (i.trangThai.equals(trangThai)) {
                return i;
            }
        }
        
        return null;
    }

    /**
     * @return the trangThai
     */
    public String getTrangThai() {
        return trangThai;
    }

    /**
     * @return the mauTrangThai
     */
    public Color getMauTrangThai() {
        return mauTrangThai;
    }
}
