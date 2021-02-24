package com.yc.wowo.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/websocket/{id}")
public class WebServerSocket {
	private static int onlineCount = 0; // 在线人数
	
	private static CopyOnWriteArraySet<WebServerSocket> webSocketSet = new CopyOnWriteArraySet<WebServerSocket>(); // 存放每个客户端对应的WebSocket对象

	private Session session;
	
	private String usid;
	
	/**
	 * 建立连接时
	 * @param session 连接会话
	 * @param usid 用户id
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("id")String usid) {
		this.session = session;
		this.usid = usid;
		
		webSocketSet.add(this);
		
		addOnlineCount();
		
		sendMessage("连接服务器成功....");
		
		System.out.println("用户 " + usid + " 上线了，当前在线用户人数 " + onlineCount);
	}
	
	/**
	 * 给客户端发送信息的方法
	 * @param msg
	 */
	public void sendMessage(String msg) {
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下线时
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 移除当前对象
		subOnlineCount();
		System.out.println("用户 " + usid + " 下线了，当前在线用户人数 " + onlineCount);
	}
	
	/**
	 * 接收到客户端发过来的信息时
	 * @param msg
	 * @param session
	 */
	@OnMessage
	public void onMessage(String msg, Session session) {
		System.out.println("收到来至用户 " + usid + " 发过来的信息 " + msg);
		
		// 转发给所有在线用户
		for (WebServerSocket wss : webSocketSet) {
			wss.sendMessage(msg);
		}
	}
	
	/**
	 * 出错时
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		new RuntimeException(error);
	}
	
	/**
	 * 根据id判断这个用户有没有登录
	 * @param usid
	 * @return
	 */
	public static WebServerSocket getWebSocket(String usid) {
		if (webSocketSet.isEmpty()) {
			return null;
		}
		
		for (WebServerSocket wss : webSocketSet) {
			if (usid.equals(wss.usid)) {
				return wss;
			}
		}
		
		return null;
	}
	
	
	private static synchronized void addOnlineCount() {
		WebServerSocket.onlineCount ++;
	}
	
	private static synchronized void subOnlineCount() {
		WebServerSocket.onlineCount --;
	}
	
	public static int getOnlineCount() {
		return WebServerSocket.onlineCount;
	}
}
