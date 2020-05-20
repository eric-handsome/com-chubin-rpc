package com.chubin.rpc.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	@Bean(name="rpcProxyClient")
	public RpcClientProxy proxyClient(){
		  return new RpcClientProxy();
	}
	
}
