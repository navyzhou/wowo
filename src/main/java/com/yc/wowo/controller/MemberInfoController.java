package com.yc.wowo.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.wowo.bean.MemberInfo;
import com.yc.wowo.biz.IMemberInfoBiz;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.SendMailUtil;
import com.yc.wowo.util.SessionKeyConstant;

/**
 * 商品类型控制
 * company 源辰信息
 * @author navy
 * @date 2020年10月26日
 * Email haijunzhou@hnit.edu.cn
 */
@RestController
@RequestMapping("/member")
public class MemberInfoController {
	@Autowired
	private SendMailUtil sendEmialUtil;
	
	@Autowired
	private IMemberInfoBiz memberInfoBizImpl;
	
	
	@RequestMapping("/check")
	public ResultDTO check(HttpSession session) throws IOException {
		Object obj = session.getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		if (obj == null) {
			return new ResultDTO(500, "失败");
		}
		return new ResultDTO(200, obj);
	}
	
	@RequestMapping("/send")
	public ResultDTO sendCode(@RequestParam("email") String receiveMail, String nickName, HttpSession session) {
		String code = "";
		Random rd = new Random();
		while (code.length() < 6) {
			code += rd.nextInt(10);
		}
		
		if (sendEmialUtil.sendEmail(receiveMail, code, nickName)) {
			session.setAttribute(SessionKeyConstant.EMAILCODE, code); // 存到session
			
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					session.removeAttribute(SessionKeyConstant.EMAILCODE);
				}
			};
			
			Timer timer = new Timer();
			timer.schedule(task, 180000); // 3分钟后执行任务一次
			return new ResultDTO(200, "成功");
		}
		return new ResultDTO(500, "失败");
	}

	@RequestMapping("/login")
	public ResultDTO login(@RequestParam Map<String, Object> map, HttpSession session) {
		MemberInfo mf = memberInfoBizImpl.login(map);
		if (mf != null) {
			session.setAttribute(SessionKeyConstant.MEMBERINFOLOGIN, mf);
			return new ResultDTO(200, "成功");
		}
		return new ResultDTO(500, "失败");
	}

	@RequestMapping("/reg")
	public ResultDTO reg(MemberInfo mf, HttpSession session, String vcode, String code) {
		String scode = String.valueOf(session.getAttribute(SessionKeyConstant.VERIFICATIONCODE));
		String svcode = String.valueOf(session.getAttribute(SessionKeyConstant.EMAILCODE));
		
		if (!scode.equalsIgnoreCase(code)) {
			return new ResultDTO(501, "验证码错误");
		}
		
		if (!svcode.equalsIgnoreCase(vcode)) {
			return new ResultDTO(502, "邮箱验证码错误");
		}
		
		if (memberInfoBizImpl.reg(mf) > 0) {
			return new ResultDTO(200, "成功");
		}
		return new ResultDTO(500, "失败");
	}
}
