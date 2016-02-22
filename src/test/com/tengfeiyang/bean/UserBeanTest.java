package com.tengfeiyang.bean;

import com.tengfeiyang.action.LoginAction;
import com.tengfeiyang.model.UserBean;

public class UserBeanTest {
	public static void main(String[] args) throws Exception {
//		Class<?> actionClass = Class.forName("com.tengfeiyang.action.LoginAction");
//		Object obj = actionClass.newInstance();
		LoginAction loginAction = new LoginAction();
		
		UserBean userBean = new UserBean();
		userBean.setUserName("Yangtengfei");
		userBean.setPassword("Yangtengfei");
		
		
		
		loginAction.setUserBean(userBean);
		
		System.out.println(loginAction.getUserBean().toString());
		
//		System.out.println(LoginAction.login(obj, "login", userBean, UserBean.class));
		
//		UserBean.setData(obj, param, value);
	}
}
