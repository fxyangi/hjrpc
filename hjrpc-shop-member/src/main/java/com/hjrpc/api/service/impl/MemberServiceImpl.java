package com.hjrpc.api.service.impl;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hjrpc.api.service.MemberService;
import com.hjrpc.base.BaseApiService;
import com.hjrpc.base.ResponseBase;
import com.hjrpc.constants.Constants;
import com.hjrpc.dao.MemberDao;
import com.hjrpc.entity.UserEntity;
import com.hjrpc.mq.RegisterMailboxProducer;
import com.hjrpc.utils.MD5Util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberServiceImpl extends BaseApiService implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	
	@Value("${messages.queue}")
	private String MESSAGESQUEUE;

	@Override
	public ResponseBase findUser(Long userId) {
		UserEntity userEntity = memberDao.findByID(userId);
		if (userEntity == null) {
			return setErrorResult("为查找到该用户信息");
		}
		return setSuccessResult(userEntity);
	}

	@Override
	public ResponseBase registUser(UserEntity userEntity) {
		String password = userEntity.getPassword();
		String newPassword = MD5Util.MD5(password);
		userEntity.setPassword(newPassword);
		Integer res = memberDao.insertUser(userEntity);
		if(res<=0) {
			return setErrorResult("用户注册失败");
		}
		
		// 采用异步方式发送消息
		String email = userEntity.getEmail();
		String json = emailJson(email);
		log.info("####会员服务推送消息到消息服务平台####json:{}", json);
		sendMsg(json);
		return setSuccessResult(userEntity);
	}

	private String emailJson(String email) {
		JSONObject rootJson = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("interfaceType", Constants.SMS_MAIL);
		JSONObject content = new JSONObject();
		content.put("email", email);
		rootJson.put("header", header);
		rootJson.put("content", content);
		return rootJson.toJSONString();
	}

	private void sendMsg(String json) {
		ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGESQUEUE);
		registerMailboxProducer.sendMsg(activeMQQueue, json);

	}
}
