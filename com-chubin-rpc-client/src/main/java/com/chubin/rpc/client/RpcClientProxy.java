package com.chubin.rpc.client;

import java.lang.reflect.Proxy;
import java.net.Socket;

import com.chubin.rpc.api.model.UserService;

public class RpcClientProxy {

	public static  <T> T getProxy(Class<T> class1,Socket socket,String version) {
		return (T)Proxy.newProxyInstance(class1.getClassLoader(), new Class[]{class1}, new RemoteInvoke(class1,socket,version));
	}

}
