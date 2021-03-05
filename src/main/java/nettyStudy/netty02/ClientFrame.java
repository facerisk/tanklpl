package nettyStudy.netty02;

import nettyStudy.netty02.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Classname ClientFrame
 * @Description TODO
 * @Date 2021/3/4 20:26
 * @Created by lplmbp
 */
public class ClientFrame extends Frame {

    public static final ClientFrame INSTANCE = new ClientFrame();

    TextArea ta = new TextArea();//多行文本
    TextField tf = new TextField();//单行

    Client c = null;

    public ClientFrame() {
        this.setSize(600, 400);
        this.setLocation(100, 20);
        this.add(ta, BorderLayout.CENTER);
        this.add(tf, BorderLayout.SOUTH);

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //把字符串发送到服务器
                c.send(tf.getText());

//                ta.setText(ta.getText() + tf.getText());

                tf.setText("");

            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                c.closeConnect();
                System.exit(0);
            }
        });

//        this.setVisible(true);
//
//        //窗口显示完毕后，连接客户端
//        connectToServer();
    }

    /**
     * @Decription 连接服务器
     * @Author lipengliang
     * @Date 2021/3/4
     */
    private void connectToServer() {
        c = new Client();
        c.connect();
    }


    public static void main(String[] args) {
        ClientFrame frame = ClientFrame.INSTANCE;
        frame.setVisible(true);
        frame.connectToServer();
    }


    public void updateText(String msgAccepted) {

        this.ta.setText(ta.getText() + System.getProperty("line.separator") + msgAccepted);
    }
}
