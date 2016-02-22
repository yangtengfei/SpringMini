package com.tengfeiyang.common;

import java.util.List;

import org.dom4j.DocumentException;

import com.tengfeiyang.common.dixmlpro.ElementBean;
import com.tengfeiyang.common.dixmlpro.ParseDIXml;

public class ParseDIXmlTest {
	public static void main(String[] args) throws Exception {
		List<ElementBean> beanList = ParseDIXml.parseAction();
		System.out.println(beanList);
		System.out.println(DIXml.getSameBean(beanList, "login"));
		DIXml.getDependency(beanList, "login");
		System.out.println(DIXml.getDependency(beanList, "login"));
		
	}
	
	public boolean containsBean(String name) throws DocumentException{
		List<ElementBean> beanList = ParseDIXml.parseAction();
		for (ElementBean elementBean : beanList) {
			if (elementBean.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
}
