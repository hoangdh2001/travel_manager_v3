package gui.form;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.huyhoang.swing.event.EventTour;

import dao.ChuyenDuLich_DAO;
import dao.DiaChi_DAO;
import dao.LoaiChuyenDi_DAO;
import gui.component.BoxTour;
import gui.component.BoxType;
import gui.component.BoxWidth;
import model.ChuyenDuLich;
import model.LoaiChuyenDi;

public class Search extends javax.swing.JPanel {

	private LoaiChuyenDi_DAO loaiChuyenDi_DAO;
	private DiaChi_DAO diaChi_DAO;
	private ChuyenDuLich_DAO chuyenDuLich_DAO;
	private EventTour event;

	public void addEventTour(EventTour event) {
		this.event = event;
	}

	public Search() throws MalformedURLException, RemoteException, NotBoundException {
		this.loaiChuyenDi_DAO = (LoaiChuyenDi_DAO) Naming.lookup("rmi://localhost:1099/loaiChuyenDi_DAO");
		this.diaChi_DAO = (DiaChi_DAO) Naming.lookup("rmi://localhost:1099/diaChi_DAO");
		this.chuyenDuLich_DAO = (ChuyenDuLich_DAO) Naming.lookup("rmi://localhost:1099/chuyendulich_dao");
		initComponents();
		boxWidth1.setVisible(true);
		buildDisplay();
	}

	private void buildDisplay() {
		loadDataForm();
		createMap();
	}

