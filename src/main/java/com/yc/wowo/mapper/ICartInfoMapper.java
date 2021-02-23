package com.yc.wowo.mapper;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.CartInfo;

public interface ICartInfoMapper {
	public int add(CartInfo cf);
	
	public List<CartInfo> findByMid(Integer mid); 
	
	public int update(Map<String, Object> map);
	
	public int del(String cid);
}
