package com.bio.zuoye.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Jerry
 * @Date 2020/10/25 10:15 上午
 */
public class Client {
    private String SERVER_URL = "localhost";
    private int PORT = 8888;

    public void send(String msg) throws IOException {
        Socket socket = new Socket(SERVER_URL,PORT);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
        System.out.println("start send msg: "+msg);
        printWriter.write(msg);
        printWriter.close();
        socket.close();
    }
}
