package com.zs.ssh.action;

import java.io.ByteArrayInputStream;
import java.util.Date;
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
	
	public String deleteByIds(){
		productService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	
	public void save(){
		//需要专门利用struts处理上传照片
		model.setDate(new Date());
		System.out.println(model);
		productService.save(model);
	}
}
