package com.dzc.learn.jms.ch04.p2p;

import javax.jms.ConnectionMetaData;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Enumeration;

public class MetaData {

    public static void main(String[] args) {
        try {
            Context ctx = new InitialContext();
            QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("QueueCF");

            QueueConnection queueConnection = factory.createQueueConnection();
            ConnectionMetaData metaData = queueConnection.getMetaData();

            System.out.println("JMS Version:" + metaData.getJMSVersion() + "." +
                    metaData.getJMSMajorVersion() + "." + metaData.getJMSMinorVersion());

            System.out.println("JMS Provider:" + metaData.getJMSProviderName());
            System.out.println("JMSX Properties Supported:");

            Enumeration e = metaData.getJMSXPropertyNames();
            while (e.hasMoreElements()) {
                System.out.println(" " + e.nextElement());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
