package com.visenergy.iec104;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        //生成一组
                        String pstr1 = GenerateDate.getMinuatesData1().trim();

                        String[] ls1 = pstr1.split(" ");

                        System.out.println(pstr1);
                        for (int i = 0; i < ls1.length; i++) {
                            os.write((byte)Integer.parseInt(ls1[i],16));
                        }
                        //生成二组
                        String pstr2 = GenerateDate.getMinuatesData2().trim();

                        String[] ls2 = pstr2.split(" ");

                        System.out.println(pstr2);
                        for (int i = 0; i < ls2.length; i++) {
                            os.write((byte)Integer.parseInt(ls2[i],16));
                        }
                        //生成3组
                        String pstr3 = GenerateDate.getMinuatesData3().trim();

                        String[] ls3 = pstr3.split(" ");

                        System.out.println(pstr3);
                        for (int i = 0; i < ls3.length; i++) {
                            os.write((byte)Integer.parseInt(ls3[i],16));
                        }
                        //生成4组
                        String pstr4 = GenerateDate.getMinuatesData4();

                        String[] ls4 = pstr4.split(" ");

                        System.out.println(pstr4);
                        for (int i = 0; i < ls4.length; i++) {
                            os.write((byte)Integer.parseInt(ls4[i],16));
                        }
                        //生成5组
                        String pstr5 = GenerateDate.getMinuatesData5();

                        String[] ls5 = pstr5.split(" ");

                        System.out.println(pstr5);
                        for (int i = 0; i < ls5.length; i++) {
                            os.write((byte)Integer.parseInt(ls5[i],16));
                        }
                        //生成6组
                        String pstr6 = GenerateDate.getMinuatesData6();

                        String[] ls6 = pstr6.split(" ");

                        System.out.println(pstr6);
                        for (int i = 0; i < ls6.length; i++) {
                            os.write((byte)Integer.parseInt(ls6[i],16));
                        }
                        //生成7组
                        String pstr7 = GenerateDate.getMinuatesData7();

                        String[] ls7 = pstr7.split(" ");

                        System.out.println(pstr7);
                        for (int i = 0; i < ls7.length; i++) {
                            os.write((byte)Integer.parseInt(ls7[i],16));
                        }
                        //生成8组
                        String pstr8 = GenerateDate.getMinuatesData8();

                        String[] ls8 = pstr8.split(" ");

                        System.out.println(pstr8);
                        for (int i = 0; i < ls8.length; i++) {
                            os.write((byte)Integer.parseInt(ls8[i],16));
                        }
                        //生成9组
                        String pstr9 = GenerateDate.getMinuatesData9();

                        String[] ls9 = pstr9.split(" ");

                        System.out.println(pstr9);
                        for (int i = 0; i < ls9.length; i++) {
                            os.write((byte)Integer.parseInt(ls9[i],16));
                        }
                        //生成10组
                        String pstr10 = GenerateDate.getMinuatesData10();

                        String[] ls10 = pstr10.split(" ");

                        System.out.println(pstr10);
                        for (int i = 0; i < ls10.length; i++) {
                            os.write((byte)Integer.parseInt(ls10[i],16));
                        }
                        //生成11组
                        String pstr11 = GenerateDate.getMinuatesData11();

                        String[] ls11 = pstr11.split(" ");

                        System.out.println(pstr11);
                        for (int i = 0; i < ls11.length; i++) {
                            os.write((byte)Integer.parseInt(ls11[i],16));
                        }
                        //生成1组
                        String ys1 = GenerateYxStatus.getMinuatesData();

                        String[] lis1 = ys1.split(" ");

                        System.out.println(ys1);
                        for (int i = 0; i < lis1.length; i++) {
                            os.write((byte)Integer.parseInt(lis1[i],16));
                        }

                        //生成2组
                        String ys2 = GenerateYxStatus.getMinuatesData2();

                        String[] lis2 = ys2.split(" ");

                        System.out.println(ys2);
                        for (int i = 0; i < lis2.length; i++) {
                            os.write((byte)Integer.parseInt(lis2[i],16));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
            service.scheduleAtFixedRate(runnable, 0, 20, TimeUnit.SECONDS);

            /*// 如果该字符串为 "bye"，则停止循环
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

            server.close(); // 关闭ServerSocket*/

        } catch (Exception e) {

            System.out.println("Error:" + e);

            // 出错，打印出错信息

        }
    }
}
