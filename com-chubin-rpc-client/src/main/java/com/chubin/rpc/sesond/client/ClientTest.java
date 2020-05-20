package com.chubin.rpc.sesond.client;

import com.chubin.rpc.api.model.User;
import com.chubin.rpc.api.model.UserService;

public class ClientTest {

	public static void main(String[] args) {
		
		 RpcClient rpc = new RpcClient();
		 UserService userService = rpc.getInstance(UserService.class);
		 String user = userService.getUser(null);
		 System.out.println(user);
	}
}
