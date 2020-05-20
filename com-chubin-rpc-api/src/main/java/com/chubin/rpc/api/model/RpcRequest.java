package com.chubin.rpc.api.model;

import java.io.Serializable;
import java.util.Arrays;

public class RpcRequest implements Serializable{
	
    private String className;
    
    private String classMethod;
    
    private Object[] params;
    
    private String version;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassMethod() {
		return classMethod;
	}

	public void setClassMethod(String classMethod) {
		this.classMethod = classMethod;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
	
	

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "RpcRequest [className=" + className + ", classMethod=" + classMethod + ", params="
				+ Arrays.toString(params) + ", version=" + version + "]";
	}

}
