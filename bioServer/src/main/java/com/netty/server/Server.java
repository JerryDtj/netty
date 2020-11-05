package com.netty.server;

import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author Jerry
 * @Date 2020/11/4 9:31 下午
 */
public class Server {
    private static final String IPADDRESS = "localhost";
    private static final int PORT = 9900;
    private static final int COREGROUPNUM = Runtime.getRuntime().availableProcessors() * 2;
    private static final int THREADNUM = 100;

    //主要是用于接收连接，然后accept阻塞
    private static final NioEventLoopGroup bossGroup = new NioEventLoopGroup(COREGROUPNUM);
    //主要是用于业务处理，比如handler注册/查找，以及业务处理
    private static final NioEventLoopGroup workGroup = new NioEventLoopGroup(THREADNUM);

    public static void start() throws InterruptedException {
        ServerBootstrap bootstrap = initServerBootStrap();
        ChannelFuture channelFuture = bootstrap.bind(IPADDRESS,PORT).sync();
        channelFuture.channel().closeFuture().sync();
        System.out.println("server start");

    }

    private static ServerBootstrap initServerBootStrap(){
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4))
                                .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                .addLast(new TcpServerHandler)
                        ;
                    }
                });
        return bootstrap;
    }

    protected void shuntDown(){
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        start();
    }
}
