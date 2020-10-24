package com.bio.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jerry
 * @Date 2020/10/22 10:12 下午
 */

public class ServerStart {
    private static Logger log = LoggerFactory.getLogger(ServerStart.class);

    private static int SERVER_POINT = 7777;
    private static ServerSocket serverSocket;

    public static void start() throws IOException {
        Start(SERVER_POINT);
    }

    private synchronized static void Start(int serverPoint)  {
        if (serverSocket!=null) return;
        try {
            serverSocket = new ServerSocket(serverPoint);
            System.out.println("Bio服务端已启动，启动端口为：" + serverPoint);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerStartHander(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
        } finally {
            try {
                if (serverSocket!=null){
                    serverSocket.close();
                    System.out.println("服务端已经关闭");
                    serverSocket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}