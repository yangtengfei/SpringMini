package com.tengfeiyang.common;

import java.util.List;

import org.dom4j.DocumentException;

import com.tengfeiyang.common.dixmlpro.ElementBean;
import com.tengfeiyang.common.dixmlpro.ElementProperty;

public class DIXml {
	/**
	 * 判断是否有与Action同名的bean节点
	 * @param beanList
	 * @param name
	 * @return
	 * @throws DocumentException
	 */
	public static boolean exsitSameBean(List<ElementBean> beanList, String name) throws DocumentException{
		if (null == getSameBean(beanList, name)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 获取同名与Action同名的bean节点
	 * @param beanList
	 * @param name
	 * @return
	 * @throws DocumentException
	 */
	public static ElementBean getSameBean(List<ElementBean> beanList, String name) throws DocumentException{
		for (ElementBean elementBean : beanList) {
			if (elementBean.getName().equalsIgnoreCase(name)) {
				return elementBean;
			}
		}
		return null;
	}

	/**
	 * 判断Action对应的bean是否存在依赖
	 * @param beanList
	 * @param actionName
	 * @return
	 * @throws DocumentException
	 */
	public static boolean exsitDependency(List<ElementBean> beanList,String actionName) throws DocumentException {
		if (null != getDependency(beanList, actionName)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取被依赖的类的类名
	 * @param beanList
	 * @param beanName
	 * @return
	 */
	public static String getDependency(List<ElementBean> beanList,String beanName){
		for (ElementBean elementBean : beanList) {
			if (elementBean.getName().equalsIgnoreCase(beanName)) {
				List<ElementProperty> properties = elementBean.getProperties();
				for (ElementProperty property : properties) {
					if (null == property.getRefClazz()) {
						return null;
					}
					return property.getRefClazz();
				}
			}
		}
		return null;
	}
}
