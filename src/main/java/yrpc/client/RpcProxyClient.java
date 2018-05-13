package yrpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author yudungang
 */
public class RpcProxyClient<T> {

    public T proxyClient(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try(Socket socket = new Socket("localhost",8000)){
                    try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
                        oos.writeUTF(method.getName());
                        oos.writeObject(args);
                        oos.flush();
                        try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
                            return ois.readObject();
                        }
                    }
                }
            }
        });
    }


}
