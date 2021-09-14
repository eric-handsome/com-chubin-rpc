package com.chubin.rpc.second.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer {
	
	ExecutorService executors = Executors.newCachedThreadPool();
	
	ServerSocket socket =null;

	public RpcServer() {
		
	}
	
	public void publish() {
		try{
			socket = new ServerSocket(8089);
	        while(true){
	        	Socket accept = socket.accept();
	        	executors.execute(new ServerThread(accept)
				);
	        }	
		}catch(Exception e){
			e.printStackTrace();
		}	
	}

	
   
}
