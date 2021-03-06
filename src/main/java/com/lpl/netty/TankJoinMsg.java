package com.lpl.netty;

import com.lpl.tank.Dir;
import com.lpl.tank.Group;
import com.lpl.tank.Tank;
import com.lpl.tank.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @Classname TankMsg
 * @Description TODO
 * @Date 2021/3/5 21:49
 * @Created by lplmbp
 */
public class TankJoinMsg {

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

    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            //TODO:�ȶ�TYPE��Ϣ������TYPE��Ϣ����ͬ����Ϣ
            //�Թ���Ϣ����
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

//    @Override
//    public void handle() {
//        if(this.id.equals(TankFrame.INSTANCE.getMainTank().getId()) ||
//                TankFrame.INSTANCE.findTankByUUID(this.id) != null) return;
////		System.out.println(this);
//        Tank t = new Tank(this);
//        TankFrame.INSTANCE.addTank(t);
//
//        //send a new TankJoinMsg to the new joined tank
//        Client.INSTANCE.send(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
//    }
//
//    @Override
//    public MsgType getMsgType() {
//        // TODO Auto-generated method stub
//        return MsgType.TankJoin;
//    }


}
