package com.zs.ssh.test;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zs.ssh.model.Account;
import com.zs.ssh.model.Category;
import com.zs.ssh.service.AccountService;
import com.zs.ssh.service.CategoryService;

//Spring的单元测试
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext*.xml"})
public class SSHTest {
	
	@Autowired
	private Date date;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AccountService accountService;

	//Spring的单元测试失败，不知道是不是因为包的原因引起的
	//把全部的jar包导入进来，测试成功了......
	@Test 
    public void springIoc() {  
        System.out.println(date);  
    }  

	@Test
	public void testHibernate(){
		//categoryService.save(new Category("男士休闲", true));
		accountService.save(new Account("user1", "客服B", "user1"));
	}
	
	@Test
	public void testUpdate(){
		categoryService.update(new Category(1, "女士休闲", false));
	}
	
	@Test
	public void testDelete(){
		categoryService.delete(1);
	}
	
	@Test
	public void testGet(){
		System.out.println(categoryService.get(1));
	}
	
	@Test
	public void testGetAll(){
		System.out.println(categoryService.query());
	}
	
	@Test
	public void testJoin(){
		System.out.println(categoryService.queryJoinAccount("",1,2));
	}
	
}
