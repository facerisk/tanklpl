package com.lpl.netty;

import com.lpl.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @Classname Client
 * @Description TODO
 * @Date 2021/3/3 19:09
 * @Created by lplmbp
 */
public class Client {

    public static final Client INSTANCE = new Client();
    private Channel channel = null;

    private Client(){

    }
    public void connect() {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        try {
            ChannelFuture f = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //channel可以加处理器，处理器可以为责任链
                            socketChannel.pipeline()
                                    .addLast(new MsgEncoder())
                                    .addLast(new MsgDecoder())
                                    .addLast(new ClientHandler());
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
                        channel = channelFuture.channel();
                    }
                }
            });
            f.sync();
            //固定写法 阻塞
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("close");
            group.shutdownGracefully();
        }


    }

    /**
     * @Decription 将msg发送给服务器
     * @Author lipengliang
     * @Date 2021/3/4
     */
    public void send(Msg msg) {
        channel.writeAndFlush(msg);
    }

    /**
     * @Decription 关闭窗口时，优雅的通知服务器断开连接
     * @Author lipengliang
     * @Date 2021/3/5
     */
    public void closeConnect() {
//        this.send("_bye_");
    }



}

//只处理一种消息类型可以继承SimpleChannelInboundHandler
class ClientHandler extends SimpleChannelInboundHandler<Msg> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
        System.out.println("log:"+msg);
        msg.handle();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //写给服务器
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));

    }
}
