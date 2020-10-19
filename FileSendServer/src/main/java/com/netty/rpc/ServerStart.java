package com.netty.rpc;

/**
 * @author Jerry
 * @Date 2020/10/16 4:11 上午
 */
public class ServerStart {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InterruptedException {
        RpcServer rpcServer = new RpcServer();
        rpcServer.loadFile("com.netty.send");
        rpcServer.receive();
    }
}
