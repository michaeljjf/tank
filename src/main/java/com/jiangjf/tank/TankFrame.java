package com.jiangjf.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * tank war
 *
 * @author jiangjf
 * @date 2022/2/7
 */
public class TankFrame extends Frame {

    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;
    Tank myTank = new Tank(210, 400, Dir.UP, Group.GOOD, this);
    List<Tank> tanks = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public TankFrame() {
        // 设置窗口大小
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        // 窗口居中
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dimension.width - GAME_WIDTH) / 2, (dimension.height - GAME_HEIGHT) / 2);
        // 禁用最大化
        this.setResizable(false);
        this.setTitle("坦克大战");
        // 显示窗口
        this.setVisible(true);
        // 监听关闭事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // 键盘监听
        this.addKeyListener(new MyKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.BLACK);
        g.drawString("子弹数量：" + bullets.size(), 15, 50);
        g.drawString("敌方坦克数量：" + tanks.size(), 95, 50);
        g.setColor(color);

        // 画出我方坦克
        myTank.paint(g);

        // 画出敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        // 画出子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        // 画出爆炸效果
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        // 子弹与敌方坦克是否碰撞
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
            bullets.get(i).collideWith(myTank);
        }

        // 下面这种通过迭代器方式在bullets.remove()的时候会越界报错
//        for (Bullet bullet : bullets) {
//            bullet.paint(g);
//        }
    }

    /**
     * 双缓冲解决画面闪的问题
     */
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    class MyKeyListener extends KeyAdapter {

        boolean boolLeft = false;
        boolean boolRight = false;
        boolean boolUp = false;
        boolean boolDown = false;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    boolLeft = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    boolRight = true;
                    break;
                case KeyEvent.VK_UP:
                    boolUp = true;
                    break;
                case KeyEvent.VK_DOWN:
                    boolDown = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    boolLeft = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    boolRight = false;
                    break;
                case KeyEvent.VK_UP:
                    boolUp = false;
                    break;
                case KeyEvent.VK_DOWN:
                    boolDown = false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!boolLeft && !boolRight && !boolUp && !boolDown) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (boolLeft) {
                    myTank.setDir(Dir.LEFT);
                }
                if (boolRight) {
                    myTank.setDir(Dir.RIGHT);
                }
                if (boolUp) {
                    myTank.setDir(Dir.UP);
                }
                if (boolDown) {
                    myTank.setDir(Dir.DOWN);
                }
            }
        }
    }
}
