package model;

import java.io.Serializable;

public enum DongTour implements Serializable {
    CAO_CAP("Cao cấp"),
    TIET_KIEM("Tiết kiệm"),
    GIA_TOT("Giá tốt");
    private final String dongTour;

    /**
     * @param dongTour
     */
    private DongTour(String dongTour) {
        this.dongTour = dongTour;
    }
    
    public  static  DongTour getEnumDongTour(String dongTour){
        for (DongTour i : DongTour.values()) {
            if (i.dongTour.equals(dongTour)) {
                return i;
            }
        }
        
        return null;
    }

    /**
     * @return the dongTour
     */
    public String getDongTour() {
        return dongTour;
    }

}
