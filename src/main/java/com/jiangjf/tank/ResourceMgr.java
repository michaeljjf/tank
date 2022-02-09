package com.jiangjf.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author jiangjf
 * @date 2022/2/8
 */
public class ResourceMgr {
    public static BufferedImage tankLeft, tankRight, tankUp, tankDown;
    public static BufferedImage imageBullet;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            ClassLoader classLoader = ResourceMgr.class.getClassLoader();

            tankLeft = ImageIO.read(classLoader.getResourceAsStream("images/tankL.gif"));
            tankRight = ImageIO.read(classLoader.getResourceAsStream("images/tankR.gif"));
            tankUp = ImageIO.read(classLoader.getResourceAsStream("images/tankU.gif"));
            tankDown = ImageIO.read(classLoader.getResourceAsStream("images/tankD.gif"));

            imageBullet = ImageIO.read(classLoader.getResourceAsStream("images/1.gif"));

            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(classLoader.getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
