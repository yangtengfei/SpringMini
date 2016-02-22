package com.tengfeiyang.common.dixmlpro;

import java.util.List;

public class ElementBean {
	private String name;
	private String clazz;
	private List<ElementProperty> properties;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public List<ElementProperty> getProperties() {
		return properties;
	}
	public void setProperties(List<ElementProperty> properties) {
		this.properties = properties;
	}
	@Override
	public String toString() {
		return "ElementBean [name=" + name + ", clazz=" + clazz
				+ ", properties=" + properties + "]";
	}
	
	
}
