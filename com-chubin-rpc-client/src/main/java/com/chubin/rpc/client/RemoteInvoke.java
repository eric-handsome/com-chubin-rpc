package com.chubin.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import com.chubin.rpc.api.model.RpcRequest;

public class RemoteInvoke implements InvocationHandler{

	private Class clazz;
	
	private Socket socket;
	
	private String version;
	
	public RemoteInvoke(Class clazz,Socket socket,String version) {
		super();
		this.clazz = clazz;
		this.socket = socket;
		this.version = version;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//封装数据
		RpcRequest request = new RpcRequest();
		request.setClassName(clazz.getName());
		request.setClassMethod(method.getName());
		request.setParams(args);
		request.setVersion(version);
		return invoker(request);
	}

	private Object invoker(RpcRequest request) {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try{
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(request);
			//获取返回数据
			ois = new ObjectInputStream(socket.getInputStream());
			return ois.readObject();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
