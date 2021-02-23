package com.yc.wowo.biz;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.CartInfo;

public interface ICartInfoBiz {
	public int add(Map<String, Object> map);
	
	public List<CartInfo> findByMid(Integer mid); 
	
	public int update(Map<String, Object> map);
	
	public int del(String cid);
}
