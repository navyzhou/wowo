package com.yc.wowo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan  // 解析 @WebServlet、@@WebListener、@WebFilter
@MapperScan("com.yc.wowo.mapper") // 配置mybatis映射文件对应的接口所在的包路径
@SpringBootApplication
public class SpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
