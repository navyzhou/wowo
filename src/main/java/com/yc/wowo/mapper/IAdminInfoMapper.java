package com.yc.wowo.mapper;

import com.yc.wowo.bean.AdminInfo;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年10月26日
 * Email haijunzhou@hnit.edu.cn
 */
public interface IAdminInfoMapper {
	/**
	 * 后台管理员登录的方法
	 * @param af
	 * @return
	 */
	public AdminInfo login(AdminInfo af);
}
