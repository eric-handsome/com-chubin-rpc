package com.chubin.rpc.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.chubin.rpc.api.model.ItemService;
import com.chubin.rpc.api.model.PublishZookeeper;
import com.chubin.rpc.api.model.UserService;
import com.chubin.rpc.client.zk.Balance;
import com.chubin.rpc.client.zk.RandomBalance;

public class RpcClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		
		ApplicationContext context = 
				 new AnnotationConfigApplicationContext(SpringConfig.class);
		
		RpcClientProxy rpcClientProxy = context.getBean(RpcClientProxy.class);
		
     //   UserService userService = RpcClientProxy.getProxy(UserService.class,new Socket("127.0.0.1", 8081));
		
		PublishZookeeper publish = new PublishZookeeper();
		
		List<String> childNodes = publish.getChildNodes("/name/"+ItemService.class.getName());
		
		Balance balance = new RandomBalance();
		
		String selectNodes = balance.selectNodes(childNodes);
		
		ItemService itemService = RpcClientProxy.getProxy(ItemService.class,new Socket(selectNodes, 8081), "1");
		
		ItemService itemService2 = RpcClientProxy.getProxy(ItemService.class,new Socket(selectNodes, 8081), "2");
		
		itemService.getItem(1);
		
		itemService2.getItem(2);
		
	}
}
