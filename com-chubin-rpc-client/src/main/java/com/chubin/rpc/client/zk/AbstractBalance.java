package com.chubin.rpc.client.zk;

import java.util.List;

public abstract class AbstractBalance implements Balance{

	public String selectNodes(List<String> nodes) {
		if(nodes==null || nodes.size()==0){
			return null;
		}else if(nodes.size()==1){
			return nodes.get(0);
		}
		return doSelect(nodes);
	}
	
	abstract String doSelect(List<String> nodes);
}
