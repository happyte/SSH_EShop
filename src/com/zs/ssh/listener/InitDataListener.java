package com.zs.ssh.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zs.ssh.model.Category;
import com.zs.ssh.model.Product;
import com.zs.ssh.service.CategoryService;
import com.zs.ssh.service.ProductService;

public class InitDataListener implements ServletContextListener {
	
	private CategoryService categoryService = null;
	private ProductService productService = null;
	private ApplicationContext context= null;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());          
        categoryService = context.getBean(CategoryService.class);//加载类别信息          
        productService = context.getBean(ProductService.class);//加载商品信息  
  
        List<List<Product>> bigList = new ArrayList<List<Product>>(); //bigList中存放一个装有Category类的list  
        // 1. 查询出热点类别  
        for(Category category : categoryService.queryByHot(true)) {  
            //根据热点类别id获取推荐商品信息  
            List<Product> lst = productService.queryByCategoryId(category.getId());  
            bigList.add(lst); //将装有category的list放到bigList中  
        }  
        // 2. 把查询的bigList交给application内置对象  
        event.getServletContext().setAttribute("bigList", bigList);  
	}

}
