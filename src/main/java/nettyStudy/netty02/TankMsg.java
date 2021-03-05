package nettyStudy.netty02;

/**
 * @Classname TankMsg
 * @Description TODO
 * @Date 2021/3/5 21:49
 * @Created by lplmbp
 */
public class TankMsg {
    //序列化在高效传输中不好
    //xy共8个字节，但是序列化后会变成十几个
    public int x,y;

    public TankMsg(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "TankMsg{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
