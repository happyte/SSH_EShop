package com.zs.ssh.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zs.ssh.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {
	//得到泛型所属的类
	private Class clazz;
	
	public BaseServiceImpl() {
		System.out.println("this代表的是当前调用构造方法的对象" + this);  
		System.out.println("this的父类信息:"+this.getClass().getSuperclass());
		System.out.println("this的父类信息包括泛型:"+this.getClass().getGenericSuperclass());
		//拿到泛型的参数类型
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
		//com.zs.ssh.model.Category
		System.out.println("clazz:"+clazz);
		//Category
		System.out.println("simpleName:"+clazz.getSimpleName());
	}
	
	//自动注入配置的SessionFactory Bean
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		String hql = "delete"+ clazz.getSimpleName()+"as c where c.id=:id";
		getSession().createQuery(hql).setInteger("id", id)
					.executeUpdate();
	}

	@Override
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> query() {
		String hql = "from "+clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}

}
