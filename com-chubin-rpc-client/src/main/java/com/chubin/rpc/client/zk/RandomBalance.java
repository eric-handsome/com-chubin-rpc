package com.chubin.rpc.client.zk;

import java.util.List;
import java.util.Random;

public class RandomBalance extends AbstractBalance{

	@Override
	String doSelect(List<String> nodes) {
		// TODO Auto-generated method stub
		Random random = new Random(nodes.size());
		return nodes.get(random.nextInt());
	}
	
}
