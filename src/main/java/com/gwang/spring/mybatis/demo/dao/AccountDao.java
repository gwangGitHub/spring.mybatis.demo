package com.gwang.spring.mybatis.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SqlBuilder;
import org.springframework.stereotype.Component;

import com.gwang.spring.mybatis.demo.dao.helper.ListParamHelper;
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
    
    public int updateUsername(@Param("id") long id, @Param("username") String username);

	@Select({ "select " + SELECT_FIELDS, "from " + TABLE, "where id=#{id} and valid=1" })
	public Account getById(@Param("id") long id);
	
	@SelectProvider(type = AccountSqlProvider.class, method = "getByIds")
    public List<Account> getByIds(@Param("ids") List<Long> ids);
	
	@SelectProvider(type = AccountSqlProvider.class, method = "getByIdsAndName")
    public List<Account> getByIdsAndName(@Param("ids") List<Long> ids, @Param("username") String username);
	
	public static class AccountSqlProvider {
		public String getByIds(Map<String, Object> params) throws Exception {
			SqlBuilder.BEGIN();
			SqlBuilder.SELECT(SELECT_FIELDS);
			SqlBuilder.FROM(TABLE);
			SqlBuilder.WHERE(ListParamHelper.preparedInSql(params, "ids", "id", true));
			SqlBuilder.WHERE("valid=1");
			return SqlBuilder.SQL();
		}
		
		public String getByIdsAndName(Map<String, Object> params) throws Exception {
			String username = (String) params.get("username");
			SqlBuilder.BEGIN();
			SqlBuilder.SELECT(SELECT_FIELDS);
			SqlBuilder.FROM(TABLE);
			SqlBuilder.WHERE(ListParamHelper.preparedInSql(params, "ids", "id", true));
			if (StringUtils.isNoneBlank(username)) {
				SqlBuilder.WHERE("username=#{username}");
			}
			SqlBuilder.WHERE("valid=1");
			return SqlBuilder.SQL();
		}
	}
	
	public List<Account> search(@Param("ids")List<Long> ids, @Param("username")String username, @Param("offset") int offset, @Param("size") int size);
	
	public int searchCount(@Param("ids")List<Long> ids, @Param("username")String username);
}
