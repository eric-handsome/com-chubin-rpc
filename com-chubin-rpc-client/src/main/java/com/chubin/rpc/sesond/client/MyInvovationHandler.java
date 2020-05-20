package com.chubin.rpc.sesond.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

import com.chubin.rpc.api.model.SecondRpcRequest;

public class MyInvovationHandler implements InvocationHandler {

	Socket socket;

	Class<?> interClass;

	public MyInvovationHandler(Class<?> interClass) {
		this.interClass = interClass;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		socket = new Socket("127.0.0.1", 8089);
		return SendParamsToServer(method, args, interClass.getName());
	}

	private Object SendParamsToServer(Method method, Object[] args, String className) {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			// 封装参数发给server端
			SecondRpcRequest request = new SecondRpcRequest();
			request.setClassName(className);
			request.setMethodName(method.getName());
			request.setParams(args);
			oos.writeObject(request);
			oos.flush();

			ois = new ObjectInputStream(socket.getInputStream());
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
