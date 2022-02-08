package com.jiangjf.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * tank war
 *
 * @author jiangjf
 * @date 2022/2/7
 */
public class TankFrame extends Frame {

    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    Tank myTank = new Tank(200, 200, Dir.DOWN, this);
    List<Tank> otherTanks = new ArrayList<>();
    List<Bullet> bullets = new ArrayList<>();

    public TankFrame() {
        // 设置窗口大小
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        // 窗口居中
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dimension.width - GAME_WIDTH) / 2, (dimension.height - GAME_HEIGHT) / 2);
        // 禁用最大化
        this.setResizable(false);
        this.setTitle("tank war");
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

        initOtherTanks();
    }

    /**
     * 初始化敌方坦克
     */
    private void initOtherTanks() {
        Random random = new Random();
        int max = random.nextInt(10);
        for (int i = 0; i < max; i++) {
            int x = random.nextInt(GAME_WIDTH);
            int y = random.nextInt(GAME_HEIGHT);
            Tank tank = new Tank(x, y, Dir.RIGHT, this);
            tank.setColor(Color.MAGENTA);
            tank.setMoving(true);
            otherTanks.add(tank);
        }
    }


    @Override
    public void paint(Graphics g) {
        System.out.println("paint");

        Color color = g.getColor();
        g.setColor(Color.BLACK);
        g.drawString("子弹数量：" + bullets.size(), 15, 50);
        g.setColor(color);

        // 画出我方坦克
        myTank.paint(g);

        // 画出敌方坦克
        for (int i = 0; i < otherTanks.size(); i++) {
            otherTanks.get(i).paint(g);
        }

        // 画出子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
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
            System.out.println("keyPressed");
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
            System.out.println("keyReleased");
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
