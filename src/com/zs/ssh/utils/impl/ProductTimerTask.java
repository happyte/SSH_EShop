package com.zs.ssh.utils.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zs.ssh.model.Category;
import com.zs.ssh.model.Product;
import com.zs.ssh.service.CategoryService;
import com.zs.ssh.service.ProductService;

//创建一个新的线程任务，只需要继承TimerTask，并重写run方法即可。
@Component
public class ProductTimerTask extends TimerTask {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	//在initDataListener中传过来
	private ServletContext application;
	
	public void setApplication(ServletContext application) {
		this.application = application;
	}

	@Override
	public void run() {
		System.out.println("===run===");
		 List<List<Product>> bigList = new ArrayList<List<Product>>(); //bigList中存放一个装有Category类的list  
	     // 1. 查询出热点类别  
	     for(Category category : categoryService.queryByHot(true)) {  
	        //根据热点类别id获取推荐商品信息  
	        List<Product> lst = productService.queryByCategoryId(category.getId());  
	        bigList.add(lst); //将装有category的list放到bigList中  
	     }  
	     // 2. 把查询的bigList交给application内置对象  
	     application.setAttribute("bigList", bigList);
	}

}
