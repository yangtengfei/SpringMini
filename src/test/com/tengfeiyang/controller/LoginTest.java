package com.tengfeiyang.controller;

import java.lang.reflect.Method;

import com.tengfeiyang.model.UserBean;


public class LoginTest {
	public static void main(String[] args) throws Exception {
		String className = "com.tengfeiyang.action.LoginAction";
		Class<?> actionClass = Class.forName(className);
		Object objAction = actionClass.newInstance();
		
		UserBean objBean = new UserBean();
		objBean.setUserName("Yangtengfei");
		objBean.setPassword("Yangtengfei");
		
		Method setMethod = actionClass.getMethod("setUserBean", new Class[]{UserBean.class});
		setMethod.invoke(objAction, objBean);
		Method loginMethod = actionClass.getMethod("login");
		String string = loginMethod.invoke(objAction).toString();
		System.out.println(string);
	}
}
