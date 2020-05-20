package com.chubin.rpc.server;

import java.applet.AppletContext;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.chubin.rpc.api.model.PublishZookeeper;

@Component
public class RpcServer implements ApplicationContextAware, InitializingBean {

	static ExecutorService pool = Executors.newCachedThreadPool();

	private int port;

	Map<String, Object> handleMap = new HashMap<String, Object>();

	public void afterPropertiesSet() throws Exception {
		try {
			ServerSocket serverSocket = new ServerSocket(8081);
			for (;;) {
				Socket socket = serverSocket.accept();
				ProcessorHandler process = new ProcessorHandler(socket, handleMap);
				pool.execute(process);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
		if (!serviceBeanMap.isEmpty()) {
			for (Object serviceBean : serviceBeanMap.values()) {
				PublishZookeeper  publish = new PublishZookeeper();
				// 拿到注解
				RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
				String serviceName = rpcService.value().getName();
				if(null!=rpcService.version() && !"".equals(rpcService.version())){
					serviceName =  serviceName + "-" + rpcService.version();
					publish.createNode(serviceName);
				}else{
					publish.createNode(serviceName);
				}
				handleMap.put(serviceName, serviceBean);
			}
		}
		
	}

}
