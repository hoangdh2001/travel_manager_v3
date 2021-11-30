package gui.component;

import model.ChiTietThamQuan;
import model.ChuyenDuLich;
import com.huyhoang.swing.button.ButtonTransparent;
import com.huyhoang.swing.image.PictureBox;
import com.huyhoang.swing.label.WrapLabel;
import com.huyhoang.swing.slideshow.SlideShowTransparent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class BoxTour extends com.huyhoang.swing.panel.PanelTransparent {

    private boolean over;
    private MigLayout layout;
    private Animator animator;
    private SlideShowTransparent slideShowTransparent1;
    private ButtonTransparent buttonTransparent;
    private ChuyenDuLich chuyenDuLich;
    private WrapLabel lblTenTour;
    private JLabel lblNguoi;
    private JLabel lblGiaTour;
    private final DecimalFormat df = new DecimalFormat("#,##0đ‘");

    public void addEventBoxTour(MouseListener event) {
        bg.addMouseListener(event);
    }

    public ChuyenDuLich getChuyenDuLich() {
        return chuyenDuLich;
    }

    public void setChuyenDuLich(ChuyenDuLich chuyenDuLich) {
        this.chuyenDuLich = chuyenDuLich;
        loadData();
    }
    
    public BoxTour() {
        initComponents();
        buildDisplay();
    }
    
    public BoxTour(ChuyenDuLich chuyenDuLich) {
        this.chuyenDuLich = chuyenDuLich;
        initComponents();
        buildDisplay();
    }

    private void buildDisplay() {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        overTransparent();
        createBg();
    }
    
    private void loadData() {
        List<ChiTietThamQuan> dsChiTietThamQuan = chuyenDuLich.getDsChiTietThamQuan();
        slideShowTransparent1.removeAllImage();
        String tinh = "Du lịch ";
        if(dsChiTietThamQuan != null && dsChiTietThamQuan.size() > 0) {
            for (int i = 0; i < dsChiTietThamQuan.size(); i++) {
                PictureBox pictureBox = new PictureBox(new ImageIcon(dsChiTietThamQuan.get(i).getAnhDiaDanh()));
                pictureBox.setBorderRadius(15);
                slideShowTransparent1.addImage(pictureBox);
                if(i == (dsChiTietThamQuan.size() - 1)) {
                    tinh = tinh + dsChiTietThamQuan.get(i).getDiaDanh().getTenDiaDanh();
                } else {
                    tinh = tinh + dsChiTietThamQuan.get(i).getDiaDanh().getTenDiaDanh() + " - ";
                }
            }
//            slideShowTransparent1.select(0);
        }
        lblTenTour.setText(tinh);
        lblNguoi.setText(chuyenDuLich.getSoLuong() + "");
        lblGiaTour.setText(df.format(chuyenDuLich.getGiaChuyenDi()) + "/khách");
        
    }

    private void createBg() {
        layout = new MigLayout("fillx, insets 0, wrap", "[fill]push[fill]");
        bg.setLayout(layout);
        createButton();
        createSlide();

        lblTenTour = new WrapLabel("Tên tour");
        lblTenTour.setForeground(Color.WHITE);
        lblTenTour.setVAlignStyle(0);
        lblTenTour.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bg.add(lblTenTour, "span 2, h 40!, w 170!");

        lblNguoi = new JLabel("10");
        lblNguoi.setIcon(new ImageIcon(getClass().getResource("/icon/person.png")));
        lblNguoi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblNguoi.setForeground(Color.WHITE);
        bg.add(lblNguoi, "span 2");

        JPanel separator = new JPanel();
        separator.setBackground(Color.DARK_GRAY);
        bg.add(separator, "h 1!, span 2");

        JLabel lblGia = new JLabel("Giá");
        lblGia.setForeground(Color.WHITE);
        lblGia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        bg.add(lblGia);

        lblGiaTour = new JLabel("$100,000/người");
        lblGiaTour.setForeground(Color.WHITE);
        lblGiaTour.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bg.add(lblGiaTour);
    }

    private void overTransparent() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (over) {
                    slideShowTransparent1.start();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                double point;
                if (over) {
                    bg.setAlpha(0.1f * fraction);
                    buttonTransparent.setAlpha(fraction);
                    point = 130 + (10 * (1f - fraction));
                } else {
                    bg.setAlpha(0.1f * (1f - fraction));
                    buttonTransparent.setAlpha(1f - fraction);
                    point = 130 + (10 * fraction);
                }
                layout.setComponentConstraints(buttonTransparent, "pos 0.85al " + point + " n n, w 40!, h 40!");
                bg.revalidate();
            }
        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }

    private void createSlide() {
        slideShowTransparent1 = new SlideShowTransparent();
        PictureBox picture1 = new PictureBox();
        picture1.setImage(new ImageIcon(getClass().getResource("/icon/slide1.jpg")));
        picture1.setBorderRadius(15);
        PictureBox picture2 = new PictureBox();
        picture2.setImage(new ImageIcon(getClass().getResource("/icon/slide2.jpeg")));
        picture2.setBorderRadius(15);
        PictureBox picture3 = new PictureBox();
        picture3.setImage(new ImageIcon(getClass().getResource("/icon/slide3.jpg")));
        picture3.setBorderRadius(15);
        slideShowTransparent1.initSlideshow(picture1, picture2, picture3);
        slideShowTransparent1.setDuration(1800);
        slideShowTransparent1.setBorderRadius(15);
        bg.add(slideShowTransparent1, "h 160!, span 2");
    }
    
    private void createButton() {
        buttonTransparent = new ButtonTransparent();
        buttonTransparent.setIcon(new ImageIcon(getClass().getResource("/icon/booking_small.png")));
        buttonTransparent.setBackground(new Color(29, 185, 84));
        buttonTransparent.setAlpha(0);
        bg.add(buttonTransparent, "pos 0.85al 130 n n, w 40!, h 40!");
    }

    public void refresh() {
        over = false;
        if (animator.isRunning()) {
            animator.stop();
        }
        animator.start();
        slideShowTransparent1.select(0);
        slideShowTransparent1.stop();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new com.huyhoang.swing.panel.PanelTransparent();

        setBackground(new java.awt.Color(30, 30, 30));

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bg.setAlpha(0.0F);
        bg.setBorderRadius(10);
        bg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bgMouseExited(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseExited
        refresh();
    }//GEN-LAST:event_bgMouseExited

    private void bgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseEntered
        over = true;
        if (animator.isRunning()) {
            animator.stop();
        }
        animator.start();
    }//GEN-LAST:event_bgMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.huyhoang.swing.panel.PanelTransparent bg;
    // End of variables declaration//GEN-END:variables
}
