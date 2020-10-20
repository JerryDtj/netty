package com.netty.rpc;

import com.sun.jndi.toolkit.url.UrlUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jerry
 * @Date 2020/10/15 7:18 上午
 */
public class RpcServer {
    private Map<String,Class> fileMap = new HashMap<String, Class>();

    public Map<String, Class> getFileMap() {
        return fileMap;
    }


    public void loadFile(String packageLocations) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (StringUtils.isBlank(packageLocations)){
            throw new RuntimeException("文件路径为空");
        }
        URL url = this.getClass().getClassLoader().getResource(packageLocations.replaceAll("\\.","/"));
        File dirs = new File(url.getFile());
        for (File file:dirs.listFiles()){
            if (file.isDirectory()){
                this.loadFile(packageLocations+"."+file.getName());
            }else if (file.getName().endsWith("class")){
                String fileName = (packageLocations+"."+file.getName()).replace(".class","");
                Class c = Class.forName(fileName);
                fileMap.put(fileName,c);
            }
        }
    }

    public void receive() throws InterruptedException {
        ReceiveHandler handler = new ReceiveHandler();

        final EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup,childGroup)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ObjectEncoder());
                            pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                            pipeline.addLast(null);
                        }
                    });
            ChannelFuture future = bootstrap.bind(8888).sync();
            System.out.println("file server startup");
            future.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }


    }
}
