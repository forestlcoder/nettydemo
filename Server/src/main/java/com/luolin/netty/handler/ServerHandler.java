package com.luolin.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @auther: luolin
 * @email: ll950129@gmail.com
 * create at 2019-03-10
 */
public class ServerHandler extends SimpleChannelInboundHandler {
    private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
        String body = new String(req, "UTF-8");
        logger.info("Server Recv Msg {} From Client", body);
        Long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        ByteBuf resp = Unpooled.copiedBuffer(date.toString().getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
