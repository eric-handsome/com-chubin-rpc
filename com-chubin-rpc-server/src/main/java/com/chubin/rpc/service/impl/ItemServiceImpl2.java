package com.chubin.rpc.service.impl;

import com.chubin.rpc.api.model.ItemService;
import com.chubin.rpc.server.RpcService;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

@RpcService(value=ItemService.class,version="2")
public class ItemServiceImpl2 implements ItemService{

	public void getItem(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("我是item2");
	}

}
