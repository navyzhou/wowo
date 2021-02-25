package com.yc.wowo.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.wowo.bean.TypeInfo;
import com.yc.wowo.biz.ITypeInfoBiz;
import com.yc.wowo.dao.IRedisDao;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.mapper.ITypeInfoMapper;
import com.yc.wowo.util.StringUtil;

/**
 * 商品类型业务模型层的实现
 * company 源辰信息
 * @author navy
 * @date 2020年10月26日
 * Email haijunzhou@hnit.edu.cn
 */
@Service
public class TypeInfoBizImpl implements ITypeInfoBiz{
	@Autowired
	private ITypeInfoMapper typeInfoMapper;

	@Autowired
	private IRedisDao redisDao;

	@Override
	public int add(TypeInfo tf) {
		if (StringUtil.checkNull(tf.getTname())) {
			return -1;
		}
		return typeInfoMapper.add(tf);
	}

	@Override
	public int update(TypeInfo tf) {
		if (StringUtil.checkNull(tf.getTname())) {
			return -1;
		}
		return typeInfoMapper.update(tf);
	}

	@Override
	public List<TypeInfo> findAll() {
		return typeInfoMapper.findAll();
	}

	@Override
	public List<TypeInfo> finds() {
		// 从缓存服务器中获取所有类型，如果没有则查询数据库然后缓存到redis中
		Object obj = redisDao.get("types");
		
		if (obj == null) {
			List<TypeInfo> types = typeInfoMapper.finds();
			redisDao.set("types", types);
			return types;
		}
		return (List<TypeInfo>) obj;
	}

	/**
	 * 针对easyui中分页查询的，easyui有分页组件，但是必须安装这个分页组件中所有的数据格式返回数据
	 * {total:"总记录数", rows:[{}, {}]}
	 */
	@Override
	public JsonObject findByPage(Map<String , Object> map) {
		return new JsonObject(typeInfoMapper.total(), typeInfoMapper.findByPage(map));
	}
}
