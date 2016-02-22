package com.tengfeiyang.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseText {
	static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// 解析出request中action的 name
	public static String parseAction(HttpServletRequest request) {
		String url = request.getRequestURI();
		int i = url.indexOf(".");
		int j = url.lastIndexOf("/");
		String subUrl = url.substring(j + 1, i);
		return subUrl;
	}

	// 解析 XML 文件
	public static Map<String, String> parseXML(String xmlString,
			String attributeName) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlString);
		Map<String, String> nodeKeyValue = new HashMap<String, String>();

		NodeList nodeList = document.getElementsByTagName("action-controller");
		Element element = (Element) nodeList.item(0);

		NodeList actionNodeList = element.getElementsByTagName("action");
		for (int i = 0; i < actionNodeList.getLength(); i++) {
			Element actionElement = (Element) actionNodeList.item(i);
			// 传入一个 action结点 获取其属性
			nodeKeyValue = getNoteAttribute(actionElement, attributeName,
					nodeKeyValue);
		}
		return nodeKeyValue;
	}

	// 匹配并解析action
	public static Map<String, String> getNoteAttribute(Node node,
			String attributeName, Map<String, String> map) {
		NamedNodeMap namedNodeMap = node.getAttributes(); // 获取 action 结点的属性
		for (int i = namedNodeMap.getLength() - 1; i >= 0; i--) {
			Node attributeNode = namedNodeMap.item(i); // 最后一个属性 name

			// 如果 name 对应的 action 名为 login 则获取后续的属性
			if ("name".equals(attributeNode.getNodeName())
					&& attributeName.equals(attributeNode.getFirstChild()
							.getNodeValue())) {
				for (int j = i - 1; j >= 0; j--) {
					attributeNode = namedNodeMap.item(j);
					map.put(attributeNode.getNodeName(),
							attributeNode.getNodeValue());
				}
			}
		}
		return map;
	}

	// result
	public static Map<String, String> getResult(String resultName, String xmlUrl)
			throws Exception {
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlUrl);

		Map<String, String> nodeKeyValue = new HashMap<String, String>();

		NodeList nodeList = document.getElementsByTagName("action-controller");
		Element element = (Element) nodeList.item(0);

		NodeList resultNodeList = element.getElementsByTagName("result");

		for (int i = 0; i < resultNodeList.getLength(); i++) {
			Element resultElement = (Element) resultNodeList.item(i);
			// 传入一个 action结点 获取其属性
			nodeKeyValue = getResultNoteAttribute(resultName, resultElement,
					nodeKeyValue);
		}
		return nodeKeyValue;
	}

	private static Map<String, String> getResultNoteAttribute(
			String resultNameValue, Node node, Map<String, String> map) {
		NamedNodeMap namedNodeMap = node.getAttributes(); // 获取 action 结点的属性
		for (int i = 0; i < namedNodeMap.getLength(); i++) {
			Node attributeNode = namedNodeMap.item(i); // 最后一个属性 name
			// 如果 name 对应的 resultName
			if (resultNameValue.equals(attributeNode.getNodeValue())) {
				// 获取属性节点
				for (int j = i+1; j < namedNodeMap.getLength(); j++) {
					attributeNode = namedNodeMap.item(j);
					map.put(attributeNode.getNodeName(),
							attributeNode.getNodeValue());
				}
				// 获取文本节点
				map.put("textNode",node.getFirstChild().getNodeValue());
			}
		}
		return map;
	}
}
