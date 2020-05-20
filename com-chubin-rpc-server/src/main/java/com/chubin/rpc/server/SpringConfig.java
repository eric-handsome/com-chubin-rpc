package com.chubin.rpc.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.chubin")
public class SpringConfig {

	@Bean("rpcServer")
	public RpcServer getRpcServer(){
		return new RpcServer();
	}
}
