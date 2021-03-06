package com.lpl.netty;

import jdk.nashorn.internal.ir.CallNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Classname ServerFrame
 * @Description TODO
 * @Date 2021/3/5 19:53
 * @Created by lplmbp
 */
public class ServerFrame extends Frame {

    public static final ServerFrame INSTANCE = new ServerFrame();

    Button btnStart = new Button("start");
    TextArea taLeft = new TextArea();//多行文本
    TextArea taRight = new TextArea();
    Server server = new Server();

    public ServerFrame() {
        this.setSize(1600, 400);
        this.setLocation(300, 30);
        this.add(btnStart, BorderLayout.NORTH);
        Panel panel = new Panel(new GridLayout(1, 2));
        panel.add(taLeft);
        panel.add(taRight);

        this.add(panel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

//        this.btnStart.addActionListener((e) -> {
//            server.serverStart();//ui线程
//        });

    }


    public static void main(String[] args) {
       ServerFrame.INSTANCE.setVisible(true);
       ServerFrame.INSTANCE.server.serverStart();
    }


    public void updateServerMsg(String s) {
        this.taLeft.setText(taLeft.getText() + System.getProperty("line.separator") + s);
    }

    public void updateClientMsg(String s) {
        this.taRight.setText(taRight.getText() + System.getProperty("line.separator") + s);
    }
}