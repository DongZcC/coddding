package com.dzc.learn.jms.ch04.p2p;


import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QLender implements MessageListener {
    private QueueConnection qConnect = null;
    private QueueSession qSession = null;
    private Queue requestQ = null;

    public QLender(String queuecf, String requestQueue) {
        try {
            Context ctx = new InitialContext();
            QueueConnectionFactory qFactory = (QueueConnectionFactory) ctx.lookup(queuecf);
            qConnect = qFactory.createQueueConnection();

            // 创建JMS会话
            qSession = qConnect.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            // 查找申请队列
            requestQ = (Queue) ctx.lookup(requestQueue);

            // 启动连接
            qConnect.start();


            // 创建消息监听器
            QueueReceiver qReceiver = qSession.createReceiver(requestQ);
            qReceiver.setMessageListener(this);

            System.out.println("Waiting for loan requests ...");
        } catch (NamingException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Passes a message to the listener.
     *
     * @param message the message passed to the listener
     */
    @Override
    public void onMessage(Message message) {
        try {
            boolean accept = false;

            // 从消息中获取数据
            MapMessage msg = (MapMessage) message;
            double salary = msg.getDouble("salary");
            double loanAmt = msg.getDouble("loanAmount");

            if (loanAmt < 200000) {
                accept = (salary / loanAmt) > .25;
            } else {
                accept = (salary / loanAmt) > .33;
            }


            System.out.println("% =  " + (salary / loanAmt) + ", loan is "
                    + (accept ? "Accepted!" : "Declined"));

            TextMessage tmsg = qSession.createTextMessage();
            tmsg.setText(accept ? "Accepted!" : "Declined");
            System.out.println(msg.getJMSMessageID());
            tmsg.setJMSCorrelationID(msg.getJMSMessageID());

            // 创建消息发送者并发送消息
            QueueSender qSender = qSession.createSender((Queue) message.getJMSReplyTo());
            qSender.send(tmsg);

            System.out.println("\nWaiting for loan requests ...");
        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    private void exit() {
        try {
            qConnect.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        String queuecf = "QueueCF";
        String requestq = "LoanRequestQ";

        QLender lender = new QLender(queuecf, requestq);

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("QLender application started");
            System.out.println("Please enter to quit application");
            bf.readLine();
            lender.exit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
