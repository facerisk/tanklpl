package nettyStudy.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;

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
    public static void main(String[] args) throws Exception {
        //负责连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //负责工作
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);

        try {
            ServerBootstrap b = new ServerBootstrap();
            ChannelFuture f = b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ServerChildHandler());
                        }
                    })
                    .bind("127.0.0.1",8888)
                    .sync();//bind()为异步方法，所以加同步
            System.out.println("server started!");

            //阻塞的作用
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter {

    /**
     * @Decription 读数据
     * @Author lipengliang
     * @Date 2021/3/3
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bf = null;
        try {
            bf = (ByteBuf) msg;
//            System.out.println(bf);
            byte[] bytes = new byte[bf.readableBytes()];
            bf.getBytes(bf.readerIndex(), bytes);
            System.out.println(new String(bytes));

            ctx.writeAndFlush(bf);
//            System.out.println(bf.refCnt());//引用
        } finally {
            if (bf != null) {
//                ReferenceCountUtil.release(bf);
                System.out.println(bf.refCnt());
            }
        }
    }
}
