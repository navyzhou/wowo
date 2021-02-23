package com.yc.wowo.mapper;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.TypeInfo;

/**
 * 商品类型数据模型层接口
 * company 源辰信息
 * @author navy
 * @date 2020年10月26日
 * Email haijunzhou@hnit.edu.cn
 */
public interface ITypeInfoMapper {
	/**
	 * 添加商品类型信息
	 * @param tf
	 * @return
	 */
	public int add(TypeInfo tf);
	
	/**
	 * 修改商品类型信息
	 * @param tf
	 * @return
	 */
	public int update(TypeInfo tf);
	
	/**
	 * 查询所有商品类型信息
	 * @return
	 */
	public List<TypeInfo> findAll();
	
	/**
	 * 获取未下架的商品类型信息
	 * @return
	 */
	public List<TypeInfo> finds();
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int total();
	
	/**
	 * 分页查询
	 * @param page 查询第几页
	 * @param rows 每页显示多少行
	 * @return
	 */
	public List<TypeInfo> findByPage(Map<String, Object> map);
}
