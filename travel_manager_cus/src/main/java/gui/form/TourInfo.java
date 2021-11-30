package gui.form;

import dao.ChuyenDuLich_DAO;
import dao.impl.ChuyenDuLichImpl;
import gui.MainFrame;
import gui.component.BoxTour;
import gui.component.Map;
import model.ChiTietThamQuan;
import model.ChuyenDuLich;
import com.huyhoang.swing.button.ButtonBadges;
import com.huyhoang.swing.button.ToggleButtonBadges;
import com.huyhoang.swing.event.EventTour;
import com.huyhoang.swing.graphics.ShadowType;
import com.huyhoang.swing.image.PictureBox;
import com.huyhoang.swing.label.LabelResizingShadow;
import com.huyhoang.swing.label.LabelRibbon;
import com.huyhoang.swing.panel.LayerPaneGradient;
import com.huyhoang.swing.slideshow.SlideShowTransparent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;

public class TourInfo extends javax.swing.JLayeredPane {

    private LayerPaneGradient pnlCenter;
    private SlideShowTransparent slide;
    private LayerPaneGradient session;
    private MigLayout layout;
    private ToggleButtonBadges btnLike;
    private EventTour event;
    private ChuyenDuLich chuyenDuLich;
    private LabelRibbon lblLoaiChuyen;
    private JLabel lblNgayKhoiHanh;
    private JLabel lblGia;
    private JLabel lblthoiGian;
    private JLabel lblPhuongTien;
    private JLabel lblNoiKhoiHanh;
    private JLabel lblDiemDen;
    private JLabel lblDescription;
    private LabelResizingShadow tourName;
    private LabelResizingShadow lblDiaDanh;
    private final DecimalFormat df = new DecimalFormat("#,##0 VND");
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private ButtonBadges btnDat;
    private ChuyenDuLich_DAO chuyenDuLich_DAO;
    private Map mapTour;

    public void addEventLike(ItemListener action) {
        btnLike.addItemListener(action);
    }

    public void addEventTour(EventTour event) {
        this.event = event;
    }

    public void addEventBookTour(ActionListener action) {
        btnDat.addActionListener(action);
    }

    public ChuyenDuLich getChuyenDuLich() {
        return chuyenDuLich;
    }

