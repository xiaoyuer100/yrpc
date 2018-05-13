package yrpc.server;

import yrpc.common.IHello;
import yrpc.server.HelloService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yudungang
 */
public class RpcProxyServer {

    private IHello hello = new HelloService();

    public void publisherServer(int port){
        try(ServerSocket ss = new ServerSocket(port)){
            while (true){
                System.out.println("服务端开启端口"+port);
                try(Socket socket = ss.accept()){
                    try(ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
                        System.out.println("客户端连接成功");
                        String method = ois.readUTF();
                        Object[] objs = (Object[]) ois.readObject();
                        Class[] types = new Class[objs.length];
                        for (int i = 0; i < types.length; i++) {
                            types[i] = objs[i].getClass();
                        }
                        Method m = HelloService.class.getMethod(method,types);
                        Object obj = m.invoke(hello,objs);
                        try(ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
                            oos.writeObject(obj);
                            oos.flush();
                        }

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
