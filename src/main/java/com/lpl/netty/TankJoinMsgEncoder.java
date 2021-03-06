package com.lpl.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Classname TankMsgEncoder
 * @Description 编码器
 * @Date 2021/3/5 21:52
 * @Created by lplmbp
 */
public class TankJoinMsgEncoder extends MessageToByteEncoder<Msg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Msg msg, ByteBuf byteBuf) throws Exception {
        //定义消息头
        byteBuf.writeInt(msg.getMsgType().ordinal());
        byte[] bytes = msg.toBytes();
        byteBuf.writeInt(bytes.length);

        //消息体
        byteBuf.writeBytes(bytes);
    }
}
