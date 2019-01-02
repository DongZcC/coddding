package com.dzc.learn.rpc.simple;

public class Provider {

    public static void main(String[] args) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        RpcFramework.exprot(helloService, 8081);
    }
}
