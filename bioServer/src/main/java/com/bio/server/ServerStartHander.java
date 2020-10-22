package com.bio.server;

import java.net.Socket;

/**
 * @author Jerry
 * @Date 2020/10/22 10:20 下午
 */
public class ServerStartHander implements Runnable {
    private Socket socket;

    public ServerStartHander(Socket socket) {
        this.socket = socket;
    }


    public void run() {

    }
}
