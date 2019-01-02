package com.dzc.learn.rpc.simple;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello : " + name;
    }
}
