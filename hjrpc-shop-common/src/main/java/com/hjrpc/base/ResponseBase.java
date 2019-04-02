package com.hjrpc.base;

import lombok.Data;

@Data
public class ResponseBase {
	private String code;
	private String msg;
	private Object data;
	
	public ResponseBase() {
		super();
	}

	public ResponseBase(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	
}
