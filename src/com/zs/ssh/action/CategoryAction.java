package com.zs.ssh.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.zs.ssh.model.Category;

@Controller
public class CategoryAction extends BaseAction<Category> {

	private static final long serialVersionUID = 1L;
	
	public String queryJoinAccount(){
		System.out.println("model:"+model);
		//pageMap用来存储分页的数据
		pageMap = new HashMap<>();
		List<Category> categories = categoryService.queryJoinAccount(model.getType(), page, rows);
		//与json一致，key-value,先存rows
		pageMap.put("rows", categories);  
		Long count = categoryService.getCount(model.getType());
		//再存总记录数
		pageMap.put("total", count);
		System.out.println("categories:"+categories);
		System.out.println("count:"+count);
		return "jsonMap";
	}
	
	public String deleteByIds(){
		System.out.println("ids:"+ids);
		categoryService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	
	public void save(){
		System.out.println(model);
		categoryService.save(model);
	}
	
	public void update(){
		System.out.println(model);
		categoryService.update(model);
	}
	
	public String query(){
		jsonList = categoryService.query();
		return "jsonList";
	}
	
}
