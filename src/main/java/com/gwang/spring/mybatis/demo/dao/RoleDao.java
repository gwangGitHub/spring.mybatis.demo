package com.gwang.spring.mybatis.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gwang.spring.mybatis.demo.datasource.DataSource;
import com.gwang.spring.mybatis.demo.datasource.DataSources;
import com.gwang.spring.mybatis.demo.domain.Role;

@Component
@DataSource(DataSources.SLAVE)
public interface RoleDao {

	public int insert(Role role);
	
	public int updateValid(long id);
	
	public List<Role> getByIds(@Param("ids")List<Long> ids);
}
