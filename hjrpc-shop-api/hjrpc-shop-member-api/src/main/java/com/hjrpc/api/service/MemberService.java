package com.hjrpc.api.service;

import org.springframework.web.bind.annotation.RequestMapping;

import com.hjrpc.base.ResponseBase;

@RequestMapping("/member")
public interface MemberService {

	@RequestMapping("/findUserById")
	public ResponseBase findUser(Long userId);
}
