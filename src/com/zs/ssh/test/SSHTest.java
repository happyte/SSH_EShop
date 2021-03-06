package com.zs.ssh.test;


import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zs.ssh.model.Account;
import com.zs.ssh.model.Category;
import com.zs.ssh.model.Product;
import com.zs.ssh.model.User;
import com.zs.ssh.service.AccountService;
import com.zs.ssh.service.CategoryService;
import com.zs.ssh.service.ProductService;
import com.zs.ssh.service.UserService;

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
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;

	//Spring的单元测试失败，不知道是不是因为包的原因引起的
	//把全部的jar包导入进来，测试成功了......
	@Test 
    public void springIoc() {  
        System.out.println(date);  
    }  

	@Test
	public void testHibernate(){
		User user = new User();
		user.setLogin("user");
		user.setPass("user");
		user = userService.login(user);
		System.out.println("user:"+user);
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
	
	@Test
	public void testGetCount(){
		System.out.println(categoryService.getCount(""));
	}
	
	@Test
	public void testProductAdd(){
//		Product product = new Product("圣得西服", new BigDecimal(12.5), "test.jpg", 
//				"这里是简单介绍", "这里是详细介绍", true, true);
		Product product = new Product("衫衫西服", new BigDecimal(10.0), "test.jpg", "这里是简单介绍", 
				"这里是详细介绍", new Date(), true, true, new Category(2, null, null));
		productService.save(product);
	}
	
	@Test
	public void testCategoryHot(){
		System.out.println(categoryService.queryByHot(true));
	}
	
	@Test
	public void testProductHot(){
		System.out.println(productService.queryByCategoryId(1));
	}
}
