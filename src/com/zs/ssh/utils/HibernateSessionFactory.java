package com.zs.ssh.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSessionFactory {
	//sessionFactory中用的是线程池技术
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>(); 
	//sessionFactory：创建session的工厂 ,单例模式 
    private static SessionFactory sessionFactory;  
      
    private static Configuration configuration = new Configuration();  
    private static ServiceRegistry serviceRegistry;   
  
    //类加载时初始化sessionFactory 
    static {
        try {  
        	configuration.configure();  
        	serviceRegistry = new ServiceRegistryBuilder().
        			applySettings(configuration.getProperties()).buildServiceRegistry();  
        	sessionFactory = configuration.buildSessionFactory(serviceRegistry); //Hibernate4中创建sessionFactory的方法  
        } 
        catch (Exception e) {  
        	System.err.println("---Error Creating SessionFactory---");  
        	e.printStackTrace();  
        }  
    }  
    
    //私有构造方法阻止new出对象，保证sessionFactory的单例 
    private HibernateSessionFactory() {
    }  
      
    public static Session getSession() throws HibernateException {  
    	//从线程池中拿session  
        Session session = (Session) threadLocal.get();  
        //如果线程池是空的，或者session打开失败  
        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {  
                rebuildSessionFactory(); //如果sessionFactory是空的，那就再创建一次，和static部分一样的  
            }  
            //sessionFactory不为空就创建一个session  
            session = (sessionFactory != null) ? sessionFactory.openSession() : null;
            //然后把这个session放到线程池中，下次再拿 
            threadLocal.set(session);  
        }  
        return session;  
    }  
  
    public static void rebuildSessionFactory() {  
	    try {  
	        configuration.configure();  
	        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();  
	        sessionFactory = configuration.buildSessionFactory(serviceRegistry);  
	    } catch (Exception e) {  
	        System.err.println("---Error Creating SessionFactory---");  
	        e.printStackTrace();  
	    }  
    }  
  
    public static void closeSession() throws HibernateException {  
        Session session = (Session) threadLocal.get();  
        threadLocal.set(null);  
  
        if (session != null) {  
            session.close();  
        }  
    }  
  
    //提供一个公共接口让外界获得这个单例sessionFactory  
    public static SessionFactory getSessionFactory() {
    	return sessionFactory;  
    }  
  
    public static Configuration getConfiguration() {  
    	return configuration;  
    }  
}
