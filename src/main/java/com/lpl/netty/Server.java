package com.lpl.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Classname Server
 * @Description TODO
 * @Date 2021/3/3 19:06
 * @Created by lplmbp
 */
public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

//    public static void main(String[] args) throws Exception {
//        //负责连接
//        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
//        //负责工作
//        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
//
//        try {
//            ServerBootstrap b = new ServerBootstrap();
//            ChannelFuture f = b.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            ChannelPipeline pipeline = socketChannel.pipeline();
//                            pipeline.addLast(new ServerChildHandler());
//                        }
//                    })
//                    .bind("127.0.0.1", 8888)
//                    .sync();//bind()为异步方法，所以加同步
//            System.out.println("server started!");
//
//            //阻塞的作用
//            f.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//        }
//
//    }

    /**
     *  @Decription 启动服务器
     *  @Author lipengliang
     *  @Date 2021/3/5
     */
    public void serverStart() {
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
                            pipeline.addLast(new MsgDecoder())
                                    .addLast(new ServerChildHandler());
                        }
                    })
                    .bind("127.0.0.1", 8888)
                    .sync();//bind()为异步方法，所以加同步

            ServerFrame.INSTANCE.updateServerMsg("server started!");
            System.out.println("server started!");

            //阻塞的作用
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    /**
     * @Decription 读数据
     * @Author lipengliang
     * @Date 2021/3/3
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //只转发
        Server.clients.writeAndFlush(msg);

        /*ByteBuf bf = null;
        try {
            bf = (ByteBuf) msg;
//            System.out.println(bf);
            byte[] bytes = new byte[bf.readableBytes()];
            bf.getBytes(bf.readerIndex(), bytes);
            String s = new String(bytes);

            //输出一份信息到服务端窗口的右边
            ServerFrame.INSTANCE.updateClientMsg(s);

            if ("_bye_".equals(s)) {
                ServerFrame.INSTANCE.updateServerMsg("客户端请求退出");
                Server.clients.remove(ctx.channel());
                ctx.close();
            } else {
                Server.clients.writeAndFlush(bf);
            }


//            System.out.println(bf.refCnt());//引用
        } finally {
            if (bf != null) {
//                ReferenceCountUtil.release(bf);
//                System.out.println(bf.refCnt());
            }
        }*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //删除出现异常的客户端channel,并关闭连接
        Server.clients.remove(ctx.channel());
        ctx.close();
    }
}
