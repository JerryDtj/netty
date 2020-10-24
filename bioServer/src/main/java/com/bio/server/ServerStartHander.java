package com.bio.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * @author Jerry
 * @Date 2020/10/22 10:20 下午
 */
public class ServerStartHander implements Runnable {
    private Logger log = LoggerFactory.getLogger(getClass());
    private Socket socket;

    public ServerStartHander(Socket socket) {
        this.socket = socket;
    }


    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            String exprocess;
            String result;
            while (true){
                if ((exprocess = bufferedReader.readLine())!=null){
                    System.out.println("bio服务端接收到消息："+exprocess);
                    result = Calculator.cal(exprocess);
                    printWriter.println(result);
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(bufferedReader)){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (Objects.nonNull(socket)){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (Objects.nonNull(printWriter)){
                printWriter.close();
            }
        }
    }
}
