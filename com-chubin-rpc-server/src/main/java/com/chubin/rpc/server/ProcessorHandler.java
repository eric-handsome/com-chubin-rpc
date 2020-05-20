package com.chubin.rpc.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.chubin.rpc.api.model.RpcRequest;

public class ProcessorHandler implements Runnable {

	Map<String, Object> handleMap;

	public Socket socket;

	public ProcessorHandler(Socket socket, Map<String, Object> handleMap) {
		super();
		this.handleMap = handleMap;
		this.socket = socket;
	}

	public void run() {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			RpcRequest request = (RpcRequest) ois.readObject();
			Object response = processResult(request);
			// 返回对象
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Object processResult(RpcRequest request) {
		// TODO Auto-generated method stub
		Object object = handleMap.get(request.getClassName() + "-" + request.getVersion());
		if (object == null) {
			throw new RuntimeException("service not found" + request.getClassName());
		}
		Object[] params = request.getParams();

		try {

			if (params == null || params.length == 0) {
				Class<?> forName = Class.forName(request.getClassName());
				Method method = forName.getMethod(request.getClassMethod());
				return method.invoke(object, params);
			}
			Class[] clazz = new Class[params.length];
			for (int i = 0; i < params.length; i++) {
				clazz[i] = params[i].getClass();
			}

			Class<?> forName = Class.forName(request.getClassName());
			// Object newInstance = forName.newInstance();
			// System.out.println(newInstance);
			Method method = forName.getMethod(request.getClassMethod(), clazz);
			return method.invoke(object, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
