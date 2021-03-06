package com.lpl.netty;

/**
 * @Classname Msg
 * @Description 消息父类
 * @Date 2021/3/6 20:00
 * @Created by lplmbp
 */
public abstract class Msg {

    public abstract void handle();
    public abstract byte[] toBytes();
    public abstract void parse(byte[] bytes);
    public abstract MsgType getMsgType();

}
