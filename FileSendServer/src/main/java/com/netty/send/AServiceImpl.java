package com.netty.send;

/**
 * @author Jerry
 * @Date 2020/10/15 8:01 上午
 */
public class AServiceImpl implements IAservice {
    @Override
    public String hello(String msg) {
        return "hello netty,this is you first msg: " + msg;
    }
}
