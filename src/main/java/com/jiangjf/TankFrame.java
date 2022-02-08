package com.jiangjf;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * tank war
 *
 * @author jiangjf
 * @date 2022/2/7
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.DOWN);
    Bullet bullet = new Bullet(300, 300, Dir.DOWN);

    public TankFrame() {
        // 设置窗口大小
        int width = 800;
        int height = 600;
        this.setSize(width, height);
        // 窗口居中
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dimension.width - width) / 2, (dimension.height - height) / 2);
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
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        myTank.paint(g);
        bullet.paint(g);
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
