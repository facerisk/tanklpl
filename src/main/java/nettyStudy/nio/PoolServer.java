package nettyStudy.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname PoolServer
 * @Description TODO
 * @Date 2021/3/2 21:36
 * @Created by lplmbp
 */
public class PoolServer {
    ExecutorService pool = Executors.newFixedThreadPool(50);

    private Selector selector;

    public static void main(String[] args) throws IOException {
        PoolServer server = new PoolServer();
        server.initServer(8888);
        server.listen();
    }

    public void initServer(int port) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);

        this.selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动成功！");
    }

    public void listen() throws IOException {
        while (true) {
            selector.select();

            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                ite.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();

                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);

                    sc.register(this.selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    key.interestOps(key.interestOps()&(-SelectionKey.OP_READ));

                    pool.execute(new ThreadHandlerChannel(key));

                }
            }
        }
    }

    private class ThreadHandlerChannel extends Thread {
        private SelectionKey key;

        public ThreadHandlerChannel(SelectionKey key) {
            this.key = key;
        }

        @Override
        public void run() {
            SocketChannel channel = (SocketChannel)key.channel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                int size = 0;
                while((size = channel.read(buffer))>0){
                    buffer.flip();
                    baos.write(buffer.array(),0,size);
                    buffer.clear();

                }
                baos.close();
                byte[] content = baos.toByteArray();
                ByteBuffer allocate = ByteBuffer.allocate(content.length);
                allocate.put(content);
                allocate.flip();
                channel.write(allocate);
                if(size==-1){
                    channel.close();
                }else {
                    key.interestOps(key.interestOps()|SelectionKey.OP_READ);
                    key.selector().wakeup();
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
