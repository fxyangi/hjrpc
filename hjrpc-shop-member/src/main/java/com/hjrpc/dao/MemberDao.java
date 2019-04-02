package com.hjrpc.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.hjrpc.entity.UserEntity;

@Mapper
public interface MemberDao {
	@Select("select  id,username,password,phone,email,created,updated from mb_user where id =#{userId}")
	UserEntity findByID(@Param("userId") Long userId);

	@Insert("INSERT  INTO mb_user(username,password,phone,email,created,updated) VALUES (#{username}, #{password},#{phone},#{email},#{created},#{updated})")
	Integer insertUser(UserEntity userEntity);
}