package com.hjrpc.adapter;

import com.alibaba.fastjson.JSONObject;

public interface MessageAdapter {

	public void sendMsg(JSONObject body);
}
