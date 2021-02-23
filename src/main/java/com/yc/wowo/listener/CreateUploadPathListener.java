package com.yc.wowo.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 当应用程序已启动，我们就监听创建文件上传的目录
 * company 源辰信息
 * @author navy
 * @date 2020年11月4日
 * Email haijunzhou@hnit.edu.cn
 */
@Component
@WebListener
public class CreateUploadPathListener implements ServletContextListener{
	@Value("${web.upload-path}")
	private String uploadPath;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		File fl = new File(uploadPath, "wowo_pics");
		
		if (!fl.exists()) {
			fl.mkdirs();
		}
	}
}
