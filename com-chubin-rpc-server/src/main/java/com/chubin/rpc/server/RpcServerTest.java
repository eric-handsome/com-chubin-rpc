package com.chubin.rpc.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.chubin.rpc.api.model.UserService;
import com.chubin.rpc.service.impl.UserServiceImpl;

public class RpcServerTest {

	public static void main(String[] args) {
		
		/*UserService userService = new UserServiceImpl();
		
		//PublishRpcServer publish = new PublishRpcServer();
		
	    PublishRpcServer.publish(userService,8081);*/
		
		ApplicationContext context 
		   = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		((AnnotationConfigApplicationContext)context).start();
		System.out.println(111);
		
		
	}
	
}
