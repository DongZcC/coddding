package com.dzc.io.nio.demo.time;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 * @date 2020-06-08 19:39
 */
public class TimerClient {


    public static class ClientTask implements Runnable {

        private Selector selector;

        private SocketChannel socketChannel;

        private volatile boolean stop;

        public ClientTask() {
            try {
                selector = Selector.open();
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void run() {
            try {
                if (socketChannel.connect(new InetSocketAddress(8091))) {
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    ByteBuffer req = ByteBuffer.wrap("QUERY TIME ORDER".getBytes());
                    req.flip();

                    socketChannel.write(req);

                    if (!req.hasRemaining())
                        System.out.println("Send order 2 server succeed.");

                } else {
                    socketChannel.register(selector, SelectionKey.OP_CONNECT);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (!stop) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> keys = selector.selectedKeys();

                    Iterator<SelectionKey> it = keys.iterator();

                    while (it.hasNext()) {
                        SelectionKey k = it.next();
                        it.remove();
                        SocketChannel sc = (SocketChannel) k.channel();
                        if (k.isValid()) {
                            if (k.isConnectable()) {
                                if (sc.finishConnect()) {
                                    sc.register(selector, SelectionKey.OP_READ);
                                    //发送请求消息，读应答
                                    byte[] req = "QUERY TIME ORDER".getBytes();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
                                    writeBuffer.put(req);
                                    writeBuffer.flip();
                                    sc.write(writeBuffer);
                                    if (!writeBuffer.hasRemaining())
                                        System.out.println("Send order 2 server succeed.");
                                } else
                                    System.exit(1);// 连接失败，进程退出
                            } else if (k.isReadable()) {
                                ByteBuffer r = ByteBuffer.allocate(1024);
                                int readBytes = sc.read(r);
                                if (readBytes > 0) {
                                    r.flip();
                                    byte[] bytes = new byte[r.remaining()];
                                    r.get(bytes);

                                    String body = new String(bytes);
                                    System.out.println("Now is : " + body);
                                    this.stop = true;
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ClientTask()).start();
    }
}
