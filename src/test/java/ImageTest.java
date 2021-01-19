
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname ImageTest
 * @Description TODO
 * @Date 2021/1/18 23:11
 * @Created by lplmbp
 */
public class ImageTest {
    @Test
    void test(){
        try {
//            BufferedImage read = ImageIO.read(new File("images/0.gif"));
//            assertNotNull(read);
            BufferedImage read = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/0.gif"));

            assertNotNull(read);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
