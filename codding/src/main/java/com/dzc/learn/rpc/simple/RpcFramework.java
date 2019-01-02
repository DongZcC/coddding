package com.dzc.learn.rpc.simple;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcFramework {

    public static void exprot(Object service, int port) throws Exception {
        if (service == null)
            throw new IllegalArgumentException("service can't be null");

        if (port > 65535 || port <= 0)
            throw new IllegalArgumentException("port is illegal");

        // 绑定server
        ServerSocket server = new ServerSocket(port);

        for (; ; ) {
            // 获取外部连接
            try {
                final Socket socket = server.accept();
                new Thread() {
                    @Override
                    public void run() {
                        try (
                                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())
                        ) {
                            // 读入方法名称
                            String methodName = input.readUTF();
                            // 读入参数类型
                            Class<?>[] parameterPype = (Class<?>[]) input.readObject();
                            // 读入参数
                            Object[] arguments = (Object[]) input.readObject();

                            Method method = service.getClass().getMethod(methodName, parameterPype);
                            Object result = method.invoke(service, arguments);
                            output.writeObject(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    // 服务引用
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) {
        if (interfaceClass == null)
            throw new IllegalArgumentException("interfaceClass  can't be null");

        if (!interfaceClass.isInterface())
            throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");

        if (host == null || host.length() == 0)
            throw new IllegalArgumentException("Host == null!");

        if (port > 65535 || port <= 0)
            throw new IllegalArgumentException("port is illegal");

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try (Socket socket = new Socket(host, port);
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

                    output.writeUTF(method.getName());
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(args);

                    Object result = input.readObject();
                    if (result instanceof Throwable)
                        throw (Throwable) result;
                    return result;
                }
            }
        });
    }
}
