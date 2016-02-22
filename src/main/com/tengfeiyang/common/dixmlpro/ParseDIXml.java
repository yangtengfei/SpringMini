package com.tengfeiyang.common.dixmlpro;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseDIXml {
	/**
	 * 解析Action
	 * 
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static List<ElementBean> parseAction() throws DocumentException {
		// 读取文档
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read("E:/WorkSpace/workSpace-luna/SimpleController_02_07/src/resource/di.xml");
		// 获取根元素
		Element root = document.getRootElement();

		List<Element> beans = root.elements();
		List<ElementBean> beanList = new ArrayList<ElementBean>();

		for (Element element : beans) {
			beanList.add(parseBean(element));
		}
		return beanList;
	}

	/**
	 * 解析Bean
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static ElementBean parseBean(Element element) {
		List<Element> nodes = element.elements();
		ElementBean elementBean = new ElementBean();
		elementBean.setProperties(new ArrayList<ElementProperty>());
		for (Element node : nodes) {
			switch (node.getName()) {
			case "name":
				elementBean.setName(node.getTextTrim());
				break;
			case "class":
				elementBean.setClazz(node.getTextTrim());
				break;
			case "property":
				parseProperty(elementBean, node);
				break;
			default:
				break;
			}
		}
		return elementBean;
	}

	/**
	 * 解析property
	 * 
	 * @param elementBean
	 * @param node
	 */
	@SuppressWarnings("unchecked")
	private static void parseProperty(ElementBean elementBean, Element node) {
		List<Element> elements = node.elements();
		ElementProperty property = new ElementProperty();
		for (Element element : elements) {
			switch (element.getName()) {
			case "name":
				property.setName(element.getTextTrim());
				break;
			case "ref-class":
				property.setRefClazz(element.getTextTrim());
				break;
			default:
				break;
			}
		}
		elementBean.getProperties().add(property);
	}
}
