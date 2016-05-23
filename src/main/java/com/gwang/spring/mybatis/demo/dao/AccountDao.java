package com.gwang.spring.mybatis.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.gwang.spring.mybatis.demo.domain.Account;

@Component
public interface AccountDao {
	public static final String TABLE = " account ";
	public static final String INSERT_FIELDS = " username, password, valid";
	public static final String SELECT_FIELDS = " id," + INSERT_FIELDS;
	public static final String INSERT_VALUES = "#{username},#{password},#{valid}";

	@Insert({ "insert into " + TABLE + "(" + INSERT_FIELDS + ")","values(" + INSERT_VALUES + ")" })
	@Options(useGeneratedKeys = true)
	public int insert(Account account);
	
    @Options(useGeneratedKeys = true)
    @Update("update " + TABLE + " set password=#{password} where id= #{id}")
    public int updatePassword(@Param("id") long id, @Param("password") String password);

	@Select({ "select " + SELECT_FIELDS, "from " + TABLE, "where id=#{id} and valid=1" })
	public Account getById(@Param("id") long id);
}
