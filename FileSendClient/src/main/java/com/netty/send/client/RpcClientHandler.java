package com.netty.send.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Jerry
 * @Date 2020/10/20 7:35 上午
 */
public class RpcClientHandler extends SimpleChannelInboundHandler {
    private Object result;

    public Object getResult() {
        return result;
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.result = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
