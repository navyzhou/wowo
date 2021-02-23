package com.yc.wowo.util;

/**
 * 字符串工具类
 * company 源辰信息
 * @author navy
 * @date 2020年10月25日
 * Email haijunzhou@hnit.edu.cn
 */
public class StringUtil {
	/**
	 * 空判断
	 * @param strs
	 * @return
	 */
	public static boolean checkNull(String ... strs) {
		if (strs == null || strs.length <= 0) {
			return true;
		}
		
		for (String str : strs) {
			if (str == null || "".equals(str)) {
				return true;
			}
		}
		return false;
	}
}
