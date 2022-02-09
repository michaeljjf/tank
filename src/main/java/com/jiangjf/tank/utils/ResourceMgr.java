package com.jiangjf.tank.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author jiangjf
 * @date 2022/2/8
 */
public class ResourceMgr {
    public static BufferedImage tankLeft, tankRight, tankUp, tankDown;
    public static BufferedImage badTankLeft, badTankRight, badTankUp, badTankDown;
    public static BufferedImage bulletUp, bulletDown, bulletLeft, bulletRight;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            ClassLoader classLoader = ResourceMgr.class.getClassLoader();

            tankUp = ImageIO.read(classLoader.getResourceAsStream("images/GoodTank1.png"));
            tankLeft = ImageUtil.rotateImage(tankUp, -90);
            tankRight = ImageUtil.rotateImage(tankUp, 90);
            tankDown = ImageUtil.rotateImage(tankUp, 180);

            badTankUp = ImageIO.read(classLoader.getResourceAsStream("images/BadTank1.png"));
            badTankLeft = ImageUtil.rotateImage(badTankUp, -90);
            badTankRight = ImageUtil.rotateImage(badTankUp, 90);
            badTankDown = ImageUtil.rotateImage(badTankUp, 180);

            bulletUp = ImageIO.read(classLoader.getResourceAsStream("images/bulletU.png"));
            bulletLeft = ImageUtil.rotateImage(bulletUp, -90);
            bulletRight = ImageUtil.rotateImage(bulletUp, 90);
            bulletDown = ImageUtil.rotateImage(bulletUp, 180);

            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(classLoader.getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
