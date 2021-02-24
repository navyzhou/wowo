function openWebSocket(usid) {
	var socket;
	
	if (typeof(WebSocket) == undefined) {
		alert("对不起，您的浏览器暂不支持WebSocket通信...");
		return;
	}
	
	socket = new WebSocket("ws://127.0.0.1:8888/websocket/" + usid);
	
	socket.onopen = function() {
		console.info("socket已连接...");
	}
	
	socket.onclose = function() {
		console.info("socket已关闭...");
	}
	
	socket.onerror = function() {
		console.info("访问服务器失败...");
	}
	
	socket.onmessage = function(msg) {
		if (msg.data == "101") {
			alert("对不起，您的账号已经在其它地方登录，若非本人操作，请及时修改密码...");
			location.href = "../index.html"; // 正常流程应该是先发请求到后台清空session等缓存数据，在跳到登录界面
		} else {
			console.info(msg);
		}
	}
}