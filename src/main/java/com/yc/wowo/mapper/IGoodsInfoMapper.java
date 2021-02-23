package com.yc.wowo.mapper;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.GoodsInfo;

public interface IGoodsInfoMapper {
	/**
	 * 后端分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<GoodsInfo> findByPage(Map<String, Object> map);
	
	/**
	 * 获取总记录数
	 * @ flag 为true查询所有商品总数，为false查询在架的商品总数
	 * @return
	 */
	public int total(boolean flag);
	
	/**
	 * 添加商品信息
	 * @param sp
	 * @return
	 */
	public int add(GoodsInfo gf);
	
	/**
	 * 修改商品信息
	 * @param sp
	 * @return
	 */
	public int update(GoodsInfo gf);
	
	/**
	 * 查询所有在架是商品信息
	 * @return
	 */
	public List<GoodsInfo> finds(Map<String, Object> map);
	
	/**
	 * 根据商品编号查询商品详细
	 * @param gid
	 * @return
	 */
	public GoodsInfo findByGid(String gid);
	
	
	public int totals(Map<String, Object> map);
	
	/**
	 * 多条件组合分页查询
	 * @param sp
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<GoodsInfo> findByCondition(Map<String, Object> map);
}
