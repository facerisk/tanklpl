package nettyStudy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Classname Server
 * @Description TODO
 * @Date 2021/3/2 21:10
 * @Created by lplmbp
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //全双工
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
        ssc.configureBlocking(false);

        System.out.println("server started,listening on: " + ssc.getLocalAddress());
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                handle(key);

            }
        }
    }

    private static void handle(SelectionKey key) {
        if (key.isAcceptable()) {
            try {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);

                sc.register(key.selector(),SelectionKey.OP_READ);

            } catch (IOException e) {
                e.printStackTrace();
            }finally {

            }

        }else if(key.isReadable()){
            SocketChannel sc = null;
            sc = (SocketChannel)key.channel();
            //nio:ByteBuffer只有一个指针，netty:ByteBuf有两个指针
            ByteBuffer buffer = ByteBuffer.allocate(512);
            buffer.compact();
            try {
                int len = sc.read(buffer);
                if(len != -1){
                    System.out.println(new String(buffer.array(),0,len));

                }
                ByteBuffer wrap = ByteBuffer.wrap("HelloClient".getBytes());
                sc.write(wrap);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(sc!=null){
                    try {
                        sc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}
