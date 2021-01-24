package com.lpl.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Classname ResourceMgr
 * @Description 坦克图片类
 * @Date 2021/1/19 22:22
 * @Created by lplmbp
 */
public class ResourceMgr {

    //坦克图片
    public static BufferedImage tankL,tankU,tankR,tankD;
    //子弹图片
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    //爆炸图片
    public static BufferedImage[] explodes = new BufferedImage[16];

    static{
        try {
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            tankL = ImageUtil.rotateImage(tankU,-90);
            tankR = ImageUtil.rotateImage(tankU,90);
            tankD = ImageUtil.rotateImage(tankU,180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);

            for (int i = 0; i <16 ; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
