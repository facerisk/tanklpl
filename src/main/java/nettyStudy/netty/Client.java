package nettyStudy.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @Classname Client
 * @Description TODO
 * @Date 2021/3/3 19:09
 * @Created by lplmbp
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("本电脑核数："+Runtime.getRuntime().availableProcessors());
        //线程池 默认内核*2个线程
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println(socketChannel);
                        }
                    })
                    .connect("127.0.0.1",8888)
                    .sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("close");
            group.shutdownGracefully();
        }


    }
}
