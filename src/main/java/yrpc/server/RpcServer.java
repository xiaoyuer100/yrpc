package yrpc.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yudungang
 */
public class RpcServer {

    //发布服务
    public static void main(String[] args) throws IOException {
        RpcProxyServer server = new RpcProxyServer();
        server.publisherServer(8000);

    }

}
