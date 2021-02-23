package com.yc.wowo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.wowo.bean.TypeInfo;
import com.yc.wowo.biz.ITypeInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.RequestParamUtil;

/**
 * 商品类型控制
 * company 源辰信息
 * @author navy
 * @date 2020年10月26日
 * Email haijunzhou@hnit.edu.cn
 */
@RestController
@RequestMapping("/type")
public class TypeInfoController{
	@Autowired
	private ITypeInfoBiz typeInfoBizImpl;

	@RequestMapping("/finds")
	public List<TypeInfo> finds() {
		return typeInfoBizImpl.finds();
	}

	@RequestMapping("/findByPage")
	public JsonObject findByPage(@RequestParam Map<String, Object> map) {
		return typeInfoBizImpl.findByPage(RequestParamUtil.findByPageUtil(map));
	}
	
	@RequestMapping("/update")
	public ResultDTO update(TypeInfo tf)  {
		int result = typeInfoBizImpl.update(tf);
		if (result > 0) { // 说明添加成功
			return new ResultDTO(200, "成功");
		} 
		return new ResultDTO(500, "失败");
	}

	/**
	 * 添加商品类型
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/add")
	public ResultDTO add(TypeInfo tf) {
		// 调用业务模型层，处理
		int result = typeInfoBizImpl.add(tf);
		if (result > 0) { // 说明添加成功
			return new ResultDTO(200, "成功");
		} 
		return new ResultDTO(500, "失败");
	}

	/**
	 * 查询所有商品类型信息
	 * @param request
	 * @param response
	 * @throws IOException 
	*/
	@RequestMapping("/findAll")
	public List<TypeInfo> findAll(){
		return  typeInfoBizImpl.findAll();
	} 
}
