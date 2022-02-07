package com.jiangjf;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * tank war
 *
 * @author jiangjf
 * @date 2022/2/7
 */
public class TankFrame extends Frame {

    int x = 200, y = 200;

    public TankFrame() {
        // 设置窗口大小
        int width = 800;
        int height = 600;
        setSize(width, height);
        // 窗口居中
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dimension.width - width) / 2, (dimension.height - height) / 2);
        // 禁用最大化
        setResizable(false);
        setTitle("tank war");
        // 显示窗口
        setVisible(true);
        // 监听关闭事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 50);
        x += 10;
        y += 10;
    }
}
