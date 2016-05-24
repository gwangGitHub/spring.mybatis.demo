package com.gwang.spring.mybatis.demo.domain;


public class AccountSearchParam {
	
	private String ids = "";
	
	private String username = "";
	
	/**
	 * 1.当url不带pageNum参数时，默认
	 * 2.当url参数为pageNum=1时，pageNum为1
	 * 3.当url参数为pageNum=时(没有具体数值) 
	 *  3.1 pageNum为int类基础数据时，页面报400错误，请求不会进入controller内部方法
	 *  3.2 pageNum为Integer封装类时，pageNum会被设置为null，而不是保留原有的默认值10
	 *  	(解决办法可以在setPageNum方法中判断下如果不为null才se)
	 */
	private int pageNum = 1; 
	
	private int pageSize = 10;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
