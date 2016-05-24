package com.gwang.spring.mybatis.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gwang.spring.mybatis.demo.domain.Role;

@Component
public interface RoleDao {

	public int insert(Role role);
	
	public int updateValid(long id);
	
	public List<Role> getByIds(@Param("ids")List<Long> ids);
}
