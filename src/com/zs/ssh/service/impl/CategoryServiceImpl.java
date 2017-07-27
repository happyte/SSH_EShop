package com.zs.ssh.service.impl;

import org.hibernate.Session;

import com.zs.ssh.model.Category;
import com.zs.ssh.service.CategoryService;
import com.zs.ssh.utils.HibernateSessionFactory;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public void save(Category category) {
		 //通过刚刚生成的sessionFactory获取session  
        Session session = HibernateSessionFactory.getSession();  
        try {  
            //手动事务  
            session.getTransaction().begin();  
            //执行业务逻辑  
            session.save(category);  
            //手动提交  
            session.getTransaction().commit();  
        } catch(Exception e) {  
            session.getTransaction().rollback();  
            throw new RuntimeException(e);  
        } finally {  
            HibernateSessionFactory.closeSession();  
        }         
	}

}
