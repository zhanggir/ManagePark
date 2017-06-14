package com.wislight.parkmanage.utils;

import java.util.List;

public class Util {

	/**
	 * 判断String列的值为空
	 * 
	 * @param params
	 * @return
	 */
	public static boolean isNull(String... params) {
		for (String s : params) {
			if (isNull(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断对象是否为空
	 * 
	 * @param object
	 * @return 空 返回true
	 */
	public static boolean isNull(Object object) {
		if (null == object) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否为空的
	 * 
	 * @param str
	 * @return 为空则返回true
	 */
	public static boolean isNull(String str) {

		if (str == null) {
			return true;
		}
		if (str.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断list集合为空 和size 是否为0
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isNull(List<?> list) {

		if (list == null) {
			return true;
		}
		if (list.size() < 1) {
			return true;
		}
		return false;
	}

}
