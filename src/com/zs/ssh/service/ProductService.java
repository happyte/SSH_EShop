package com.zs.ssh.service;

import java.util.List;

import com.zs.ssh.model.Product;

public interface ProductService extends BaseService<Product>{
	//查询类别信息，级联管理员，并实现分页  
    public List<Product> queryJoinCategory(String name,int page,int size); 
    //根据关键字查询总记录
    public Long getCount(String name);
    //根据ids删除记录
    public void deleteByIds(String ids);
    //根据热点类别查询推荐商品
    public List<Product> queryByCategoryId(int cid);
}
