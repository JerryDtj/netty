package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Jerry
 * @Date 2019/10/19 9:16 上午
 */
public class ServerStart {
//    private int port;
//    ServerStart(int port){
//        this.port = port;
//    }

    public static void main(String[] args) {
//        int port;
//        if (args.length>0){
//            port = Integer.parseInt(args[0]);
//        }else {
//            port = 8080;
//        }
//
//        new ServerStart(port).start();

        EventLoopGroup serverGoup = new NioEventLoopGroup();
        EventLoopGroup clientGoup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(serverGoup, clientGoup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer())
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
        ;
        try {
            ChannelFuture future = bootstrap.bind(8080).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            serverGoup.shutdownGracefully();
            clientGoup.shutdownGracefully();
        }
    }

    private void start() {
//        EventLoopGroup serverGoup = new NioEventLoopGroup();
//        EventLoopGroup clientGoup = new NioEventLoopGroup();
//
//        ServerBootstrap bootstrap = new ServerBootstrap();
//        bootstrap.group(serverGoup,clientGoup)
//                .channel(NioServerSocketChannel.class)
//                .childHandler(new ServerChannelInitializer())
//                .option(ChannelOption.SO_BACKLOG,128)
//                .childOption(ChannelOption.SO_KEEPALIVE,true)
//                ;
//        try {
//            ChannelFuture future = bootstrap.bind(port).sync();
//            future.channel().closeFuture().sync();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            serverGoup.shutdownGracefully();
//            clientGoup.shutdownGracefully();
//        }

    }
}
