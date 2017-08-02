package com.zs.ssh.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zs.ssh.utils.impl.ProductTimerTask;


public class InitDataListener implements ServletContextListener {
	
	private ProductTimerTask productTimerTask = null;
	private ApplicationContext context= null;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());          
		productTimerTask = context.getBean(ProductTimerTask.class);
		//把内置对象交给productTimerTask,因为productTimerTask里面是拿不到application的，只能通过监听器set给它  
		productTimerTask.setApplication(event.getServletContext());
		//配置为守护线程，知道程序结束后，守护线程才会结束，垃圾回收是个守护线程
		new Timer(true).schedule(productTimerTask, 0, 10*60*1000);
	}

}
