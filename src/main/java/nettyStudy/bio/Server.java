package nettyStudy.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Classname Server
 * @Description TODO
 * @Date 2021/3/2 20:56
 * @Created by lplmbp
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("127.0.0.1",8888));
        while (true){
            //阻塞方法
            Socket s = ss.accept();

            new Thread(()->{
                handle(s);
            }).start();
        }

    }

    private static void handle(Socket s) {
        byte[] bytes = new byte[1024];
        try {
            int len = s.getInputStream().read(bytes);
            System.out.println("server:"+new String(bytes,0,len));

            s.getOutputStream().write(bytes,0,len);
            s.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
