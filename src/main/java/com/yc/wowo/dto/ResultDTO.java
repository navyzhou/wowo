package com.yc.wowo.dto;

/**
 * 数据结果返回传输对象
 * company 源辰信息
 * @author navy
 * @date 2021年1月28日
 * Email haijunzhou@hnit.edu.cn
 */
public class ResultDTO {
	private int code; // 响应码
	private String msg; // 响应信息
	private Object data; // 响应数据
	
	public ResultDTO() {
		super();
	}
	
	public ResultDTO(int code) {
		this.code = code;
	}

	public ResultDTO(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ResultDTO(int code, Object data) {
		this.code = code;
		this.data = data;
	}
	
	public ResultDTO(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultDTO [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultDTO other = (ResultDTO) obj;
		if (code != other.code)
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (this.data == null) {
			if (other.data != null)
				return false;
		} else if (!this.data.equals(other.data))
			return false;
		return true;
	}
}
