package gui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import com.huyhoang.swing.button.ButtonTransparent;
import com.huyhoang.swing.image.PictureBox;
import com.huyhoang.swing.label.LabelResizingShadow;
import com.huyhoang.swing.label.WrapLabel;
import com.huyhoang.swing.slideshow.SlideShowTransparent;

import model.ChiTietThamQuan;
import model.ChuyenDuLich;
import net.miginfocom.swing.MigLayout;

public class BoxWidth extends com.huyhoang.swing.panel.PanelTransparent {

	private boolean over;
	private SlideShowTransparent slideShowTransparent;
	private WrapLabel tourName;
	private LabelResizingShadow lblTinh;
	private ButtonTransparent buttonTransparent;
	private Animator animator;
	private MigLayout layout;
	private ChuyenDuLich chuyenDuLich;

	public void addEventBoxWidth(MouseListener mouseListener) {
		bg.addMouseListener(mouseListener);
	}

	public void setChuyenDuLich(ChuyenDuLich chuyenDuLich) {
		this.chuyenDuLich = chuyenDuLich;
		loadData();
		bg.repaint();
		bg.revalidate();
	}

	private void loadData() {
		List<ChiTietThamQuan> dsChiTietThamQuan = chuyenDuLich.getDsChiTietThamQuan();
        slideShowTransparent.removeAllImage();
        String diaDanh = "Du lịch ";
        String tinh = "";
        if(dsChiTietThamQuan != null && dsChiTietThamQuan.size() > 0) {
            for (int i = 0; i < dsChiTietThamQuan.size(); i++) {
                PictureBox pictureBox = new PictureBox(new ImageIcon(dsChiTietThamQuan.get(i).getAnhDiaDanh()));
                pictureBox.setBorderRadius(15);
                slideShowTransparent.addImage(pictureBox);
                if(i == (dsChiTietThamQuan.size() - 1)) {
                    diaDanh = diaDanh + dsChiTietThamQuan.get(i).getDiaDanh().getTenDiaDanh();
                    tinh = tinh + dsChiTietThamQuan.get(i).getDiaDanh().getTinh();
                } else {
                    diaDanh = diaDanh + dsChiTietThamQuan.get(i).getDiaDanh().getTenDiaDanh() + " - ";
                    tinh = tinh + dsChiTietThamQuan.get(i).getDiaDanh().getTinh() + " - ";
                }
            }
        }
        tourName.setText(diaDanh);
        lblTinh.setText(tinh);
	}

	public BoxWidth() {
		initComponents();
		buildDisplay();
	}

	private void buildDisplay() {
		createBg();
		overTransparent();
	}

	private void createBg() {
		bg.setLayout(layout = new MigLayout("fillx, insets 0, wrap", "[fill]"));
		createSlideShow();

		tourName = new WrapLabel();
		tourName.setText("Du lịch");
		tourName.setForeground(Color.WHITE);
		tourName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		tourName.setVAlignStyle(0);
		bg.add(tourName);

		lblTinh = new LabelResizingShadow();
		lblTinh.setText("Địa danh");
		lblTinh.setForeground(Color.WHITE);
		lblTinh.setFont(new Font("Segoe UI", Font.BOLD, 14));
		bg.add(lblTinh);

		createButton();
	}

	private void createButton() {
		buttonTransparent = new ButtonTransparent();
		buttonTransparent.setIcon(new ImageIcon(getClass().getResource("/icon/booking_small.png")));
		buttonTransparent.setBackground(new Color(29, 185, 84));
		buttonTransparent.setAlpha(0);
		bg.add(buttonTransparent, "pos 0.9al 0.9al n n, w 50!, h 50!");
	}

	private void createSlideShow() {
		slideShowTransparent = new SlideShowTransparent();
		PictureBox picture1 = new PictureBox();
		picture1.setImage(new ImageIcon(getClass().getResource("/icon/slide1.jpg")));
		picture1.setBorderRadius(10);
		PictureBox picture2 = new PictureBox();
		picture2.setImage(new ImageIcon(getClass().getResource("/icon/slide2.jpeg")));
		picture2.setBorderRadius(10);
		PictureBox picture3 = new PictureBox();
		picture3.setImage(new ImageIcon(getClass().getResource("/icon/slide3.jpg")));
		picture3.setBorderRadius(10);
		slideShowTransparent.initSlideshow(picture1, picture2, picture3);
		slideShowTransparent.setDuration(1800);
		slideShowTransparent.setBorderRadius(10);
		bg.add(slideShowTransparent, "h 160!, w 200!");
	}

	private void overTransparent() {
		TimingTarget target = new TimingTargetAdapter() {
			@Override
			public void begin() {
				if (over) {
					slideShowTransparent.start();
				}
			}

			@Override
			public void timingEvent(float fraction) {
				double point;
				if (over) {
					bg.setAlpha(0.2f * fraction);
					buttonTransparent.setAlpha(fraction);
					point = 0.8 + (0.1 * (1f - fraction));
				} else {
					bg.setAlpha(0.1f * (1f - fraction));
					buttonTransparent.setAlpha(1f - fraction);
					point = 0.8 + (0.1 * fraction);
				}
				layout.setComponentConstraints(buttonTransparent, "pos 0.9al " + point + "al n n, w 50!, h 50!");
				bg.revalidate();
			}
		};
		animator = new Animator(400, target);
		animator.setResolution(0);
		animator.setAcceleration(0.5f);
		animator.setDeceleration(0.5f);
	}

	public void refresh() {
		over = false;
		if (animator.isRunning()) {
			animator.stop();
		}
		animator.start();
		slideShowTransparent.select(0);
		slideShowTransparent.stop();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		bg = new com.huyhoang.swing.panel.PanelTransparent();

		setBackground(new java.awt.Color(30, 30, 30));
		setBorderRadius(10);

		bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
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
		bgLayout.setHorizontalGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
				485, Short.MAX_VALUE));
		bgLayout.setVerticalGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 250,
				Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(bg,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(bg,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	private void bgMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_bgMouseEntered
		over = true;
		if (animator.isRunning()) {
			animator.stop();
		}
		animator.start();
	}// GEN-LAST:event_bgMouseEntered

	private void bgMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_bgMouseExited
		refresh();
	}// GEN-LAST:event_bgMouseExited

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private com.huyhoang.swing.panel.PanelTransparent bg;
	// End of variables declaration//GEN-END:variables
}
