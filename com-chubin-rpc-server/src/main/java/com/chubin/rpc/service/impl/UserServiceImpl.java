package com.chubin.rpc.service.impl;

import com.chubin.rpc.api.model.User;
import com.chubin.rpc.api.model.UserService;
import com.chubin.rpc.server.RpcService;

@RpcService(UserService.class)
public class UserServiceImpl implements UserService{

	public String sayHello(String str) {
		// TODO Auto-generated method stub
		return "hello"+str;
	}

	public String getUser(User user) {
		// TODO Auto-generated method stub
		return user.getName();
	}

}
