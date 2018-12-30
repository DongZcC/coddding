package com.dzc.learn.netty.echo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * will not release the message at the channelRead method
 * The message is released in channelReadComplete method in the EchoServerHandler when writeAndFlush() is called
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in  = (ByteBuf) msg;
        // Logs the message to the console
        System.out.println("Server received : " + in.toString(CharsetUtil.UTF_8));

        // Writes the received message to the sender without flushing the outbound messages
        ctx.write(in);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // Flushes pending messages to the remote peer and close the channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();  // Closes the channel
    }
}
