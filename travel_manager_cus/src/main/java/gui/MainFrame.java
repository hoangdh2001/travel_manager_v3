package gui;

import dao.KhachHang_DAO;
import dao.impl.KhachHangImpl;
import gui.dialog.DialogBookTour;
import gui.form.Home;
import gui.form.Library;
import gui.form.ResultMap;
import gui.form.Search;
import gui.form.TourInfo;
import model.ChuyenDuLich;
import model.KhachHang;
import model.LoaiChuyenDi;

import com.huyhoang.swing.event.EventMenuSelected;
import com.huyhoang.swing.event.EventTour;
import com.huyhoang.swing.panel.ComponentResizer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.Json;
import javax.json.JsonValue;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MainFrame extends javax.swing.JFrame {

    private int xx;
    private int yy;
    private boolean show;
    private Animator start;
    private TourInfo tourInfo;
    private final List<Component> historyComponent = new ArrayList<>();
    private int currentIndex = -1;
    public static KhachHang khachHang;
    private KhachHang_DAO khachHang_DAO;

    public MainFrame(KhachHang khachHang) throws RemoteException, MalformedURLException, NotBoundException {
        MainFrame.khachHang = khachHang;
        this.khachHang_DAO = (KhachHang_DAO)  Naming.lookup("rmi://localhost:1099/khachHang_DAO");
        initComponents();
        btrang.setVisible(false);
        jPanel1.setVisible(true);
        buildDisplay();
        resized();
    }

    private void buildDisplay() throws RemoteException, MalformedURLException, NotBoundException {
        start();
        createForm();
        createMenu();
        createHeader();
        createContent();
        createChat();
    }

    private void createForm() throws RemoteException, MalformedURLException, NotBoundException {
        createFormTourInfo();
    }

    private void createMenu() {
        menu.initMenu((int index) -> {
            if (index == 0) {
                Home home = null;
				try {
					home = new Home();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
                home.addEventTour(new EventTour() {
                    @Override
                    public void openTour(Object obj) {
                        ChuyenDuLich chuyenDuLich = (ChuyenDuLich) obj;
                        tourInfo.setChuyenDuLich(chuyenDuLich);
                        main.getContent().showForm(tourInfo);
                        menu.unSelectedAll();
                        addHistory(tourInfo);
                        try {
                            write2File(chuyenDuLich);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                main.getContent().showForm(home);
                addHistory(home);
            } else if (index == 1) {
                Search search = null;
				try {
					search = new Search();
					search.addEventTour(new EventTour() {
						@Override
						public void openTour(Object arg0) {
							if(arg0 instanceof LoaiChuyenDi) {
								LoaiChuyenDi loaiChuyenDi = (LoaiChuyenDi) arg0;
								ResultMap resultMap = null;
								try {
									resultMap = new ResultMap(loaiChuyenDi);
									resultMap.addEventTour(new EventTour() {
										@Override
										public void openTour(Object arg0) {
											ChuyenDuLich chuyenDuLich = (ChuyenDuLich) arg0;
											tourInfo.setChuyenDuLich(chuyenDuLich);
											main.getContent().showForm(tourInfo);
											menu.unSelectedAll();
											addHistory(tourInfo);
											try {
			                                    write2File(chuyenDuLich);
			                                } catch (FileNotFoundException ex) {
			                                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			                                }
										}
									});
									
								} catch (MalformedURLException | RemoteException | NotBoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								main.getContent().showForm(resultMap);
								menu.unSelectedAll();
								addHistory(resultMap);
							} else if(arg0 instanceof ChuyenDuLich) {
								ChuyenDuLich chuyenDuLich = (ChuyenDuLich) arg0;
	                            tourInfo.setChuyenDuLich(chuyenDuLich);
	                            main.getContent().showForm(tourInfo);
	                            menu.unSelectedAll();
	                            addHistory(tourInfo);
	                            try {
	                                write2File(chuyenDuLich);
	                            } catch (FileNotFoundException ex) {
	                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
	                            }
							}
						}
					});
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
                main.getContent().showForm(search);
                addHistory(search);
            } else if(index == 2) {
                Library library = null;
				try {
					library = new Library();
				} catch (RemoteException | MalformedURLException | NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                library.addEventTour(new EventTour() {
                    @Override
                    public void openTour(Object obj) {
                        ChuyenDuLich chuyenDuLich = (ChuyenDuLich) obj;
                        tourInfo.setChuyenDuLich(chuyenDuLich);
                        main.getContent().showForm(tourInfo);
                        menu.unSelectedAll();
                        addHistory(tourInfo);
                        try {
                            write2File(chuyenDuLich);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                main.getContent().showForm(library);
                addHistory(library);
            }
        });
        move(menu.getjPanel1(), 0);
    }

    private void createHeader() {
        main.getHeader().addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void menuSelected(int index) {
                switch (index) {
                    case 0:
                        System.out.println("Open hồ sơ");
                        break;
                    case 1:
                        System.out.println("Open cài đặt");
                        break;
                    case 2:
                        close();
                        Application.getLogin().setVisible(true);
                        break;
                    default:
                        break;
                }
            }
        });
        main.getHeader().addEventBack(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(currentIndex > 0) {
                    currentIndex--;
                    Component com = historyComponent.get(currentIndex);
                    if(com instanceof Home) {
                        menu.setSelectedIndex(0);
                    } else if(com instanceof Search) {
                        menu.setSelectedIndex(1);
                    } else {
                        menu.unSelectedAll();
                    }
                    main.getContent().showForm(com);
                    main.getHeader().getBtnNext().setEnabled(true);
                    if(currentIndex == 0) {
                        main.getHeader().getBtnBack().setEnabled(false);
                    }
                }
            }
        });
        main.getHeader().addEventNext(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(currentIndex < historyComponent.size()) {
                    currentIndex++;
                    Component com = historyComponent.get(currentIndex);
                    if(com instanceof Home) {
                        menu.setSelectedIndex(0);
                    } else if(com instanceof Search) {
                        menu.setSelectedIndex(1);
                    } else {
                        menu.unSelectedAll();
                    }
                    main.getContent().showForm(com);
                    main.getHeader().getBtnBack().setEnabled(true);
                    if(currentIndex == (historyComponent.size() - 1)) {
                        main.getHeader().getBtnNext().setEnabled(false);
                    }
                }
            }
        });
        move(main.getHeader(), menu.getWidth());
    }

    private void createContent() throws RemoteException {
        main.getScrollPaneCustom1().getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent arg0) {
                if (arg0.getValue() > 10) {
                    if(main.getHeader().isTransparent()) {
                        main.getHeader().hidden();
                    }
                } else {
                    if(!main.getHeader().isTransparent()) {
                        main.getHeader().display();
                    }
                }
            }
        });
        Home home = new Home();
        home.addEventTour(new EventTour() {
            @Override
            public void openTour(Object obj) {
                ChuyenDuLich chuyenDuLich = (ChuyenDuLich) obj;
                tourInfo.setChuyenDuLich(chuyenDuLich);
                main.getContent().showForm(tourInfo);
               menu.unSelectedAll();
                addHistory(tourInfo);
                try {
                    write2File(chuyenDuLich);
                } catch (FileNotFoundException ex) {
                            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
        main.getContent().add(home);
        historyComponent.add(home);
        currentIndex++;
    }

    private void createChat() {
        chat.addActionMinimize((ActionEvent arg0) -> {
            setState(JFrame.ICONIFIED);
        });
        chat.addActionMaximize((ActionEvent arg0) -> {
            if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                bg.setBackground(new Color(18, 18, 18));
                bg.setBorder(new EmptyBorder(10, 10, 10, 10));
                setExtendedState(JFrame.NORMAL);
            } else {
                bg.setBackground(new Color(0, 0, 0, 0));
                bg.setBorder(new EmptyBorder(0, 0, 40, 0));
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
        chat.addActionClose((ActionEvent arg0) -> {
            System.exit(0);
        });
        move(chat.getPnlTop(), menu.getWidth() + main.getHeader().getWidth());
    }

    private void createFormTourInfo() throws RemoteException, MalformedURLException, NotBoundException {
        tourInfo = new TourInfo();
        tourInfo.addEventLike(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                int state = arg0.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    khachHang.themChuyenDiDaThich(tourInfo.getChuyenDuLich());
                    try {
						khachHang_DAO.updateKhachHang(khachHang);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    main.showMessage("Đã lưu vào thư viện");
                } else {
                    main.showMessage("Đã xóa khỏi thư viện");
                    khachHang.getChuyenDiDaThich().remove(tourInfo.getChuyenDuLich());
                    try {
						khachHang_DAO.updateKhachHang(khachHang);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        });
        tourInfo.addEventTour(new EventTour() {
            @Override
            public void openTour(Object obj) {
                ChuyenDuLich chuyenDuLich = (ChuyenDuLich) obj;
                tourInfo.setChuyenDuLich(chuyenDuLich);
                main.getContent().showForm(tourInfo);
                menu.unSelectedAll();
                addHistory(tourInfo);
                try {
                    write2File(chuyenDuLich);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tourInfo.addEventBookTour(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogBookTour bookTour = null;
				try {
					bookTour = new DialogBookTour(MainFrame.this, tourInfo.getChuyenDuLich());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                btrang.setVisible(true);
                bookTour.setVisible(true);
                if(bookTour.isThem()) {
                    main.showMessage("Đặt chuyến du lịch thành công");
                } else {
                    main.showMessage("Đặt thất bại");
                }
                btrang.setVisible(false);
            }
        });
    }
    
    private void addHistory(Component com) {
        if(currentIndex < (historyComponent.size() - 1)) {
            for (int i = (currentIndex + 1); i < historyComponent.size(); i++) {
                historyComponent.remove(i);
            }
            main.getHeader().getBtnNext().setEnabled(false);
        }
        historyComponent.add(com);
        currentIndex++;
        main.getHeader().getBtnBack().setEnabled(true);
        main.getScrollPaneCustom1().getVerticalScrollBar().setValue(0);
    }

    private void start() {
        setBackground(new Color(0, 0, 0, 0));
        setOpacity(0);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    setOpacity(fraction);
                } else {
                    setOpacity(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (!show) {
                    dispose();
                }
            }
        };
        start = new Animator(200, target);
        start.setResolution(0);
        start.setAcceleration(0.5f);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        show = b;
        if (show) {
            start.start();
        }
    }

    private void close() {
        if (start.isRunning()) {
            start.stop();
        }
        show = false;
        start.start();
    }

    private void move(Component com, int o) {
        System.out.println(com.getX());
        com.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                xx = e.getX();
                yy = e.getY();
            }
        });
        com.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                if (getExtendedState() == MAXIMIZED_BOTH) {
                    bg.setBackground(new Color(18, 18, 18));
                    bg.setBorder(new EmptyBorder(10, 10, 10, 10));
                    setExtendedState(JFrame.NORMAL);
                    setLocation(x - xx - o, y - yy);
                }
                setLocation(x - xx - o, y - yy);
            }
        });
    }

    private static void write2File(ChuyenDuLich chuyenDuLich) throws FileNotFoundException {
        JsonReader jsonReader = Json.createReader(new FileReader("data/ChuyenDuLich.json"));
        JsonArray jsonArray = jsonReader.readArray();
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder(jsonArray);
        if (jsonArray.size() > 2) {
            jsonArrayBuilder.remove(0);
        }
        for (JsonValue jsonValue : jsonArray) {
            if(jsonValue instanceof JsonObject) {
                JsonObject jo = jsonValue.asJsonObject();
                if(chuyenDuLich.getMaChuyen().equals(jo.getString("maChuyenDi"))) {
                    return;
                }
            }
        }
        JsonObject jsonObject = job.add("maChuyenDi", chuyenDuLich.getMaChuyen()).build();
        
        jsonArray = jsonArrayBuilder.add(jsonObject).build();
        try (PrintWriter out = new PrintWriter(new FileWriter("data/ChuyenDuLich.json"))) {
            out.println(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void resized() {
        ComponentResizer com = new ComponentResizer();
        com.registerComponent(this);
        com.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        com.setSnapSize(new Dimension(10, 10));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new com.huyhoang.swing.panel.LayerPaneShadow();
        btrang = new com.huyhoang.swing.panel.PanelTransparent();
        jPanel1 = new javax.swing.JPanel();
        bottom = new gui.component.Bottom();
        menu = new gui.component.Menu();
        chat = new gui.component.Chat();
        main = new gui.component.Main();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bg.setShadowOpacity(0.3F);
        bg.setShadowSize(10);
        bg.setLayout(new java.awt.CardLayout());

        btrang.setBackground(new java.awt.Color(0, 0, 0));
        btrang.setAlpha(0.5F);
        bg.setLayer(btrang, javax.swing.JLayeredPane.POPUP_LAYER);
        bg.add(btrang, "card2");

        jPanel1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout bottomLayout = new javax.swing.GroupLayout(bottom);
        bottom.setLayout(bottomLayout);
        bottomLayout.setHorizontalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1216, Short.MAX_VALUE)
        );
        bottomLayout.setVerticalGroup(
            bottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel1.add(bottom, java.awt.BorderLayout.PAGE_END);
        jPanel1.add(menu, java.awt.BorderLayout.LINE_START);
        jPanel1.add(chat, java.awt.BorderLayout.LINE_END);
        jPanel1.add(main, java.awt.BorderLayout.CENTER);

        bg.add(jPanel1, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.huyhoang.swing.panel.LayerPaneShadow bg;
    private gui.component.Bottom bottom;
    private com.huyhoang.swing.panel.PanelTransparent btrang;
    private gui.component.Chat chat;
    private javax.swing.JPanel jPanel1;
    private gui.component.Main main;
    private gui.component.Menu menu;
    // End of variables declaration//GEN-END:variables
}
