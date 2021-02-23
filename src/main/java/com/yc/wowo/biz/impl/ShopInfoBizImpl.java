package com.yc.wowo.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.wowo.bean.ShopInfo;
import com.yc.wowo.biz.IShopInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.mapper.IShopInfoMapper;
import com.yc.wowo.util.StringUtil;

@Service
public class ShopInfoBizImpl implements IShopInfoBiz {
	@Autowired
	private IShopInfoMapper shopInfoMapper;
	
	@Override
	public JsonObject findByPage(Map<String, Object> map) {
		return new JsonObject(shopInfoMapper.total(), shopInfoMapper.findByPage(map));
	}

	@Override
	public int add(ShopInfo sp) {
		if (StringUtil.checkNull(sp.getSname(), sp.getLicense(), sp.getEndHours(), sp.getStartHours(), sp.getPics(), sp.getTel())) {
			return -1;
		}
		return shopInfoMapper.add(sp);
	}

	@Override
	public int update(ShopInfo sp) {
		if (StringUtil.checkNull(sp.getSname(), sp.getLicense(), sp.getEndHours(), sp.getStartHours(), sp.getPics(), sp.getTel())) {
			return -1;
		}
		return shopInfoMapper.update(sp);
	}

	@Override
	public List<ShopInfo> finds() {
		return shopInfoMapper.finds();
	}

	@Override
	public ShopInfo findBySid(String sid) {
		return shopInfoMapper.findBySid(sid);
	}

	@Override
	public JsonObject findByCondition(Map<String, Object> map) {
		return new JsonObject(shopInfoMapper.totals(map), shopInfoMapper.findByCondition(map));
	}
}
