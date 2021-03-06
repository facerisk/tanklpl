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
public class TankJoinMsgEncoder extends MessageToByteEncoder<TankJoinMsg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TankJoinMsg tankJoinMsg, ByteBuf byteBuf) throws Exception {

        byteBuf.writeBytes(tankJoinMsg.toBytes());
    }
}
