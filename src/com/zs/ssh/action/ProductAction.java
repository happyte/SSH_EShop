package com.zs.ssh.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.zs.ssh.model.Product;

@Controller
public class ProductAction extends BaseAction<Product> {

	private static final long serialVersionUID = 1L;
	
	public String queryJoinCategory(){
		System.out.println("model:"+model);
		//pageMap用来存储分页的数据
		pageMap = new HashMap<>();
		List<Product> products = productService.queryJoinCategory(model.getName(), page, rows);
		//与json一致，key-value,先存rows
		pageMap.put("rows", products);  
		Long count = productService.getCount(model.getName());
		//再存总记录数
		pageMap.put("total", count);
		System.out.println("products:"+products);
		System.out.println("count:"+count);
		return "jsonMap";
	}

}
