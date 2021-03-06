package com.yc.wowo.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.wowo.util.SessionKeyConstant;

/**
 * 图形验证码
 * company 源辰信息
 * @author navy
 * @date 2020年10月25日
 * Email haijunzhou@hnit.edu.cn
 */
@Controller
@RequestMapping
public class CreateCodeController {
	
	@RequestMapping("/code")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 处理缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		// 生成验证码
		String code = getRandomCode();
		
		// 将验证码存到session中
		request.getSession().setAttribute(SessionKeyConstant.VERIFICATIONCODE, code);
		
		// 创建验证码图片并返回
		BufferedImage image = this.getCodeImage(code, 110, 38);
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}
	
	private BufferedImage getCodeImage(String code, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		// 绘制图片 -> 获取一个绘笔
		Graphics g = image.getGraphics();
		
		// 先绘制背景色  -> 设置绘笔的颜色
		g.setColor(this.getRandomColor(210, 45));
		
		// 绘制背景
		g.fillRect(0, 0, width, height);
		
		// 绘制干扰线
		int x1, y1, x2, y2;
		Random rd = new Random();
		for (int i = 0; i < 50; i ++) {
			// 给每条干扰线一种颜色
			g.setColor(this.getRandomColor(140, 50));
			x1 = rd.nextInt(width);
			y1 = rd.nextInt(height);
			x2 = rd.nextInt(width);
			y2 = rd.nextInt(height);
			
			g.drawLine(x1, y1, x2, y2);
		}
		
		// 绘制验证码
		// 设置验证码的字体
		g.setFont(new Font("Couriew New", Font.ITALIC, 24));
		
		char[] codes = code.toCharArray();
		for (int i = 0, len = codes.length; i < len; i ++) {
			// 给每一个字体一种颜色
			g.setColor(this.getRandomColor(40, 80));
			g.drawString(String.valueOf(codes[i]), 10 + 24 * i, 28);
		}
		
		return image;
	}
	
	/**
	 * 生成验证码的方法
	 * @return
	 */
	private String getRandomCode() {
		Random rd = new Random();
		StringBuffer sbf = new StringBuffer();
		int flag = 0;
		for (int i = 0; i < 4; i ++) {
			flag = rd.nextInt(3);
			switch (flag) {
			case 0: sbf.append(rd.nextInt(10)); break;
			case 1: sbf.append((char)(rd.nextInt(26) + 65)); break;
			default: sbf.append((char)(rd.nextInt(26) + 97)); break;
			}
		}
		return sbf.toString();
	}
	
	/**
	 * 生成随机颜色
	 * @param start 开始值
	 * @param bound 范围
	 * @return
	 */
	private Color getRandomColor(int start, int bound) {
		Random rd = new Random();
		int r = start + rd.nextInt(bound);
		int g = start + rd.nextInt(bound);
		int b = start + rd.nextInt(bound);
		return new Color(r, g, b);
	}
}