	private void loadDataForm() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				DefaultComboBoxModel<String> cmbDiemDenModel = new DefaultComboBoxModel<>();
				cmbDiemDenModel.addElement("Hãy chọn điểm đến");
				try {
					cmbDiemDenModel.addAll(diaChi_DAO.getAllTinhThanh());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cmbDiemDen.setModel(cmbDiemDenModel);
			}
		}).start();

		for (int i = 1; i <= 31; i++) {
			cmbNgay.addItem(i);
		}

		for (int i = 1; i <= 12; i++) {
			cmbThang.addItem(i);
		}

		cmbNgay.setSelectedIndex(new Date().getDay());
		cmbThang.setSelectedIndex(new Date().getMonth());
	}

	private void createMap() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				List<LoaiChuyenDi> dsChuyenDi = null;
				try {
					dsChuyenDi = loaiChuyenDi_DAO.getDsLoaiChuyenDi();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (dsChuyenDi != null) {
					for (LoaiChuyenDi loaiChuyenDi : dsChuyenDi) {
						BoxType boxType = new BoxType(loaiChuyenDi);
						boxType.addEventBoxType(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								event.openTour(loaiChuyenDi);
								System.out.println("vào");
								boxType.refreshBox();
							}
						});
						mapLoaiChuyen.addBox(boxType, 200, 200);
					}
				}
			}
		}).start();
	}

	private void createSearchMap() {
		int diemDi = cmbDiemDi.getSelectedIndex();
		String maDiaChi = "";
		switch (diemDi) {
		case 0:
			maDiaChi = "DC-0001204";
			break;
		case 1:
			maDiaChi = "DC-0000678";
			break;
		default:
			maDiaChi = "DC-0000121";
			break;
		}
		String diemDen = cmbDiemDen.getSelectedItem().toString();
		int ngay = Integer.valueOf(cmbNgay.getSelectedItem().toString());
		int thang = Integer.valueOf(cmbThang.getSelectedItem().toString());
		int nam = Integer.valueOf(cmbNam.getSelectedItem().toString());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date ngayKhoiHanh = null;
		try {
			ngayKhoiHanh = df.parse(nam + "-" + thang + "-" + ngay);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		int index = cmbSoNgay.getSelectedIndex();
		int startDay = 0;
		int endDay = 0;
		switch (index) {
		case 0:
			startDay = 1;
			endDay = 3;
			break;
		case 1:
			startDay = 4;
			endDay = 7;
			break;
		case 2:
			startDay = 8;
			endDay = 14;
			break;
		default:
			startDay = 15;
			endDay = 30;
			break;
		}
		mapLoaiChuyen.setVisible(false);
		result.setVisible(true);
		List<ChuyenDuLich> dsChuyenDuLich = null;
		try {
			dsChuyenDuLich = chuyenDuLich_DAO.searchChuyenDuLich(maDiaChi, diemDen, df.format(ngayKhoiHanh), startDay,
					endDay);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		mapSearch.removeAllBox();
		int i = 0;
		if (dsChuyenDuLich != null) {
			for (ChuyenDuLich chuyenDuLich1 : dsChuyenDuLich) {
				if (i == 0) {
					boxWidth1.setVisible(true);
					boxWidth1.addEventBoxWidth(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            event.openTour(chuyenDuLich1);
                            boxWidth1.refresh();
                        }
                    });
                    boxWidth1.setChuyenDuLich(chuyenDuLich1);
                    i++;
				} else {
					BoxTour boxTour = new BoxTour();
					boxTour.setChuyenDuLich(chuyenDuLich1);
					boxTour.addEventBoxTour(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							event.openTour(chuyenDuLich1);
							boxTour.refresh();
						}
					});
					mapSearch.addBox(boxTour, 200, 280);
				}
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		panelRoundLine1 = new com.huyhoang.swing.panel.PanelRoundLine();
		cmbDiemDi = new com.huyhoang.swing.combobox.Combobox<>();
		jLabel1 = new javax.swing.JLabel();
		panelRoundLine2 = new com.huyhoang.swing.panel.PanelRoundLine();
		cmbDiemDen = new com.huyhoang.swing.combobox.Combobox<>();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		panelRoundLine3 = new com.huyhoang.swing.panel.PanelRoundLine();
		jLabel5 = new javax.swing.JLabel();
		cmbNgay = new com.huyhoang.swing.combobox.Combobox<>();
		cmbThang = new com.huyhoang.swing.combobox.Combobox<>();
		cmbNam = new com.huyhoang.swing.combobox.Combobox<>();
		panelRoundLine4 = new com.huyhoang.swing.panel.PanelRoundLine();
		jLabel7 = new javax.swing.JLabel();
		cmbSoNgay = new com.huyhoang.swing.combobox.Combobox();
		button1 = new com.huyhoang.swing.button.Button();
		jPanel2 = new javax.swing.JPanel();
		mapLoaiChuyen = new gui.component.Map();
		result = new javax.swing.JPanel();
		boxWidth1 = new gui.component.BoxWidth();
		mapSearch = new gui.component.Map();
		jLabel4 = new javax.swing.JLabel();

		setBackground(new java.awt.Color(255, 255, 255));
		setBorder(javax.swing.BorderFactory.createEmptyBorder(57, 20, 0, 20));
		setOpaque(false);

		jPanel1.setOpaque(false);

		panelRoundLine1.setBackground(new java.awt.Color(255, 255, 255));
		panelRoundLine1.setBorderColor(new java.awt.Color(40, 40, 40));
		panelRoundLine1.setBorderRadius(20);

		cmbDiemDi.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Thành phố Hồ Chí Minh", "Thủ đô Hà Nội", "Thành phố Đà Nẵng" }));
		cmbDiemDi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		cmbDiemDi.setLabeText("Điếm đi");

		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/location.png"))); // NOI18N

		javax.swing.GroupLayout panelRoundLine1Layout = new javax.swing.GroupLayout(panelRoundLine1);
		panelRoundLine1.setLayout(panelRoundLine1Layout);
		panelRoundLine1Layout.setHorizontalGroup(panelRoundLine1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRoundLine1Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(cmbDiemDi,
								javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)));
		panelRoundLine1Layout.setVerticalGroup(panelRoundLine1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelRoundLine1Layout.createSequentialGroup()
						.addGroup(panelRoundLine1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(panelRoundLine1Layout.createSequentialGroup().addContainerGap().addComponent(
										cmbDiemDi, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(panelRoundLine1Layout.createSequentialGroup().addGap(19, 19, 19)
										.addComponent(jLabel1)))
						.addContainerGap()));

		panelRoundLine2.setBackground(new java.awt.Color(255, 255, 255));
		panelRoundLine2.setBorderColor(new java.awt.Color(40, 40, 40));
		panelRoundLine2.setBorderRadius(20);

		cmbDiemDen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hãy chọn điểm đến" }));
		cmbDiemDen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
		cmbDiemDen.setLabeText("Điếm đến");
		cmbDiemDen.setLightWeightPopupEnabled(false);

		jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/location.png"))); // NOI18N

		javax.swing.GroupLayout panelRoundLine2Layout = new javax.swing.GroupLayout(panelRoundLine2);
		panelRoundLine2.setLayout(panelRoundLine2Layout);
		panelRoundLine2Layout.setHorizontalGroup(
				panelRoundLine2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						panelRoundLine2Layout.createSequentialGroup().addContainerGap().addComponent(jLabel2)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cmbDiemDen, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
								.addContainerGap()));
		panelRoundLine2Layout
				.setVerticalGroup(panelRoundLine2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panelRoundLine2Layout.createSequentialGroup().addContainerGap()
								.addComponent(cmbDiemDen, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								panelRoundLine2Layout.createSequentialGroup()
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel2).addGap(18, 18, 18)));

		jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/direction.png"))); // NOI18N

		panelRoundLine3.setBackground(new java.awt.Color(255, 255, 255));
		panelRoundLine3.setBorderColor(new java.awt.Color(40, 40, 40));
		panelRoundLine3.setBorderRadius(20);
		panelRoundLine3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

		jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/calendar.png"))); // NOI18N

		cmbNgay.setLabeText("Ngày");

		cmbThang.setLabeText("Tháng");

		cmbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022" }));
		cmbNam.setLabeText("Năm");

		javax.swing.GroupLayout panelRoundLine3Layout = new javax.swing.GroupLayout(panelRoundLine3);
		panelRoundLine3.setLayout(panelRoundLine3Layout);
		panelRoundLine3Layout
				.setHorizontalGroup(panelRoundLine3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panelRoundLine3Layout.createSequentialGroup().addContainerGap().addComponent(jLabel5)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cmbNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 114,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cmbThang, javax.swing.GroupLayout.PREFERRED_SIZE, 134,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cmbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelRoundLine3Layout.setVerticalGroup(panelRoundLine3Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelRoundLine3Layout.createSequentialGroup().addGap(19, 19, 19).addComponent(jLabel5)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRoundLine3Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(panelRoundLine3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cmbNgay, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbThang, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbNam, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		panelRoundLine4.setBackground(new java.awt.Color(255, 255, 255));
		panelRoundLine4.setBorderColor(new java.awt.Color(40, 40, 40));
		panelRoundLine4.setBorderRadius(20);

		jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/calendar.png"))); // NOI18N

		cmbSoNgay.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "1-3 ngày", "4-7 ngày", "8-14 ngày", "Trên 14 ngày" }));
		cmbSoNgay.setLabeText("Số ngày");

		javax.swing.GroupLayout panelRoundLine4Layout = new javax.swing.GroupLayout(panelRoundLine4);
		panelRoundLine4.setLayout(panelRoundLine4Layout);
		panelRoundLine4Layout
				.setHorizontalGroup(panelRoundLine4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(panelRoundLine4Layout.createSequentialGroup().addContainerGap().addComponent(jLabel7)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(cmbSoNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 89, Short.MAX_VALUE)
								.addContainerGap()));
		panelRoundLine4Layout.setVerticalGroup(panelRoundLine4Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelRoundLine4Layout.createSequentialGroup()
						.addGroup(panelRoundLine4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(panelRoundLine4Layout.createSequentialGroup().addGap(19, 19, 19)
										.addComponent(jLabel7))
								.addGroup(panelRoundLine4Layout.createSequentialGroup().addContainerGap().addComponent(
										cmbSoNgay, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		button1.setBackground(new java.awt.Color(40, 40, 40));
		button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_enter.png"))); // NOI18N
		button1.setBorderRadius(20);
		button1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(panelRoundLine3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelRoundLine4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(panelRoundLine1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(jLabel3)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(panelRoundLine2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(panelRoundLine1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelRoundLine2, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(18, 18, 18).addComponent(jLabel3)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(panelRoundLine3, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(panelRoundLine4, javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));

		jPanel2.setOpaque(false);
		jPanel2.setLayout(new java.awt.CardLayout());

		mapLoaiChuyen.setTitle("Duyệt tìm tất cả");
		jPanel2.add(mapLoaiChuyen, "card2");

		result.setOpaque(false);

		mapSearch.setTitle("");

		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
		jLabel4.setForeground(new java.awt.Color(255, 255, 255));
		jLabel4.setText("Kết quả hàng đầu");

		javax.swing.GroupLayout resultLayout = new javax.swing.GroupLayout(result);
		result.setLayout(resultLayout);
		resultLayout.setHorizontalGroup(resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(mapSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(resultLayout.createSequentialGroup()
						.addGroup(resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(boxWidth1, javax.swing.GroupLayout.PREFERRED_SIZE, 489,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(resultLayout.createSequentialGroup().addContainerGap().addComponent(jLabel4)))
						.addContainerGap(159, Short.MAX_VALUE)));
		resultLayout.setVerticalGroup(resultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(resultLayout.createSequentialGroup().addComponent(jLabel4).addGap(4, 4, 4)
						.addComponent(boxWidth1, javax.swing.GroupLayout.PREFERRED_SIZE, 247,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(mapSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)));

		jPanel2.add(result, "card3");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	}// </editor-fold>

	private void button1ActionPerformed(java.awt.event.ActionEvent evt) {
		createSearchMap();
	}

	// Variables declaration - do not modify
	private gui.component.BoxWidth boxWidth1;
	private com.huyhoang.swing.button.Button button1;
	private com.huyhoang.swing.combobox.Combobox<String> cmbDiemDen;
	private com.huyhoang.swing.combobox.Combobox<String> cmbDiemDi;
	private com.huyhoang.swing.combobox.Combobox<String> cmbNam;
	private com.huyhoang.swing.combobox.Combobox<Integer> cmbNgay;
	private com.huyhoang.swing.combobox.Combobox cmbSoNgay;
	private com.huyhoang.swing.combobox.Combobox<Integer> cmbThang;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private gui.component.Map mapLoaiChuyen;
	private gui.component.Map mapSearch;
	private com.huyhoang.swing.panel.PanelRoundLine panelRoundLine1;
	private com.huyhoang.swing.panel.PanelRoundLine panelRoundLine2;
	private com.huyhoang.swing.panel.PanelRoundLine panelRoundLine3;
	private com.huyhoang.swing.panel.PanelRoundLine panelRoundLine4;
	private javax.swing.JPanel result;
	// End of variables declaration
}
