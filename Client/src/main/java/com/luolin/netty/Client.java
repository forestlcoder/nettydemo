package com.luolin.netty;

import com.luolin.netty.constant.ServerInfo;
import com.luolin.netty.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auther: luolin
 * @email: ll950129@gmail.com
 * create at 2019-03-10
 */
public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    /**
     * 客户端启动类
     * @param host  服务器IP地址
     * @param port  服务器端口
     */
    public void connect(String host, Integer port) {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast("clientHandler", new ClientHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("Client boot occur to exception.");
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        Client client = new Client();
        client.connect(ServerInfo.SERVER_IP, ServerInfo.SERVER_PORT);
    }
}
