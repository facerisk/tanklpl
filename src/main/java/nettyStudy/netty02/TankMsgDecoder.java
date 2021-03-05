package nettyStudy.netty02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Classname TankMsgDecoder
 * @Description TODO
 * @Date 2021/3/5 21:54
 * @Created by lplmbp
 */
public class TankMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 8) return;//解决TCP拆包 粘包的问题

//        byteBuf.markReaderIndex();

        int x = byteBuf.readInt();
        int y = byteBuf.readInt();

        list.add(new TankMsg(x, y));


    }
}
