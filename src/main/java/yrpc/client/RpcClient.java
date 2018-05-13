package yrpc.client;

import yrpc.common.IHello;
import yrpc.server.HelloService;

import java.io.IOException;

/**
 * @author yudungang
 */
public class RpcClient {

    public static void main(String[] args) throws IOException {
        RpcProxyClient<HelloService> rpcClient = new RpcProxyClient<>();

        IHello iHello = rpcClient.proxyClient(HelloService.class);

        String result = iHello.sayHello("rpc");
        System.out.println(result);
    }

}
