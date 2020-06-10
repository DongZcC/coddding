package com.dzc.io.nio.demo.time;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Administrator
 * @date 2020-06-08 19:22
 */
public class NioServer {


    public static void main(String[] args) {

        new Thread(new ReactorTask()).start();
    }

    public static class ReactorTask implements Runnable {


        private Selector selector;


        public ReactorTask() {
            try {
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

                serverSocketChannel.bind(new InetSocketAddress(8091));
                serverSocketChannel.configureBlocking(false);
                selector = Selector.open();
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = keys.iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        keyIterator.remove();

                        if (key.isValid()) {
                            if (key.isAcceptable()) {
                                // 接受请求
                                doAccept(key);
                            } else if (key.isReadable()) {
                                doRead(key);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void doRead(SelectionKey key) {
            // 第九步：异步读取客户端请求消息到缓存区
            SocketChannel sc = (SocketChannel) key.channel();
            try {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);

                // 第十步：对ByteBuffer进行编解码，如果有半包消息指针reset，继续读取后续的报文
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order : " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                            ? new java.util.Date(System.currentTimeMillis()).toString()
                            : "BAD ORDER";
                    //写应答
                    byte[] bytes2 = currentTime.getBytes();
                    ByteBuffer writeBuffer = ByteBuffer.allocate(bytes2.length);
                    writeBuffer.put(bytes2);
                    writeBuffer.flip();
                    sc.write(writeBuffer);
                } else if (readBytes < 0) {
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                } else
                    ; // 读到0字节，忽略
            } catch (Exception e) {
                key.cancel();
                try {
                    sc.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        private void doAccept(SelectionKey key) {
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            try {
                SocketChannel socket = channel.accept();
                socket.configureBlocking(false);
                socket.socket().setReuseAddress(true);
                socket.register(selector, SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
                key.cancel();
                try {
                    channel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

}
