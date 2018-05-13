package yrpc.server;

import yrpc.common.IHello;

/**
 * @author yudungang
 */
public class HelloService implements IHello{

    @Override
    public String sayHello(String info) {
        String result = "hello ," + info;
        System.out.println(result);
        return result;
    }
}
