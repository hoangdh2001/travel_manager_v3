package gui.form;

import com.huyhoang.swing.image.PictureBox;
import javax.swing.ImageIcon;
import dao.ChuyenDuLich_DAO;
import dao.impl.ChuyenDuLichImpl;
import gui.component.BoxTour;
import gui.component.Map;
import model.ChuyenDuLich;
import com.huyhoang.swing.event.EventTour;
import com.huyhoang.swing.list.Reversed;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import net.miginfocom.swing.MigLayout;

public class Home extends javax.swing.JPanel {

	private EventTour event;
	private ChuyenDuLich_DAO chuyenDuLich_DAO;

	public void addEventTour(EventTour event) {
		this.event = event;
	}

	public Home() throws RemoteException {
		this.chuyenDuLich_DAO = new ChuyenDuLichImpl();
		initComponents();
		buildDisplay();
	}

	private void buildDisplay() {
		createSlide();
		createMapTour();
	}

	private void createSlide() {
		PictureBox picture1 = new PictureBox();
		picture1.setImage(new ImageIcon(getClass().getResource("/icon/slide1.jpg")));
		PictureBox picture2 = new PictureBox();
		picture2.setImage(new ImageIcon(getClass().getResource("/icon/slide2.jpeg")));
		PictureBox picture3 = new PictureBox();
		picture3.setImage(new ImageIcon(getClass().getResource("/icon/slide3.jpg")));
		slideShowTransparent1.initSlideshow(picture1, picture2, picture3);
		slideShowTransparent1.showPagination();
		slideShowTransparent1.start();
	}

	private void createMapTour() {
		pnlSuggestions.setLayout(new MigLayout("fillx, insets 0, wrap", "[fill]"));
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					loadDataMapTourDaXemGanDay();
					loadDataMapTourMoi();
					loadDataMapTourPhoBien();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
	}

	private void loadDataMapTourDaXemGanDay() throws RemoteException {
		List<String> dsChuyenDuLich = null;
		try {
			JsonReader jReader = Json.createReader(new FileReader("data/chuyendulich.json"));
			dsChuyenDuLich = new ArrayList<>();
			JsonArray ja = jReader.readArray();
			for (JsonValue jsonValue : ja) {
				if (jsonValue instanceof JsonObject) {
					JsonObject jo = jsonValue.asJsonObject();
					dsChuyenDuLich.add(jo.getString("maChuyenDi"));
				}
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (dsChuyenDuLich != null && dsChuyenDuLich.size() > 0) {
			Map mapTourDaXem = new Map();
			mapTourDaXem.setTitle("Tour đã xem gần đâyy");
			for (String string : Reversed.reversed(dsChuyenDuLich)) {
				ChuyenDuLich chuyenDuLich = chuyenDuLich_DAO.getChuyenDuLich(string);
				BoxTour boxTour = new BoxTour();
				boxTour.setChuyenDuLich(chuyenDuLich);
				boxTour.addEventBoxTour(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						event.openTour(chuyenDuLich);
						boxTour.refresh();
					}
				});
				mapTourDaXem.addBox(boxTour, 200, 280);
			}
			pnlSuggestions.add(mapTourDaXem);
		}
	}

	private void loadDataMapTourMoi() throws RemoteException {
		List<ChuyenDuLich> dsChuyenDuLich = chuyenDuLich_DAO.getDsChuyenDuLichMoi();
		if (dsChuyenDuLich != null && dsChuyenDuLich.size() > 0) {
			Map mapTourMoi = new Map();
			mapTourMoi.setTitle("Tour mới nhất");
			for (ChuyenDuLich chuyenDuLich : dsChuyenDuLich) {
				BoxTour boxTour = new BoxTour();
				boxTour.setChuyenDuLich(chuyenDuLich);
				boxTour.addEventBoxTour(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						event.openTour(chuyenDuLich);
						boxTour.refresh();
					}
				});
				mapTourMoi.addBox(boxTour, 200, 280);
			}
			pnlSuggestions.add(mapTourMoi);
		}
	}

	private void loadDataMapTourPhoBien() throws RemoteException {
		List<ChuyenDuLich> dsChuyenDuLich = chuyenDuLich_DAO.getDsChuyenDuLichNhieuDonDatNhat();
		if (dsChuyenDuLich != null && dsChuyenDuLich.size() > 0) {
			Map mapTourMoi = new Map();
			mapTourMoi.setTitle("Phổ biến");
			for (ChuyenDuLich chuyenDuLich : dsChuyenDuLich) {
				BoxTour boxTour = new BoxTour();
				boxTour.setChuyenDuLich(chuyenDuLich);
				boxTour.addEventBoxTour(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						event.openTour(chuyenDuLich);
						boxTour.refresh();
					}
				});
				mapTourMoi.addBox(boxTour, 200, 280);
			}
			pnlSuggestions.add(mapTourMoi);
		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		pnlSuggestions = new javax.swing.JPanel();
		slideShowTransparent1 = new com.huyhoang.swing.slideshow.SlideShowTransparent();

		setBackground(new java.awt.Color(255, 255, 255));
		setBorder(javax.swing.BorderFactory.createEmptyBorder(57, 20, 0, 20));
		setOpaque(false);

		pnlSuggestions.setOpaque(false);

		javax.swing.GroupLayout pnlSuggestionsLayout = new javax.swing.GroupLayout(pnlSuggestions);
		pnlSuggestions.setLayout(pnlSuggestionsLayout);
		pnlSuggestionsLayout.setHorizontalGroup(pnlSuggestionsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 324, Short.MAX_VALUE));
		pnlSuggestionsLayout.setVerticalGroup(pnlSuggestionsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 299, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(slideShowTransparent1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
				.addComponent(pnlSuggestions, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(slideShowTransparent1, javax.swing.GroupLayout.PREFERRED_SIZE, 220,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(pnlSuggestions, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel pnlSuggestions;
	private com.huyhoang.swing.slideshow.SlideShowTransparent slideShowTransparent1;
	// End of variables declaration//GEN-END:variables
}
