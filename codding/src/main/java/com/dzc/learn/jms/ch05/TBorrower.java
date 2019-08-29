package com.dzc.learn.jms.ch05;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TBorrower implements MessageListener {

    private TopicConnection connection = null;

    private TopicSession session = null;

    private Topic topic = null;

    private double currentRate;

    public TBorrower(String cf, String topicName, String rate) {
        try {
            currentRate = Double.valueOf(rate);
            Context context = new InitialContext();
            TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup(cf);

            connection = connectionFactory.createTopicConnection();
            session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            topic = (Topic) context.lookup(topicName);

            TopicSubscriber subscriber = session.createSubscriber(topic);
            subscriber.setMessageListener(this);

//            session.setMessageListener(this);
            connection.start();

            System.out.println("Waiting for loan requests ...");
        } catch (Exception e) {
            e.printStackTrace();
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
            BytesMessage bytesMessage = (BytesMessage) message;

            double rate = bytesMessage.readDouble();

            if ((currentRate - rate) >= 1.0) {
                System.out.println("New Rate = " + rate + " - Consider refinancing loan");
            } else {
                System.out.println("New Rate = " + rate + " - Keeping existing loan");
            }

            System.out.println("\nWaiting for rate updates ...");
        } catch (Exception e) {

        }
    }


    public static void main(String[] args) throws IOException {
        TBorrower borrower = new TBorrower("TopicCF", "RateTopic", "0.35");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

    }
}
