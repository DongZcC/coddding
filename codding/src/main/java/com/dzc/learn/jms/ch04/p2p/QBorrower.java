package com.dzc.learn.jms.ch04.p2p;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.UUID;

public class QBorrower {

    private QueueConnection qConnect = null;

    private QueueSession qSession = null;

    private Queue reponseQ = null;

    private Queue requestQ = null;

    public QBorrower(String queuecf, String requestQueue, String responseQueue) {
        try {
            Context ctx = new InitialContext();
            QueueConnectionFactory qFactory = (QueueConnectionFactory) ctx.lookup(queuecf);

            qConnect = qFactory.createQueueConnection();

            // 创建JMS会话
            qSession = qConnect.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            // 查找请求和相应队列
            requestQ = (Queue) ctx.lookup(requestQueue);
            reponseQ = (Queue) ctx.lookup(responseQueue);

            // 完成创建启动连接
            qConnect.start();
        } catch (NamingException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void sendLoanRequest(double salary, double loanAmt) {
        try {
            // 创建JMS消息
            MapMessage msg = qSession.createMapMessage();
            msg.setDouble("salary", salary);
            msg.setDouble("loanAmount", loanAmt);
            msg.setJMSReplyTo(reponseQ);

            UUID uuid = UUID.randomUUID();
            String uniqueId = uuid.toString();
            msg.setStringProperty("UUID", uniqueId);


            // 创建发送者并发送消息
            QueueSender qSender = qSession.createSender(requestQ);
            qSender.send(msg);

            // 等待查看贷款申请被接收 或者拒绝

//            String filter = "JMSCorrelationID='" + msg.getJMSMessageID() + "'";
            String filter = "JMSCorrelationID='" + uniqueId + "'";
            System.out.println(filter);
            QueueReceiver qReceiver = qSession.createReceiver(reponseQ, filter);
            TextMessage tmsg = (TextMessage) qReceiver.receive(30000);
            if (tmsg == null) {
                System.out.println("QLender not responding");
            } else {
                System.out.println("Loan request was " + tmsg.getText());
            }
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
        String responseq = "LoanResponseQ";

        QBorrower borrower = new QBorrower(queuecf, requestq, responseq);
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("QBorrower Application Started");
            System.out.println("Press enter to quit application");
            System.out.println("Enter: Salary, Loan_Amount");
            System.out.println("\ne.g. 50000, 120000");

            while (true) {
                System.out.println("> ");
                String loanRequest = stdin.readLine();
                if (loanRequest == null || loanRequest.trim().length() <= 0) {
                    borrower.exit();
                }

                StringTokenizer st = new StringTokenizer(loanRequest, ",");
                double salary = Double.valueOf(st.nextToken().trim()).doubleValue();
                double loanAmt = Double.valueOf(st.nextToken().trim()).doubleValue();
                borrower.sendLoanRequest(salary, loanAmt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
