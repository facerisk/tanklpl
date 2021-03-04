package nettyStudy.netty02;

import nettyStudy.netty.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Classname ClientFrame
 * @Description TODO
 * @Date 2021/3/4 20:26
 * @Created by lplmbp
 */
public class ClientFrame extends Frame {

    TextArea ta = new TextArea();//多行文本
    TextField tf = new TextField();//单行

    public ClientFrame() {
        this.setSize(600, 400);
        this.setLocation(100, 20);
        this.add(ta, BorderLayout.CENTER);
        this.add(tf, BorderLayout.SOUTH);

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //把字符串发送到服务器
                ta.setText(ta.getText() + tf.getText());

                tf.setText("");

            }
        });

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ClientFrame();
    }


}
