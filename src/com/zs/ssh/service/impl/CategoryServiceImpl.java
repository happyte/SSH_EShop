package com.zs.ssh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zs.ssh.model.Category;
import com.zs.ssh.service.CategoryService;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	@Override
	public List<Category> queryJoinAccount(String type,int page,int size) {
		//左外连接查询出Account
		String hql = "from Category c left join fetch c.account where c.type like:type";
		return getSession().createQuery(hql).setString("type", "%"+type+"%")
						   .setFirstResult((page-1)*size)
						   .setMaxResults(size)
						   .list();
	}

}
