package com.netty.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.Set;

/**
 * @author Jerry
 * @Date 2020/10/15 7:54 上午
 */

public class RpcServerTest {


    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        try {
            rpcServer.loadFile("com.netty.send");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        Map fileMap = rpcServer.getFileMap();
        System.out.println(JSONObject.toJSONString(fileMap));
    }


}
