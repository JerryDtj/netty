package com.netty.send.client;

import com.netty.send.iservice.domain.ServiceDoMain;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * @author Jerry
 * @Date 2020/10/16 4:37 上午
 */
public class RpcProxy {

    public static<T> T create(final Class<?> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (Object.class.equals(method.getDeclaringClass())) {
                    return method.invoke(this, args);
                }
                return rpcInvoke(clazz,method,args);
            }
        });
    }

    private static Object rpcInvoke(Class<?> clazz, Method method, Object[] args) {
        final RpcClientHandler rpcClientHandler = new RpcClientHandler();

        NioEventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ObjectEncoder());
                            pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                            pipeline.addLast(rpcClientHandler);
                        }
                    });
            ChannelFuture future = bootstrap.connect("localhost",8888).sync();
            ServiceDoMain doMain = new ServiceDoMain();
            doMain.setArgs(args);
            doMain.setClassName(clazz.getName());
            doMain.setMethodName(method.getName());
            doMain.setParamTypes(method.getParameterTypes());
            future.channel().writeAndFlush(doMain).sync();

            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(loopGroup)){
                loopGroup.shutdownGracefully();
            }
        }
        return rpcClientHandler.getResult();

    }

}
