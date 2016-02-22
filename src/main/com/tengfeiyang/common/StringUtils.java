package com.tengfeiyang.common;

import java.lang.reflect.Method;



public class StringUtils {

	// 检查一个字符串是否为空
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}
	
	/**
	 * 反射调用方法，运行输出结果
	 * @param objclass	newInstance的新类
	 * @param obj		操作的方法名
	 * @param value		参数的值
	 * @param type		参数的属性
	 * @return 
	 */
	public static Object login(Object objclass, Object obj, Object value, Class<?> type){
		try {
			Method method = objclass.getClass().getMethod("login", type);
			return method.invoke(objclass, value);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 反射设置UserBean属性
	 * @param obj		newInstance的新类	
	 * @param param		参数属性
	 * @param value		参数的值
	 */
	public static void setData(Object obj, String param, String value) {
		String firstLetter = param.substring(0, 1).toUpperCase();
		try {
			Method m = obj.getClass().getMethod("set" + firstLetter + param.substring(1), String.class);
			m.invoke(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
