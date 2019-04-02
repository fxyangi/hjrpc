package com.hjrpc.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.hjrpc.api.service.MemberService;
import com.hjrpc.base.BaseApiService;
import com.hjrpc.base.ResponseBase;
import com.hjrpc.dao.MemberDao;
import com.hjrpc.entity.UserEntity;

@RestController
public class MemberServiceImpl extends BaseApiService implements MemberService {

	@Autowired
	private MemberDao memberDao;

	@Override
	public ResponseBase findUser(Long userId) {
		UserEntity userEntity = memberDao.findByID(userId);
		if (userEntity == null) {
			return setErrorResult("为查找到该用户信息");
		}
		return setSuccessResult(userEntity);
	}

}
