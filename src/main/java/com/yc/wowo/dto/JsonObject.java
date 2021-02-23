package com.yc.wowo.dto;

public class JsonObject {
	private int total; // 总记录数
	private Object rows; // 当前查询的这一页的数据
	
	@Override
	public String toString() {
		return "JsonObject [total=" + total + ", rows=" + rows + "]";
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public JsonObject(int total, Object rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public JsonObject() {
		super();
	}
}
