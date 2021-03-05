package nettyStudy.netty02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Classname TankMsgEncoder
 * @Description 编码器
 * @Date 2021/3/5 21:52
 * @Created by lplmbp
 */
public class TankMsgEncoder extends MessageToByteEncoder<TankMsg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, TankMsg tankMsg, ByteBuf byteBuf) throws Exception {
        //写进字节数组
        byteBuf.writeInt(tankMsg.x);
        byteBuf.writeInt(tankMsg.y);
    }
}
