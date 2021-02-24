package com.yc.wowo.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.wowo.bean.AdminInfo;
import com.yc.wowo.biz.IAdminInfoBiz;
import com.yc.wowo.mapper.IAdminInfoMapper;
import com.yc.wowo.util.StringUtil;

/**
 * 源辰信息
 * @author navy
 * @data 2021年2月24日
 * @Email haijunzhou@hnit.edu.cn
 */
@Service
public class AdminInfoBizImpl implements IAdminInfoBiz{
	@Autowired
	private IAdminInfoMapper adminInfoMapper;

	@Override
	public AdminInfo login(AdminInfo af) {
		if (StringUtil.checkNull(af.getAname(), af.getPwd())) {
			return null;
		}
		return adminInfoMapper.login(af);
	}
}
