package com.gwang.spring.mybatis.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwang.spring.mybatis.demo.dao.TranFlowDao;
import com.gwang.spring.mybatis.demo.domain.TranFlow;

@Service
public class TranFlowService {
	
    @Autowired
    private TranFlowDao tranFlowDao;

    public TranFlow getById(int id) {
        return tranFlowDao.getById(id);
    }
}
