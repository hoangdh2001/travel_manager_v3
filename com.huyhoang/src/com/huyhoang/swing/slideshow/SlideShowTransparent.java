package com.huyhoang.swing.slideshow;

import com.huyhoang.swing.image.PictureBox;
import com.huyhoang.swing.panel.PanelTransparent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLayeredPane;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class SlideShowTransparent extends JLayeredPane {

    private final PanelTransparent panel;
    private final Pagination pagination;
    private final Animator animator;
    private final Timer timer;
    private PictureBox pictureShow;
    private PictureBox pictureOut;
    private int currentIndex;
    private boolean next;

    public SlideShowTransparent() {
        panel = new PanelTransparent();
        panel.setBackground(new Color(30, 30, 30));
        pagination = new Pagination();
        pagination.setVisible(false);
        pagination.setEventPagination(new EventPagination() {
            @Override
            public void onClick(int pageClick) {
                if (!animator.isRunning()) {
                    if (pageClick != currentIndex) {
                        timer.restart();
                        next = currentIndex < pageClick;
                        if (next) {
                            pictureOut = (PictureBox) panel.getComponent(checkNext(currentIndex));
                            currentIndex = getNext(pageClick - 1);
                            pictureShow = (PictureBox) panel.getComponent(currentIndex);
                            animator.start();
                        } else {
                            pictureOut = (PictureBox) panel.getComponent(checkBack(currentIndex));
                            currentIndex = getBack(pageClick + 1);
                            pictureShow = (PictureBox) panel.getComponent(currentIndex);
                            animator.start();
                        }
                    }
                }
            }
        });

        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                pictureShow.setVisible(true);
                pictureOut.setVisible(true);
                pagination.setIndex(currentIndex);
            }

            @Override
            public void timingEvent(float fraction) {
                pictureShow.setAlpha(fraction);
                pictureOut.setAlpha(1f - fraction);
                pagination.setAnimation(fraction);
                panel.revalidate();
            }

            @Override
            public void end() {
                pictureOut.setVisible(false);
            }
        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        setLayer(pagination, POPUP_LAYER);
        panel.setLayout(new CardLayout());
        setLayout(new MigLayout("fill, insets 0", "[fill]", "[fill]"));

        add(pagination, "pos 0.5al 1al n n");
        add(panel);

        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(panel.getComponentCount() != 0) {
                    next();
                }
            }
        });
    }

    public void select(int pageClick) {
        if (animator.isRunning()) {
            animator.stop();
        }
        if (pageClick != currentIndex) {
            timer.restart();
            next = currentIndex < pageClick;
            if (next) {
                pictureOut = (PictureBox) panel.getComponent(checkNext(currentIndex));
                currentIndex = getNext(pageClick - 1);
                pictureShow = (PictureBox) panel.getComponent(currentIndex);
                animator.start();
            } else {
                pictureOut = (PictureBox) panel.getComponent(checkBack(currentIndex));
                currentIndex = getBack(pageClick + 1);
                pictureShow = (PictureBox) panel.getComponent(currentIndex);
                animator.start();
            }
        }
    }

    public void setBorderRadius(int borderRadius) {
        panel.setBorderRadius(borderRadius);
    }

    public void setDuration(int duration) {
        timer.setInitialDelay(duration);
    }

    public void showPagination() {
        pagination.setVisible(true);
    }

    public void initSlideshow(PictureBox... pictures) {
        timer.stop();
        panel.removeAll();
        if (pictures.length >= 2) {
            for (PictureBox picture : pictures) {
                picture.setVisible(false);
                picture.setAlpha(0);
                panel.add(picture);
            }
            if (panel.getComponentCount() > 0) {
                pictureShow = (PictureBox) panel.getComponent(0);
                pictureShow.setAlpha(1f);
                pictureShow.setVisible(true);
            }
            pagination.setTotalPage(panel.getComponentCount());
            pagination.setCurrentIndex(0);
        }
    }

    public void addImage(PictureBox pictureBox) {
        pictureBox.setVisible(false);
        pictureBox.setAlpha(0);
        panel.add(pictureBox);
        if (panel.getComponentCount() > 0) {
            pictureShow = (PictureBox) panel.getComponent(0);
            pictureShow.setAlpha(1f);
            pictureShow.setVisible(true);
        }
        pagination.setTotalPage(panel.getComponentCount());
        pagination.setCurrentIndex(0);
    }
    
    public void removeAllImage() {
        panel.removeAll();
        pagination.setTotalPage(panel.getComponentCount());
        panel.repaint();
        panel.revalidate();
    }
    
    public void removeImage(int index) {
        panel.remove(index);
        pagination.setTotalPage(panel.getComponentCount());
        panel.repaint();
        panel.revalidate();
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void restart() {
        timer.restart();
        next();
    }

    public void next() {
        if (!animator.isRunning()) {
            timer.restart();
            next = true;
            currentIndex = getNext(currentIndex);
            pictureShow = (PictureBox) panel.getComponent(currentIndex);
            pictureOut = (PictureBox) panel.getComponent(checkNext(currentIndex - 1));
            animator.start();
        }

    }

    public void back() {
        if (!animator.isRunning()) {
            timer.restart();
            next = false;
            currentIndex = getBack(currentIndex);
            pictureShow = (PictureBox) panel.getComponent(currentIndex);
            pictureOut = (PictureBox) panel.getComponent(checkBack(currentIndex + 1));
            animator.start();
        }
    }

    private int getNext(int index) {
        if (index == panel.getComponentCount() - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private int checkNext(int index) {
        if (index == -1) {
            return panel.getComponentCount() - 1;
        } else {
            return index;
        }
    }

    private int getBack(int index) {
        if (index == 0) {
            return panel.getComponentCount() - 1;
        } else {
            return index - 1;
        }
    }

    private int checkBack(int index) {
        if (index == panel.getComponentCount()) {
            return 0;
        } else {
            return index;
        }
    }
}
