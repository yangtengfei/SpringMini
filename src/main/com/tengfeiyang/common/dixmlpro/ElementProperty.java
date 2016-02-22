package com.tengfeiyang.common.dixmlpro;

public class ElementProperty {
	private String name;
	private String refClazz;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRefClazz() {
		return refClazz;
	}
	public void setRefClazz(String refClazz) {
		this.refClazz = refClazz;
	}
	@Override
	public String toString() {
		return "ElementProperty [name=" + name + ", refClazz=" + refClazz + "]";
	}
	
}	
