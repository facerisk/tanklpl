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
    public static BufferedImage goodTankL,goodTankU,goodTankR,goodTankD;
    //敌方坦克
    public static BufferedImage badTankL,badTankU,badTankR,badTankD;
    //子弹图片
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;
    //爆炸图片
    public static BufferedImage[] explodes = new BufferedImage[16];

    static{
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);
            goodTankR = ImageUtil.rotateImage(goodTankU,90);
            goodTankD = ImageUtil.rotateImage(goodTankU,180);


            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU,-90);
            badTankR = ImageUtil.rotateImage(badTankU,90);
            badTankD = ImageUtil.rotateImage(badTankU,180);

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
