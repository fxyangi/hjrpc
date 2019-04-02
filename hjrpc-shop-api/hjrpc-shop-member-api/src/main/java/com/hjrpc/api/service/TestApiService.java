package com.hjrpc.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import com.hjrpc.base.ResponseBase;

@RequestMapping("/member")
public interface TestApiService {
	@RequestMapping("/test")
	public Map<String,Object> test(Integer id,String name);
	
	@RequestMapping("/test02")
	public ResponseBase test02(Integer id,String name);
	
	@RequestMapping("/setRedisTest")
	public ResponseBase setRedisTest(String key,String value);
	
	@RequestMapping("/getRedisTest")
	public ResponseBase getRedisTest(String key);
}
