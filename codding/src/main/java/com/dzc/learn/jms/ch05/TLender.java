package com.dzc.learn.jms.ch05;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TLender {

    private TopicConnection connection = null;

    private TopicSession session = null;

    private Topic topic = null;

    public TLender(String factory, String topicName) {
        try {
            Context context = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(factory);

            this.connection = (TopicConnection) connectionFactory.createConnection();
            this.session = this.connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            topic = (Topic) context.lookup(topicName);

            connection.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    private void publishRate(double newRate) {
        try {
            BytesMessage bytesMessage = session.createBytesMessage();
            bytesMessage.writeDouble(newRate);

            TopicPublisher publisher = session.createPublisher(topic);
            publisher.publish(bytesMessage);

        } catch (JMSException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    private void exit() {
        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }


    public static void main(String[] args) {
        String topiccf = "TopicCF";
        String topicName = "RateTopic";

        try {
            // Read all standard input and send it as a message
            BufferedReader stdin = new BufferedReader
                    (new InputStreamReader(System.in));
            System.out.println("TLender Application Started");
            System.out.println("Press enter to quit application");
            System.out.println("Enter: Rate");
            System.out.println("\ne.g. 6.8");


            TLender lender = new TLender(topiccf, topicName);


            while (true) {
                System.out.print("> ");

                String rate = stdin.readLine();
                if (rate == null || rate.trim().length() <= 0) {
                    lender.exit();
                }

                // Parse the deal description
                double newRate = Double.valueOf(rate);
                lender.publishRate(newRate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


