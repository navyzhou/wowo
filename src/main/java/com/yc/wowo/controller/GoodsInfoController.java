package com.yc.wowo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yc.wowo.bean.GoodsInfo;
import com.yc.wowo.biz.IGoodsInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.RequestParamUtil;

@RestController
@RequestMapping("/goods")
public class GoodsInfoController{
	@Autowired
	private IGoodsInfoBiz goodsInfoBizImpl;
	
	@Value("${web.upload-path}")
	private String uploadPath;

	@RequestMapping("/findByFirst")
	public Map<String,Object> findByFirst(@RequestParam Map<String, Object> map)  {
		// 要返回第一页的数据以及总记录数
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("total", goodsInfoBizImpl.total());
		result.put("rows", goodsInfoBizImpl.finds(RequestParamUtil.findByPageUtil(map)));
		return result;
	}

	@RequestMapping("/finds")
	public List<GoodsInfo> finds(@RequestParam Map<String, Object> map) throws IOException {
		return goodsInfoBizImpl.finds(RequestParamUtil.findByPageUtil(map));
	}

	@RequestMapping("/upload")
	public Map<String, Object> upload(MultipartFile upload, HttpServletRequest request) {
		String path = "wowo_pics";
		
		String savePath = "";
		File dest = null;
		Map<String, Object> result = new HashMap<String, Object>();

		if (upload != null && upload.getSize() > 0) {
			try {
				savePath = path + "/" + new Date().getTime() + "_" + upload.getOriginalFilename();
				dest = new File(uploadPath, savePath);
				upload.transferTo(dest);

				result.put("filename", upload.getOriginalFilename());
				result.put("url", "../../../" + savePath);
				result.put("uploaded", 1);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@RequestMapping("/findByGid")
	public ResultDTO findByGid(String gid){
		GoodsInfo goodsInfo = goodsInfoBizImpl.findByGid(gid);
		if (goodsInfo == null) {
			return new ResultDTO(500, "查无此商品");
		}
		return new ResultDTO(200, goodsInfo);
	}

	@RequestMapping("/findCondition")
	public JsonObject findCondition(@RequestParam Map<String, Object> map) {
		return goodsInfoBizImpl.findByCondition(RequestParamUtil.findByPageUtil(map));
	}

	@RequestMapping("/add")
	public ResultDTO add(GoodsInfo gf, MultipartFile[] goods_pics, HttpServletRequest request) {
		String path = "wowo_pics";

		String savePath = "";
		File dest = null;

		if (goods_pics != null && goods_pics.length > 0 && goods_pics[0].getSize() > 0) {
			String picStr = "";
			try {
				for (MultipartFile pic : goods_pics) {
					savePath = path + "/" + new Date().getTime() + "_" + pic.getOriginalFilename();
					dest = new File(uploadPath, savePath);
					pic.transferTo(dest);
					if ("".equals(picStr)) {
						picStr += "../" + savePath;
					} else {
						picStr += ";../" + savePath;
					}
				}
				gf.setPics(picStr);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int result = goodsInfoBizImpl.add(gf);
		if (result > 0) {
			return new ResultDTO(200, "成功");
		} 
		return new ResultDTO( 500, "失败");
	}

	@RequestMapping("/findByPage")
	public JsonObject findByPage(@RequestParam Map<String, Object> map){
		return goodsInfoBizImpl.findByPage(RequestParamUtil.findByPageUtil(map));
	}
}
