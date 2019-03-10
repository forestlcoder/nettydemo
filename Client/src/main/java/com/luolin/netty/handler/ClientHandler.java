package com.luolin.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther: luolin
 * @email: ll950129@gmail.com
 * create at 2019-03-10
 */
public class ClientHandler extends SimpleChannelInboundHandler {

    private static Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    private final ByteBuf msg;

    public ClientHandler() {
        byte[] req = "QUERY TIME".getBytes();
        msg = Unpooled.buffer(req.length);
        msg.writeBytes(req);
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write(msg);
    }
}
