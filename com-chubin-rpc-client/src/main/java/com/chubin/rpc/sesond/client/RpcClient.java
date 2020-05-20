package com.chubin.rpc.sesond.client;

import java.lang.reflect.Proxy;

public class RpcClient {

	public <T> T getInstance(Class<T> interClass) {
		// TODO Auto-generated method stub
		return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{interClass}, new MyInvovationHandler(interClass));
	}

	 
}
