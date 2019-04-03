package com.hjrpc.api.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hjrpc.base.ResponseBase;
import com.hjrpc.entity.UserEntity;

@RequestMapping("/member")
public interface MemberService {

	@RequestMapping("/findUserById")
	public ResponseBase findUser(Long userId);
	
	@RequestMapping("/registUser")
	public ResponseBase registUser(@RequestBody UserEntity userEntity);
}
