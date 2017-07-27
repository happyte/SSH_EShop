package com.zs.ssh.action;

import com.zs.ssh.model.Category;

public class CategoryAction extends BaseAction<Category> {

	private static final long serialVersionUID = 1L;
	
	public String update(){
		 System.out.println("----update----"); 
	     categoryService.update(model);
	     return "index";  
	}
	
	public String save() {  
        System.out.println("----save----");  
        categoryService.save(model);
        return "index";  
    }  
	
	public String query(){
		 System.out.println("----query----");  
		request.put("categoryList", categoryService.query());
		return "index";
	}
	
}
