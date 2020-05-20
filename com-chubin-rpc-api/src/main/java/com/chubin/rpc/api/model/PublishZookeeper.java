package com.chubin.rpc.api.model;

import java.net.InetAddress;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class PublishZookeeper {

	private CuratorFramework client;
	
	
	public PublishZookeeper() {
		
		client = CuratorFrameworkFactory.builder()
					//	.authorization("digest","admin:123".getBytes())   //创建客户端的时候授权
			            .connectString("106.12.12.39:2181")
			            .sessionTimeoutMs(50000)
			            .connectionTimeoutMs(30000)
			            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
		client.start();
	}
	
	//创建node
	public void createNode(String node){
		
		try {
			if(null != client.checkExists().forPath("/name/"+node)){
				client.create().withMode(CreateMode.EPHEMERAL).forPath("/name/"+node+"/"+InetAddress.getLocalHost().getHostAddress());
			}else{
				client.create().withMode(CreateMode.PERSISTENT).forPath("/name/"+node);
				client.create().withMode(CreateMode.EPHEMERAL).forPath("/name/"+node+"/"+InetAddress.getLocalHost().getHostAddress());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getChildNodes(String node){
		 try {
			 return client.getChildren().forPath(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
