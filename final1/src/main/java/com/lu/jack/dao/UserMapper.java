package com.lu.jack.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	
	@Results({
		@Result(property="password" ,column="password"),
		@Result(property="nickName" ,column="nickName"),
		@Result(property="userType", column="userType")}
	)
		@Select("select * from person where userName=#{username}")
	public User getUser(String userName);
	
}
