package com.tengfeiyang.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.tengfeiyang.common.DIXml;
import com.tengfeiyang.common.ParseText;
import com.tengfeiyang.common.StringUtils;
import com.tengfeiyang.common.dixmlpro.ElementBean;
import com.tengfeiyang.common.dixmlpro.ParseDIXml;
import com.tengfeiyang.model.UserBean;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private List<ElementBean> beanList = null;
	
	public void init() throws ServletException {
		try {
			beanList = ParseDIXml.parseAction();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 设置编码格式
		request.setCharacterEncoding("utf-8");

		String userName = request.getParameter("userName");
		String passWord = request.getParameter("password");

		// 获取URL 解析 action /SimpleController_02/login.action
		String actionName = ParseText.parseAction(request);
		String xmlUrl = "E:/WorkSpace/workSpace-luna/SimpleController_02/src/resource/controller.xml";
		try {
			// 检查对应name的 action，如果有返回 相应的结点属性
			Map<String, String> map = ParseText.parseXML(xmlUrl, actionName);
			if (null != map) {

				// 调用方法
				String methodName = map.get("method");
				if (actionName.equals(methodName)) {
					String className = null;
					Class<?> actionClass = null;
					Object objAction = null;
					String resultName = null;
					
					// 方法名类名是否相同
					if (DIXml.exsitSameBean(beanList, actionName)) {
						className = DIXml.getSameBean(beanList, actionName).getClazz();
						actionClass = Class.forName(className);
						objAction = actionClass.newInstance();
						
						// 是否存在依赖
						if (DIXml.exsitDependency(beanList, actionName)) {
							String refClassName = DIXml.getDependency(beanList, actionName);
							ElementBean refBean = DIXml.getSameBean(beanList, refClassName);
							
							className = refBean.getClazz();
							Class<?> refClass = Class.forName(className);
							Object objBean = refClass.newInstance();
							
							StringUtils.setData(objBean, "userName", userName);
							StringUtils.setData(objBean, "password", passWord);
							
							// set依赖注入
							Method setMethod = actionClass.getMethod("setUserBean", new Class[]{UserBean.class});
							setMethod.invoke(objAction, objBean);
							Method loginMethod = actionClass.getMethod("login");
							resultName = loginMethod.invoke(objAction).toString();
							
							// 依赖注入2
//							resultName = StringUtils.login(objAction, actionName, objBean, UserBean.class);
						}else {
							UserBean userBean = new UserBean();
							userBean.setUserName(userName);
							userBean.setPassword(passWord);
							
							// set依赖注入
							Method setMethod = actionClass.getMethod("setUserBean", new Class[]{UserBean.class});
							setMethod.invoke(objAction, userBean);
							Method loginMethod = actionClass.getMethod("login");
							resultName = loginMethod.invoke(objAction).toString();
//							resultName = StringUtils.login(objAction, actionName, userBean, UserBean.class);
						}
					} else {
						// 反射获取类
						className = map.get("class");
						actionClass = Class.forName(className);
						objAction = actionClass.newInstance();
						
						
						UserBean userBean = new UserBean();
						userBean.setUserName(userName);
						userBean.setPassword(passWord);
						
						// set依赖注入
						Method setMethod = actionClass.getMethod("setUserBean", new Class[]{UserBean.class});
						setMethod.invoke(objAction, userBean);
						Method loginMethod = actionClass.getMethod("login");
						resultName = loginMethod.invoke(objAction).toString();
//						resultName = StringUtils.login(objAction, actionName, userBean, UserBean.class);
					}
					
					
					Map<String, String> resultMap = ParseText.getResult(resultName, xmlUrl);

					// 选择返回页面
					if (resultMap == null) {
						System.out.println("没有请求的资源");
						request.getRequestDispatcher(
								"fail.jsp?error=" + "没有请求的资源").forward(request,
								response);
						return;
					} else {
						String returnType = resultMap.get("type");
						String returnPage = resultMap.get("textNode");
						if ("redirect".equals(returnType)) {
							response.sendRedirect(returnPage);
						}
						if ("forward".equals(returnType)) {
							RequestDispatcher rd = request
									.getRequestDispatcher(returnPage);
							rd.forward(request, response);
						}
					}
				}
			} else {
				System.out.println("不可识别的action请求");
				request.getRequestDispatcher("fail.jsp?error=" + "没有请求的资源")
						.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
