package com.bio.zuoye.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jerry
 * @Date 2020/10/25 10:36 上午
 */
public class Server {
    protected void start(int serverPort) throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverPort);
//        因为想服务端一直接收消息，所以这里死循环
        while (true){
            //如果没有服务介入，服务端会在accept()上阻塞
            Socket socket = serverSocket.accept();
            ServerHander serverHander = new ServerHander();
            serverHander.setSocket(socket);
            new Thread(serverHander).start();
        }
    }
}
