package com.yc.wowo.mapper;

import java.util.Map;

import com.yc.wowo.bean.MemberInfo;

public interface IMemberInfoMapper {
	public int reg(MemberInfo mf);
	public MemberInfo login(Map<String, Object> map);
}
