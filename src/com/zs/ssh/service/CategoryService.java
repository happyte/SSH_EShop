package com.zs.ssh.service;

import java.util.List;

import com.zs.ssh.model.Category;

//基本的增删改查
public interface CategoryService extends BaseService<Category> {
	//查询类别信息，级联管理员，并实现分页  
    public List<Category> queryJoinAccount(String type,int page,int size); 
    //根据关键字查询总记录
    public Long getCount(String type);
    //根据ids删除记录
    public void deleteByIds(String ids);
}
