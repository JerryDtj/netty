package com.bio.client;

import com.bio.server.ServerStart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.applet.Main;
import sun.rmi.runtime.Log;

import java.io.*;
import java.net.Socket;
import java.rmi.server.ExportException;
import java.util.Objects;
import java.util.Random;

/**
 * @author Jerry
 * @Date 2020/10/24 2:55 下午
 */
public class ClientStart {
    private static Logger log = LoggerFactory.getLogger(ClientStart.class);

    private static int SERVER_PORT = 7777;
    private static String SERVER_IP = "127.0.0.1";

    public static void send(String exprocess){
        BufferedReader in = null;
        PrintWriter out = null;
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP,SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println(exprocess);
            System.out.println("发送消息："+in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (Objects.nonNull(out)){
                out.close();
            }
            if (Objects.nonNull(socket)){
                try {
                    socket.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }



}
