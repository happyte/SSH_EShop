package com.zs.ssh.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.ssh.model.Category;
import com.zs.ssh.service.CategoryService;
import com.zs.ssh.utils.HibernateSessionFactory;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	//自动注入配置的SessionFactory Bean
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Category category) {
//		 //通过刚刚生成的sessionFactory获取session  
//        Session session = HibernateSessionFactory.getSession();  
//        try {  
//            //手动事务  
//            session.getTransaction().begin();  
//            //执行业务逻辑  
//            session.save(category);  
//            //手动提交  
//            session.getTransaction().commit();  
//        } catch(Exception e) {  
//            session.getTransaction().rollback();  
//            throw new RuntimeException(e);  
//        } finally {  
//            HibernateSessionFactory.closeSession();  
//        }         
		sessionFactory.getCurrentSession().save(category);
	}

	@Override
	public void update(Category category) {
		sessionFactory.getCurrentSession().update(category);
	}

}
