package nettyStudy.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @Classname Client
 * @Description TODO
 * @Date 2021/3/2 20:49
 * @Created by lplmbp
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1",8888);
        s.getOutputStream().write("HelloServer".getBytes());
        s.getOutputStream().flush();
        //Bio 半双工
//        s.getOutputStream().close();
        System.out.println("write over, waiting for msg back...");
        byte[] bytes = new byte[1024];
        int read = s.getInputStream().read(bytes);
        System.out.println(new String(bytes,0,read));
        s.close();
    }
}
