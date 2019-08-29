package com.dzc.learn.jms.ch04.p2p;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Enumeration;

public class LoanRequestQueueBrowser {

    public static void main(String[] args) {
        try {
            Context context = new InitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("QueueCF");

            QueueConnection connection = factory.createQueueConnection();
            connection.start();

            // 建立会话
            Queue requestQ = (Queue) context.lookup("LoanRequestQ");
            QueueSession queueSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueBrowser browser = queueSession.createBrowser(requestQ);

            Enumeration enumeration = browser.getEnumeration();
            while (enumeration.hasMoreElements()) {
                MapMessage msg = (MapMessage) enumeration.nextElement();
                System.out.println("Browsing : " + msg);
            }

            browser.close();
            connection.close();
            System.exit(0);
        } catch(Exception e) {

        }
    }
}
