package com.visenergy.iec104;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/8/23 0023.
 */
public class ServerTest {
    public static void main(String[] args) {
        try {

            ServerSocket server = null;

            try {
                // 创建一个ServerSocket在端口2405监听客户请求
                server = new ServerSocket(2405);
            } catch (Exception e) {
                // 出错，打印出错信息
                System.out.println("can not listen to:" + e);
            }

            Socket socket = null;

            try {
                // 使用accept()阻塞等待客户请求，有客户
                // 请求到来则产生一个Socket对象，并继续执行
                socket = server.accept();
                System.out.println("接受"+ socket.getInetAddress() +"连接");
            } catch (Exception e) {
                // 出错，打印出错信息
                System.out.println("Error." + e);
            }


            // 由Socket对象得到输入流，并构造相应的BufferedReader对象
            BufferedReader is = new BufferedReader(new InputStreamReader(
                    System.in));

            // 在标准输出上打印从客户端读入的字符串
           OutputStream os = socket.getOutputStream();

            // 如果该字符串为 "bye"，则停止循环
            String line = is.readLine();

            while (!line.equals("bye")) {
                String[] ls = line.split(" ");
                for (int i = 0; i < ls.length; i++) {
                    os.write((byte)Integer.parseInt(ls[i],16));
                }

                // 从Client读入一字符串，并打印到标准输出上
                line = is.readLine();

            } // 继续循环

            os.close(); // 关闭Socket输出流

            is.close(); // 关闭Socket输入流

            socket.close(); // 关闭Socket

            server.close(); // 关闭ServerSocket

        } catch (Exception e) {

            System.out.println("Error:" + e);

            // 出错，打印出错信息

        }
    }
}
