package com.dzc.learn.jms;

import com.rabbitmq.client.*;

import javax.jms.DeliveryMode;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class DelayQueue {

    static ConnectionFactory connectionFactory;

    static Connection connection;

    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        producer();
    }

    private static void producer() throws Exception {
        Channel channel = connection.createChannel(); // 创建一个channel

        // 定义一个交换机， 路由类型为direct
        channel.exchangeDeclare("orderExchange", "direct", true);
        // 定义一个交换机 路由类型为direct
        channel.exchangeDeclare("orderDelayExchange", "direct", true);


        HashMap<String, Object> arguments = new HashMap<>();
        // 申明死信交换机为 orderDelayExchange
        arguments.put("x-dead-letter-exchange", "orderDelayExchange");

        // 定义一个名称为 order_queue 这样就告诉rabbit此队列延迟的消息，发送给orderDelayExchange交换机
        channel.queueDeclare("order_queue", true, false, false, null);

        channel.queueDeclare("order_delay_queue", true, false, false, null);

        // order_queue 和 orderExchange 绑定
        channel.queueBind("order_queue", "orderExchange", "delay");

        channel.queueBind("order_delay_queue", "orderDelayExchange", "delay");

        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.expiration("15000");
        builder.deliveryMode(DeliveryMode.PERSISTENT);

        AMQP.BasicProperties properties = builder.build();

        Thread productThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                String order = "order" + i;

                try {
                    channel.basicPublish("orderExchange", "delay", properties, order.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });

        productThread.start();
    }


    private static void consume() throws Exception {
        Channel channel = connection.createChannel();//创建一个channel，不管是生产数据，还是消费数据，都是通过channel去操作的

        //消费名称为order_delay_queue的队列，且关闭自动应答，需要手动应答
        channel.basicConsume("order_delay_queue", false, new DefaultConsumer(channel) {
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //消息的标记，应答的时候需要传入这个参数
                long deliveryTag = envelope.getDeliveryTag();
                String str = "现在时间是" + new Date().toString() + "  " + new String(body) + "  的消息消费了";
                System.out.println(str);
                //手动应答，代表这个消息处理完成了
                channel.basicAck(deliveryTag, false);
            }
        });
    }
}
