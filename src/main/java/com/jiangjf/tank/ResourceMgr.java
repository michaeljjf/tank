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

    static {
        try {
            ClassLoader classLoader = ResourceMgr.class.getClassLoader();
            tankLeft = ImageIO.read(classLoader.getResourceAsStream("images/tankL.gif"));
            tankRight = ImageIO.read(classLoader.getResourceAsStream("images/tankR.gif"));
            tankUp = ImageIO.read(classLoader.getResourceAsStream("images/tankU.gif"));
            tankDown = ImageIO.read(classLoader.getResourceAsStream("images/tankD.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
