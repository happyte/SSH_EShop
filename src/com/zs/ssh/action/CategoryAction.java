package com.zs.ssh.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.zs.ssh.model.Category;
import com.zs.ssh.service.CategoryService;

public class CategoryAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Category category;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Autowired
	private CategoryService categoryService;
	
	public String update(){
		 System.out.println("----update----"); 
		 System.out.println("category:"+category);
	     System.out.println("categoryService:"+categoryService); 
	     categoryService.update(category);
	     return "index";  
	}
	
	public String save() {  
        System.out.println("----save----");  
        System.out.println(categoryService);  
        categoryService.save(category);
        return "index";  
    }  
	
}
