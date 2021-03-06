package com.lpl.netty;

import com.lpl.tank.Tank;
import com.lpl.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;


/**
 * @Classname Client
 * @Description TODO
 * @Date 2021/3/3 19:09
 * @Created by lplmbp
 */
public class Client {

    private Channel channel = null;

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
                                    .addLast(new TankJoinMsgEncoder())
                                    .addLast(new TankJoinMsgDecoder())
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
    public void send(String msg) {
        ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
        channel.writeAndFlush(buf);
    }

    /**
     * @Decription 关闭窗口时，优雅的通知服务器断开连接
     * @Author lipengliang
     * @Date 2021/3/5
     */
    public void closeConnect() {
        this.send("_bye_");
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
    }


}

//只处理一种消息类型可以继承SimpleChannelInboundHandler
class ClientHandler extends SimpleChannelInboundHandler<TankJoinMsg> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TankJoinMsg msg) throws Exception {
        //客户端展示加入的所有坦克，主要是展示后加入服务器的坦克
        if (msg.id.equals(TankFrame.INSTANCE.getMainTank().getId()) ||
                TankFrame.INSTANCE.findTankByUUID(msg.id) != null) return;
        System.out.println(msg);
        Tank tank = new Tank(msg);
        TankFrame.INSTANCE.addTank(tank);

        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //写给服务器
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));

    }
}
