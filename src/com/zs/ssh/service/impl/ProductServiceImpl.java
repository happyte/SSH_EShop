package com.zs.ssh.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zs.ssh.model.Product;
import com.zs.ssh.service.ProductService;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

	@Override
	public List<Product> queryJoinCategory(String name, int page, int size) {
		String hql = "from Product p left join fetch p.category where p.name like:name";
		return getSession().createQuery(hql).setString("name", "%"+name+"%")
						   .setFirstResult((page-1)*size)
						   .setMaxResults(size)
					       .list();
	}

	@Override
	public Long getCount(String name) {
		String hql = "select count(p) from Product p where p.name like:name ";
		return (Long) getSession().createQuery(hql)
				.setString("name",  "%"+name+"%")
				.uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Product p where p.id in ("+ids+")";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<Product> queryByCategoryId(int cid) {
		//同时查询出商品的种类,只查询4个
		String hql = "from Product p join fetch p.category "
					+"where p.commend=true and p.open=true and p.category.id=:cid order by p.date desc";
		return getSession().createQuery(hql).setInteger("cid", cid).
							setFirstResult(0).
							setMaxResults(4).list();
	}


}
