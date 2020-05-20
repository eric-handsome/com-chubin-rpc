package com.chubin.rpc.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.chubin.rpc.api.model.UserService;


public class PublishRpcServer {
    
	static ExecutorService pool = Executors.newCachedThreadPool();
	
	public static void publish(UserService userService, int port) {
		
		try{
			
			ServerSocket serverSocket = new ServerSocket(8081);
			
			for(;;){
				
				Socket socket = serverSocket.accept();
				
//                ProcessorHandler process = new ProcessorHandler(socket,userService);
//                
//                pool.execute(process);
                
			} 
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
