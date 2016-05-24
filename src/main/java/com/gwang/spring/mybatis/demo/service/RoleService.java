package com.gwang.spring.mybatis.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwang.spring.mybatis.demo.dao.RoleDao;
import com.gwang.spring.mybatis.demo.domain.Role;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public int createRole(Role role) {
		return roleDao.insert(role);
	}
	
	public int deleteRole(long id) {
		return roleDao.updateValid(id);
	}
	
	public List<Role> getRoleByIds(List<Long> ids) {
		return roleDao.getByIds(ids);
	}
}
