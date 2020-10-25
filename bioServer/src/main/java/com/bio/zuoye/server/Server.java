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
        while (true){
            Socket socket = serverSocket.accept();
            ServerHander serverHander = new ServerHander();
            serverHander.setSocket(socket);
            new Thread(serverHander).start();
        }
    }
}
