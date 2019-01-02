package com.dzc.learn.rpc.simple;

public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = RpcFramework.refer(HelloService.class, "127.0.0.1", 8081);
        System.out.println(helloService.hello("zyw"));
    }
}
