package com.visenergy.iec104.util;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/8/23 0023.
 */
public class RabbitMqUtils {
    private static String USERNAME = "test";
    private static String PASSWORD = "123456";
    private static String HOST = "192.168.100.100";
    private static int PORT = 5672;
    private static String VIRTUAL_HOST = "/";

    static{
        try {
            Properties rabbitProp = FileUtils.loadPropFile("rabbitmq.properties");
            if(!"".equals(rabbitProp.getProperty("username"))){
                USERNAME = rabbitProp.getProperty("username");
            }
            if(!"".equals(rabbitProp.getProperty("password"))){
                PASSWORD = rabbitProp.getProperty("password");
            }
            if(!"".equals(rabbitProp.getProperty("host"))){
                HOST = rabbitProp.getProperty("host");
            }
            if(!"".equals(rabbitProp.getProperty("port"))){
                PORT = Integer.parseInt(rabbitProp.getProperty("port"));
            }
            if(!"".equals(rabbitProp.getProperty("virtualhost"))){
                VIRTUAL_HOST = rabbitProp.getProperty("virtualhost");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立rabbitMq连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection newConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setHost(HOST);
        factory.setVirtualHost(VIRTUAL_HOST);
        factory.setPort(PORT);

        return factory.newConnection();
    }

    /**
     * 创建rabbitMq通道
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Channel createRabbitMqChannel(Connection conn) throws IOException, TimeoutException {
        return conn.createChannel();
    }

    public static void sendMq(Channel channel,String queueName,String message) throws IOException, TimeoutException {
        channel.queueDeclare(queueName,false,false,false,null);

        channel.basicPublish("",queueName,null,message .getBytes());
    }

    public static void receiveMq(Channel channel,String queueName) throws IOException, TimeoutException {
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("Received:" + message);
            }
        };
        channel.basicConsume(queueName,true,consumer);
    }
}
