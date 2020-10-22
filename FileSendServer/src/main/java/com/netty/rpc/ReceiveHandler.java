package com.netty.rpc;

import com.netty.send.iservice.domain.ServiceDoMain;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jerry
 * @Date 2020/10/20 8:18 上午
 */
public class ReceiveHandler extends ChannelInboundHandlerAdapter {
    private Map<String,Class> methodMap = new HashMap<String, Class>();

    public void setMethodMap(Map<String, Class> methodMap) {
        this.methodMap = methodMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ServiceDoMain){
            ServiceDoMain s = (ServiceDoMain) msg;
            Object result = "没有实现该方法，请检查版本";
            if (methodMap.containsKey(s.getClassName())){
                Class clazz = methodMap.get(s.getClassName());
                Method m = clazz.getMethod(s.getMethodName(),s.getParamTypes());
                result = m.invoke(clazz.newInstance(),s.getArgs());
            }
            ctx.writeAndFlush(result);
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
