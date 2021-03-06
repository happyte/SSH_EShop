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

	@Override
	public Long getCount(String type) {
		String hql = "select count(c) from Category c where c.type like:type ";
		return (Long) getSession().createQuery(hql)
				.setString("type",  "%"+type+"%")
				.uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		//删除集合操作
		String hql = "delete from Category c where c.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<Category> queryByHot(boolean hot) {
		String hql = "from Category c where c.hot=:hot";
		return getSession().createQuery(hql).setBoolean("hot", hot).list();
	}

}
