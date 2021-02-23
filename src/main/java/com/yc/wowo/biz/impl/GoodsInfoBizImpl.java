package com.yc.wowo.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.wowo.bean.GoodsInfo;
import com.yc.wowo.biz.IGoodsInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.mapper.IGoodsInfoMapper;
import com.yc.wowo.util.StringUtil;

@Service
public class GoodsInfoBizImpl implements IGoodsInfoBiz {
	@Autowired
	private IGoodsInfoMapper goodsInfoMapper;
	
	@Override
	public int add(GoodsInfo gf) {
		if (StringUtil.checkNull(gf.getGname(), String.valueOf(gf.getPrice()), String.valueOf(gf.getRebate()), gf.getSdate(), gf.getEdate())) {
			return -1;
		}
		return goodsInfoMapper.add(gf);
	}

	@Override
	public int update(GoodsInfo gf) {
		if (StringUtil.checkNull(gf.getSdate(), gf.getEdate())) {
			return -1;
		}
		return goodsInfoMapper.update(gf);
	}

	@Override
	public GoodsInfo findByGid(String gid) {
		return goodsInfoMapper.findByGid(gid);
	}

	@Override
	public JsonObject findByPage(Map<String, Object> map) {
		return new JsonObject(goodsInfoMapper.total(true), goodsInfoMapper.findByPage(map));
	}

	@Override
	public List<GoodsInfo> finds(Map<String, Object> map) {
		return goodsInfoMapper.finds(map);
	}

	@Override
	public int total() {
		return goodsInfoMapper.total(false);
	}

	@Override
	public JsonObject findByCondition(Map<String, Object> map) {
		return new JsonObject(goodsInfoMapper.totals(map), goodsInfoMapper.findByCondition(map));
	}
}
