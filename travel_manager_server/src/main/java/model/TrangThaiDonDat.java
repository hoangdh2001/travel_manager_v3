package model;

import java.awt.Color;
import java.io.Serializable;

public enum TrangThaiDonDat implements Serializable {
    DANG_XU_LY("Đang xử lý", Color.ORANGE),
    HUY("Hủy", Color.RED),
    HOAN_THANH("Hoàn thành", Color.DARK_GRAY);
    private final String trangThai;
    private final Color mauTrangThai;

    /**
     * @param trangThai
     * @param mauTrangThai
     */
    private TrangThaiDonDat(String trangThai, Color mauTrangThai) {
        this.trangThai = trangThai;
        this.mauTrangThai = mauTrangThai;
    }
    
    //nh
    public static TrangThaiDonDat getValueTrangThaiDonDat(String trangThai) {
        for (TrangThaiDonDat i : TrangThaiDonDat.values()) {
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
