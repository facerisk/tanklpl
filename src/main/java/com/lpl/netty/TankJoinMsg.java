package com.lpl.netty;

import com.lpl.tank.Dir;
import com.lpl.tank.Group;
import com.lpl.tank.Tank;
import com.lpl.tank.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @Classname TankMsg
 * @Description 坦克加入消息
 * @Date 2021/3/5 21:49
 * @Created by lplmbp
 */
public class TankJoinMsg extends Msg{

    public int x, y;
    public Dir dir;
    public boolean moving;
    public Group group;
    //uuid应该服务端生成
    public UUID id;



    public TankJoinMsg(Tank t) {
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
        this.group = t.getGroup();
        this.id = t.getId();
        this.moving = t.isMoving();
    }

    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = id;
    }

    public TankJoinMsg() {
    }

    /**
     *  @Decription 读
     *  @Author lipengliang
     *  @Date 2021/3/6
     */
    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            //TODO:先读TYPE信息，根据TYPE信息处理不同的消息
            //略过消息类型
            //dis.readInt();

            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.group = Group.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(), dis.readLong());
            //this.name = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     *  @Decription 向外写
     *  @Author lipengliang
     *  @Date 2021/3/6
     */
    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);

            //dos.writeInt(TYPE.ordinal());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            //uuid是128位，16字节，所以要两个long类型存储
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            //dos.writeUTF(name);
            dos.flush();
            bytes = baos.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    /**
     *  @Decription 识别服务器消息内容，潘敦啊是否重新发送自己的信息给服务器
     *  @Author lipengliang
     *  @Date 2021/3/6
     */
    @Override
    public void handle() {
        //客户端展示加入的所有坦克，主要是展示后加入服务器的坦克
        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getId()) ||
                TankFrame.INSTANCE.findTankByUUID(this.id) != null) return;
        System.out.println(this);
        Tank tank = new Tank(this);
        TankFrame.INSTANCE.addTank(tank);
        //再一次把自己发送给服务器
        Client.INSTANCE.send(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }

    /**
     *  @Decription 获取类型
     *  @Author lipengliang
     *  @Date 2021/3/6
     */
    @Override
    public MsgType getMsgType() {
        return MsgType.TankJoin;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getName())
                .append("[")
                .append("uuid=" + id + " | ")
                //.append("name=" + name + " | ")
                .append("x=" + x + " | ")
                .append("y=" + y + " | ")
                .append("moving=" + moving + " | ")
                .append("dir=" + dir + " | ")
                .append("group=" + group + " | ")
                .append("]");
        return builder.toString();
    }





}
