package com.tengfeiyang.service;

import com.tengfeiyang.model.UserBean;

public class UserService {
	public boolean login(UserBean userBean) {
		if ("Yangtengfei".equals(userBean.getUserName())
				&& "Yangtengfei".equals(userBean.getPassword())) {
			return true;
		} else {
			return false;
		}
	}
}