    public void setChuyenDuLich(ChuyenDuLich chuyenDuLich) {
        this.chuyenDuLich = chuyenDuLich;
        loadData();
    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                slide.removeAllImage();
                String diaDanh = "[";
                String tinh = "Du lịch ";
                String diemDen = "";
                List<ChiTietThamQuan> dsChiTietThamQuan = chuyenDuLich.getDsChiTietThamQuan();
                if (dsChiTietThamQuan.size() > 0) {
                    for (int i = 0; i < dsChiTietThamQuan.size(); i++) {
                        slide.addImage(new PictureBox(new ImageIcon(dsChiTietThamQuan.get(i).getAnhDiaDanh())));
                        if (i == (dsChiTietThamQuan.size() - 1)) {
                            diaDanh = diaDanh + dsChiTietThamQuan.get(i).getDiaDanh().getTenDiaDanh() + "]";
                            tinh = tinh + dsChiTietThamQuan.get(i).getDiaDanh().getTinh();
                            diemDen = diemDen + dsChiTietThamQuan.get(i).getDiaDanh().getTinh();
                        } else {
                            diaDanh = diaDanh + dsChiTietThamQuan.get(i).getDiaDanh().getTenDiaDanh() + " - ";
                            tinh = tinh + dsChiTietThamQuan.get(i).getDiaDanh().getTinh() + " - ";
                            diemDen = diemDen + dsChiTietThamQuan.get(i).getDiaDanh().getTinh() + " - ";
                        }
                    }
                    slide.select(0);
                }
                tourName.setText(tinh);
                lblDiaDanh.setText(diaDanh);
                lblLoaiChuyen.setText(chuyenDuLich.getLoaiChuyenDi().getTenLoaiChuyen());
                lblGia.setText(df.format(chuyenDuLich.getGiaChuyenDi()));
                lblNgayKhoiHanh.setText("NGÀY KHỞI HÀNH: " + sdf.format(chuyenDuLich.getNgayKhoiHanh()));
                lblPhuongTien.setText("PHƯƠNG TIỆN: " + chuyenDuLich.getPhuongTien().getPhuongTien());
                lblNoiKhoiHanh.setText("NĂM KHỞI HÀNH: " + chuyenDuLich.getNoiKhoiHanh().getTinhThanh());
                lblDiemDen.setText("ĐIỂM ĐẾN: " + diemDen);
                String lblText = String.format("<html><div style=\"width:%dpx;font-size:10px;font-family:'Segoe UI', Arial, sans-serif;\">%s</div></html>", 500, chuyenDuLich.getMoTa());
                lblDescription.setText(lblText);
                lblthoiGian.setText("THỜI GIAN: " + getDifferenceDays(chuyenDuLich.getNgayKhoiHanh(), chuyenDuLich.getNgayKetThuc()) + " ngày");
//                if(MainFrame.khachHang.getChuyenDiDaThich().contains(chuyenDuLich))  {
//                    btnLike.setSelected(true);
//                } else {
//                    btnLike.setSelected(false);
//                }
                List<ChuyenDuLich> dsChuyenDuLich = null;
				try {
					dsChuyenDuLich = chuyenDuLich_DAO.getDsChuyenDuLichNgauNhien(chuyenDuLich.getMaChuyen());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                mapTour.removeAllBox();
                if (dsChuyenDuLich != null) {
                    for (ChuyenDuLich chuyenDuLich1 : dsChuyenDuLich) {
                        BoxTour boxTour = new BoxTour();
                        boxTour.setChuyenDuLich(chuyenDuLich1);
                        boxTour.addEventBoxTour(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                event.openTour(chuyenDuLich1);
                                boxTour.refresh();
                            }
                        });
                        mapTour.addBox(boxTour, 200, 280);
                    }

                }
            }
        }).start();
    }

    private long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public TourInfo() throws RemoteException, MalformedURLException, NotBoundException {
        this.chuyenDuLich_DAO = (ChuyenDuLich_DAO) Naming.lookup("rmi://localhost:1099/chuyendulich_dao");
        initComponents();
        builldDisplay();
    }

    private void builldDisplay() {
        layout = new MigLayout("fill, insets 0, wrap", "[fill]", "300[fill]");
        setLayout(layout);
        createTitle();
        createPaneCenter();
        createSession();
        createSlide();
    }

    private void createSlide() {
        slide = new SlideShowTransparent();
        PictureBox picture1 = new PictureBox();
        picture1.setImage(new ImageIcon(getClass().getResource("/icon/slide1.jpg")));
        PictureBox picture2 = new PictureBox();
        picture2.setImage(new ImageIcon(getClass().getResource("/icon/slide2.jpeg")));
        PictureBox picture3 = new PictureBox();
        picture3.setImage(new ImageIcon(getClass().getResource("/icon/slide3.jpg")));
        slide.initSlideshow(picture1, picture2, picture3);
        slide.start();
        add(slide, "pos 0al 0al n n, w 100%, h 500!");
    }

    private void createSession() {
        session = new LayerPaneGradient();
        session.setColor1(new Color(34, 34, 34, 10));
        session.setColor2(new Color(10, 10, 10));
        setLayer(session, JLayeredPane.POPUP_LAYER);
        add(session, "pos 0al 230 n n, w 100%, h 280!");
    }

    private void createPaneCenter() {
        pnlCenter = new LayerPaneGradient();
        MigLayout layout2 = new MigLayout("fill, insets 0, wrap", "20[fill]push[fill]20", "100[]10[]10[]20[]5[]20[]20");
        pnlCenter.setLayout(layout2);

        btnDat = new ButtonBadges();
        btnDat.setBackground(new Color(29, 185, 84));
        btnDat.setIcon(new ImageIcon(getClass().getResource("/icon/booking.png")));
        pnlCenter.add(btnDat, "pos 30 20 n n, w 54!, h 54!");

        btnDat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                layout2.setComponentConstraints(btnDat, "pos 27 17 n n, w 60!, h 60!");
                pnlCenter.revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                layout2.setComponentConstraints(btnDat, "pos 30 20 n n, w 55!, h 55!");
                pnlCenter.revalidate();
            }
        });

        pnlCenter.setColor1(new Color(34, 34, 34, 150));
        setLayer(pnlCenter, POPUP_LAYER);

        btnLike = new ToggleButtonBadges();
        btnLike.setBackground(new Color(0, 0, 0, 0));
        btnLike.setIcon(new ImageIcon(getClass().getResource("/icon/like.png")));
        btnLike.setRolloverIcon(new ImageIcon(getClass().getResource("/icon/like_over.png")));
        btnLike.setSelectedIcon(new ImageIcon(getClass().getResource("/icon/like_selected.png")));
        pnlCenter.add(btnLike, "pos 104 20 n n, w 54!, h 54!");

        lblLoaiChuyen = new LabelRibbon();
        lblLoaiChuyen.setText("Du lịch tham quan");
        lblLoaiChuyen.setForeground(Color.WHITE);
        lblLoaiChuyen.setBackground(new Color(29, 185, 84));
        lblLoaiChuyen.setShadowOpacity(0.3f);
        lblLoaiChuyen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pnlCenter.add(lblLoaiChuyen, "pos 0.99al 27 n n, h 40!");

        lblNgayKhoiHanh = new JLabel("NGÀY KHỞI HÀNH: 27-11-2021");
        lblNgayKhoiHanh.setForeground(new Color(29, 185, 84));
        lblNgayKhoiHanh.setFont(new Font("Segoe UI", Font.BOLD, 16));
        pnlCenter.add(lblNgayKhoiHanh);

        lblGia = new JLabel("500,000 đ/người");
        lblGia.setForeground(new Color(29, 185, 84));
        lblGia.setFont(new Font("Segoe UI", Font.BOLD, 18));
        pnlCenter.add(lblGia, "right");

        lblthoiGian = new JLabel("THỜI GIAN: 1 ngày");
        lblthoiGian.setForeground(Color.WHITE);
        lblthoiGian.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pnlCenter.add(lblthoiGian);

        lblPhuongTien = new JLabel("PHƯƠNG TIỆN: Xe khách");
        lblPhuongTien.setForeground(Color.WHITE);
        lblPhuongTien.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pnlCenter.add(lblPhuongTien);

        lblNoiKhoiHanh = new JLabel("NƠI KHỞI HÀNH: Bến Tre");
        lblNoiKhoiHanh.setForeground(Color.WHITE);
        lblNoiKhoiHanh.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pnlCenter.add(lblNoiKhoiHanh);

        lblDiemDen = new JLabel("ĐIỂM ĐẾN: Củ Chi - Tây Ninh");
        lblDiemDen.setForeground(Color.WHITE);
        lblDiemDen.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pnlCenter.add(lblDiemDen);

        JLabel lblMota = new JLabel("Tour này có gì hay");
        lblMota.setForeground(new Color(29, 185, 84));
        lblMota.setFont(new Font("Segoe UI", Font.BOLD, 13));
        pnlCenter.add(lblMota, "span 2");

        lblDescription = new JLabel();
        String lblText = String.format("<html><div style=\"width:%dpx;font-size:10px;font-family:'Segoe UI', Arial, sans-serif;\">%s</div></html>", 500, "");
        lblDescription.setText(lblText);
        lblDescription.setForeground(Color.WHITE);
        lblDescription.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        pnlCenter.add(lblDescription, "span 2");

        createMap();

        add(pnlCenter);
    }

    private void createMap() {
        mapTour = new Map();
        mapTour.setTitle("Có thể bạn sẽ thích");
        pnlCenter.add(mapTour, "span 2");
    }

    private void createTitle() {
        tourName = new LabelResizingShadow();
        tourName.setText("Du lịch Củ Chi - Tây Ninh");

        tourName.setFont(new Font("sansserif", Font.BOLD, 30));
        tourName.setForeground(Color.WHITE);
        tourName.setShadowType(ShadowType.BOT);
        add(tourName, "pos 20 210 n n, w 550!, h 50!");

        lblDiaDanh = new LabelResizingShadow();
        lblDiaDanh.setText("");
        lblDiaDanh.setFont(new Font("sansserif", Font.BOLD, 30));
        lblDiaDanh.setShadowType(ShadowType.BOT);
        lblDiaDanh.setForeground(Color.WHITE);
        add(lblDiaDanh, "pos 20 240 n n, w 650!, h 60!");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 718, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 758, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
