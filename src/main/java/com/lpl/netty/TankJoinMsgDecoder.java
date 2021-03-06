package com.lpl.netty;

import com.lpl.tank.Dir;
import com.lpl.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * @Classname TankMsgDecoder
 * @Description 自定义协议
 * @Date 2021/3/5 21:54
 * @Created by lplmbp
 */
public class TankJoinMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //消息固定可计算字节，不固定时可在消息头获取
        if (byteBuf.readableBytes() < 33) return;//解决TCP拆包 粘包的问题

//        byteBuf.markReaderIndex();

        TankJoinMsg msg = new TankJoinMsg();

        msg.x = byteBuf.readInt();
        msg.y = byteBuf.readInt();
        msg.dir = Dir.values()[byteBuf.readInt()];
        msg.moving = byteBuf.readBoolean();
        msg.group = Group.values()[byteBuf.readInt()];
        msg.id= new UUID(byteBuf.readLong(),byteBuf.readLong());

        list.add(msg);

    }
}
