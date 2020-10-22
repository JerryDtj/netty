package com.netty.send;

import com.netty.send.client.RpcProxy;

/**
 * @author Jerry
 * @Date 2020/10/16 4:37 上午
 */
public class ClientStart {
    public static void main(String[] args) {
        IAservice iAservice = RpcProxy.create(IAservice.class);
        String result = iAservice.hello("hello,word");
        System.out.println(result);
    }
}
