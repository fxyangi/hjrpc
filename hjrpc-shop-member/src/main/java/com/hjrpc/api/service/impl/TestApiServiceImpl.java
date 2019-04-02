package com.hjrpc.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.hjrpc.api.service.TestApiService;
import com.hjrpc.base.BaseApiService;
import com.hjrpc.base.BaseRedisService;
import com.hjrpc.base.ResponseBase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestApiServiceImpl extends BaseApiService implements TestApiService {
	
	@Autowired
	private BaseRedisService baseRedisService;
	@Override
	public Map<String, Object> test(Integer id, String name) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("rtnCode", "200");
		result.put("msg", "success");
		result.put("data", new String[]{"111111","222222","id"+id+",name+"+name});
		return result;
	}

	@Override
	public ResponseBase test02(Integer id, String name) {
		return setSuccessResult();
	}

	@Override
	public ResponseBase setRedisTest(String key, String value) {
		baseRedisService.setString(key, value, null);
		return setSuccessResult();
	}

	@Override
	public ResponseBase getRedisTest(String key) {
		return setSuccessResult(baseRedisService.getString(key));
	}

}
