package com.yc.wowo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.wowo.bean.AdminInfo;
import com.yc.wowo.biz.IAdminInfoBiz;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.SessionKeyConstant;
import com.yc.wowo.websocket.WebServerSocket;

@RestController // @Controller + @ResponseBody
@RequestMapping("/admin")
public class AdminInfoController {
	@Autowired
	private IAdminInfoBiz adminInfoBiz;

	@PostMapping("/login")
	public ResultDTO login(AdminInfo af, HttpSession session) {
		AdminInfo adminInfo = adminInfoBiz.login(af);
		if (adminInfo == null) {
			return new ResultDTO(500, "账号或密码错误");
		}
		
		session.setAttribute(SessionKeyConstant.ADMINLOGIN, adminInfo);
		
		// 如果登录成功，则判断当前用户有没有在其他地方登录
		WebServerSocket wss = WebServerSocket.getWebSocket(String.valueOf(adminInfo.getAid()));
		if (wss != null) { // 说明已经在其他地方登录，则发送一个挤退信息
			wss.sendMessage("101");
		}
		return new ResultDTO(200, "成功");
	}
	
	@GetMapping("check")
	public ResultDTO checkLogin(HttpSession session) {
		Object obj = session.getAttribute(SessionKeyConstant.ADMINLOGIN);
		
		if (obj == null) {
			return new ResultDTO(500, "未登录");
		}
		
		return new ResultDTO(200, "已登录", obj);
	}
}