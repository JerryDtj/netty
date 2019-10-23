package server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author Jerry
 * @Date 2019/10/19 8:35 上午
 */
public class ServerHander extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for (Channel c:channels){
            c.writeAndFlush(String.format("id:%s,ip:%s is coming",channel.id(),channel.remoteAddress()));
        }
        channels.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for (Channel c:channels){
            c.writeAndFlush(String.format("id:%s,ip:%s is remove",channel.id(),channel.remoteAddress()));
        }
        channels.remove(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channel.writeAndFlush(String.format(String.format("id:%s,ip:%s is online",channel.id(),channel.remoteAddress())));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channel.writeAndFlush(String.format(String.format("id:%s,ip:%s is offline",channel.id(),channel.remoteAddress())));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        for (Channel c:channels){
            if (c!=channel){
                c.writeAndFlush(String.format(String.format("id:%s,ip:%s send a msg: %s",channel.id(),channel.remoteAddress(),msg)));
            }else {
                c.writeAndFlush(String.format(String.format("you send a msg: %s",msg)));
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("SimpleChatClient:"+channel.remoteAddress()+"异常");
        cause.printStackTrace();
        ctx.close();
    }
}
