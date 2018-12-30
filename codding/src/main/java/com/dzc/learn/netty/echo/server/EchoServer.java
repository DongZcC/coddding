package com.dzc.learn.netty.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        new EchoServer(port).start();
    }


    public void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // Creates the eventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // Creates the ServerBootStrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)  // Specifies the use of nio transport Channel
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {  // Adds an EchoServerHandler to the Channel's pipeline
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(serverHandler); // EchoHandler is @Shareable so we can always use the same one .
                        }
                    });
            ChannelFuture f = b.bind().sync();  // Bind the server asynchronously; sync() waits for the bind to complete
            f.channel().closeFuture().sync(); // Gets the closeFuture of the Channel and blocks the current thread until it's complete
        } finally {
            group.shutdownGracefully().sync();  // Shuts down the EventLoopGroup , releasing all resources
        }
    }

}
