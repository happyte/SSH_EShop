package com.zs.ssh.test;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zs.ssh.model.Category;
import com.zs.ssh.service.CategoryService;
import com.zs.ssh.service.impl.CategoryServiceImpl;

//Spring的单元测试
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SSHTest {
	
	@Autowired
	private Date date;

	//Spring的单元测试失败，不知道是不是因为包的原因引起的
	//把全部的jar包导入进来，测试成功了......
	@Test 
    public void springIoc() {  
        System.out.println(date);  
    }  

	@Test
	public void testHibernate(){
		Category category = new Category("男士休闲", true);
		CategoryServiceImpl categoryService = new CategoryServiceImpl();
		categoryService.save(category);
	}
}
