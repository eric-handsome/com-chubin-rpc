package com.chubin.rpc.second.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.chubin.rpc.api.model.SecondRpcRequest;

public class ServerThread implements Runnable {
	Socket accept;

	public ServerThread(Socket accept) {
		this.accept = accept;
	}

	public void run() {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		SecondRpcRequest secondRpcRequest = null;

		try {
			ois = new ObjectInputStream(accept.getInputStream());
			//获取请求参数
			Object readObject = ois.readObject();
			if(readObject instanceof SecondRpcRequest){
				secondRpcRequest = (SecondRpcRequest) readObject;
			}
			String methodName = secondRpcRequest.getMethodName();
			Object[] params = secondRpcRequest.getParams();
			String className = secondRpcRequest.getClassName();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
