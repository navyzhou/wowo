package com.yc.wowo.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.yc.wowo.util.StringUtil;

/**
 * 当应用程序已启动，我们就监听创建文件上传的目录
 * company 源辰信息
 * @author navy
 * @date 2020年11月4日
 * Email haijunzhou@hnit.edu.cn
 */
@WebListener
public class CreateUploadPathListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String path = sce.getServletContext().getInitParameter("uploadPath");
		if (StringUtil.checkNull(path)) {
			path = "images";
		}
		
		String basePath = sce.getServletContext().getRealPath("/"); // 获取Tomcat在服务器中的绝对路径
		path = "../" + path;
		File fl = new File(basePath, path);
		
		if (!fl.exists()) {
			fl.mkdirs();
		}
	}
}
