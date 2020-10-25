package com.bio.zuoye.server;

import java.io.IOException;

/**
 * @author Jerry
 * @Date 2020/10/25 10:31 上午
 */
public class ServerStart {
    private static int SERVER_PORT = 8888;

    public static void main(String[] args) {
        Server server = new Server();
        try {
            System.out.println("server service is start, start port is "+ SERVER_PORT);
            server.start(SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
