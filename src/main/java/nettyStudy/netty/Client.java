package nettyStudy.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;


/**
 * @Classname Client
 * @Description TODO
 * @Date 2021/3/3 19:09
 * @Created by lplmbp
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("本电脑核数：" + Runtime.getRuntime().availableProcessors());
        //线程池 默认内核*2个线程
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try {
            ChannelFuture f = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    })
                    .connect("127.0.0.1", 8888);
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        System.out.println("not connected!");
                    } else {
                        System.out.println("connected!");
                    }
                }
            });
            f.sync();
            //固定写法
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("close");
            group.shutdownGracefully();
        }


    }
}

class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bf = null;
        try {
            bf = (ByteBuf) msg;
//            System.out.println(bf);
            byte[] bytes = new byte[bf.readableBytes()];
            bf.getBytes(bf.readerIndex(), bytes);
            System.out.println(new String(bytes));
//            System.out.println(bf.refCnt());//引用
        } finally {
            if (bf != null) {
                ReferenceCountUtil.release(bf);
                System.out.println(bf.refCnt());
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //channel 第一次连上可用，写出一个字符串 Direct Memory
        ByteBuf bf = Unpooled.copiedBuffer("hello".getBytes());
        ctx.writeAndFlush(bf);//该方法，自动释放直接内存
    }
}
