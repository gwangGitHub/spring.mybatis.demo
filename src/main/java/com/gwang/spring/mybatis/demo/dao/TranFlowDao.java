package com.gwang.spring.mybatis.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gwang.spring.mybatis.demo.domain.TranFlow;

public interface TranFlowDao {
	
	public static final String TABLE = " tran_flow ";
	public static final String INSERT_FIELDS = " tran_date, acc_no, tran_state, tran_type, id_type, id_no";
	public static final String SELECT_FIELDS = " flow_seq," + INSERT_FIELDS;
	
	@Select({ "select " + SELECT_FIELDS, "from " + TABLE, "where flow_seq=#{id}" })
	public TranFlow getById(@Param("id") int id);
}
