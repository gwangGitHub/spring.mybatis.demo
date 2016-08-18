package com.gwang.spring.mybatis.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Service;

import com.gwang.spring.mybatis.demo.dao.AccountDao;
import com.gwang.spring.mybatis.demo.domain.Account;
import com.gwang.spring.mybatis.demo.domain.AccountSearchParam;
import com.gwang.spring.mybatis.demo.vo.AccountPage;
import com.gwang.spring.mybatis.demo.vo.helper.AccountHelper;

@Service
public class AccountService implements InitializingBean, ApplicationListener<ApplicationContextEvent>, ApplicationContextAware {

	@Autowired
	private AccountDao accountDao;
	
	public int createAccount(Account account) {
		return accountDao.insert(account);
	}
	
	public int changePassword(long id, String password) {
		return accountDao.updatePassword(id, password);
	}
	
	public Account getAccountById (long id) {
		return accountDao.getById(id);
	}
	
	public List<Account> getAccountByIds(List<Long> ids) {
		return accountDao.getByIds(ids);
	}
	
	public List<Account> getAccountByIdsAndName(List<Long> ids, String username) {
		return accountDao.getByIdsAndName(ids, username);
	}
	
	public AccountPage search(AccountSearchParam accountSearchParam) {
		AccountPage accountPage = new AccountPage();
		int pageSize = accountSearchParam.getPageSize();
		int offset = (accountSearchParam.getPageNum() - 1) * pageSize;
		List<Long> ids = new ArrayList<Long>();
		if (StringUtils.isNoneBlank(accountSearchParam.getIds())) {
			String [] idsStr = accountSearchParam.getIds().split(",");
			for (String id : idsStr) {
				ids.add(Long.valueOf(id));
			}
		}
		int total = accountDao.searchCount(ids, accountSearchParam.getUsername());
		accountPage.setPageNum(accountSearchParam.getPageNum());
		accountPage.setPageSize(accountSearchParam.getPageSize());
		accountPage.setTotal(total);
		if (total > 0) {
			List<Account> accounts = accountDao.search(ids, accountSearchParam.getUsername(), offset, pageSize);
			accountPage.setAccounts(AccountHelper.transToViews(accounts));
		}
		return accountPage;
	}

	/**
	 * InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，
	 * 凡是继承该接口的类，在初始化bean的时候会执行该方法。
	 * 
	 * 关于在spring  容器初始化 bean 和销毁前所做的操作定义方式有三种：
	 * 第一种：通过@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作
	 * 第二种是：通过 在xml中定义init-method 和  destory-method方法
	 * 第三种是： 通过bean实现InitializingBean和 DisposableBean接口
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("bean init");
	}

	/**
	 * 什么是ApplicationContext? 
	 * 它是Spring的核心，Context我们通常解释为上下文环境，但是理解成容器会更好些。 
	 * ApplicationContext则是应用的容器。
	 * Spring把Bean（object）放在容器中，需要用就通过get方法取出来。
	 * 什么是 ApplicationEvent
	 * 是个抽象类，里面只有一个构造函数和一个长整型的timestamp。
	 * ApplicationListener
	 * 是一个接口，里面只有一个onApplicationEvent方法。
	 * 所以自己的类在实现该接口的时候，要实装该方法。
	 * 如果在上下文中部署一个实现了ApplicationListener接口的bean,
	 * 那么每当在一个ApplicationEvent发布到 ApplicationContext时，
	 * 这个bean得到通知。其实这就是标准的Observer设计模式。
	 */
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		System.out.println("service event:" + event.getSource());
		System.out.println(event.toString());
	}

	/**
	 * Spring中，普通bean可以通过实现ApplicationContextAware得到ApplicationContext
	 * ApplicationContextAwareProcessor.postProcessBeforeInitialization()，
	 * 对继承自ApplicationContextAware的bean进行处理，调用其setApplicationContext。
	 * 而ApplicationContextAwareProcessor是在spring容器start的时候生成的
	 * 
	 * 如果想在初始化bean的同时获得applicationContext用这个方法
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
	}
}
