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
public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //消息固定可计算字节，不固定时可在消息头获取
        if (byteBuf.readableBytes() < 8) return;//解决TCP拆包 粘包的问题

        //记录读取位置
        byteBuf.markReaderIndex();

        //读消息头
        MsgType msgType = MsgType.values()[byteBuf.readInt()];
        int length = byteBuf.readInt();

        //判断消息体是否符合消息头中记录的长度
        if (byteBuf.readableBytes() < length) {
            //重置会上一读取的位置
            byteBuf.resetReaderIndex();
            return;
        }

        //读取消息体
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Msg msg = null;
        switch (msgType) {
            case TankJoin:
                msg = new TankJoinMsg();
                break;
            case TankStartMoving:
                msg = new TankStartMovingMsg();
                break;
            default:
                break;

        }
        msg.parse(bytes);
        list.add(msg);

    }
}
