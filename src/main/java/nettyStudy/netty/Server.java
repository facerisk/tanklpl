package nettyStudy.netty;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Classname Server
 * @Description TODO
 * @Date 2021/3/3 19:06
 * @Created by lplmbp
 */
public class Server {
    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket();

        ss.bind(new InetSocketAddress("127.0.0.1",8888));

        Socket accept = ss.accept();

        System.out.println("a client connect!");
    }
}
