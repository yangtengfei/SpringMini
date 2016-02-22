package com.tengfeiyang.action;

import com.tengfeiyang.model.UserBean;
import com.tengfeiyang.service.UserService;

public class LoginAction {

	private UserService userService = new UserService();
	private UserBean userBean;

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String login() {
		if (userService.login(userBean)) {
			return "success";
		} else {
			return "fail";
		}
	}

}
