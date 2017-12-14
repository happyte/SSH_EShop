package com.zs.ssh.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/UserFilter")
public class UserFilter implements Filter {

   
    public UserFilter() {
    }

	public void destroy() {
	}

	//http://localhost:8989/EShop/sorder_addSorder.action?product.id=5
	//http://localhost:8989/EShop/user/confirm.jsp
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req  = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//判断当前session中是否有用户信息
		if(req.getSession().getAttribute("user") == null){
//			System.out.println("没有用户信息");
			String goURL = req.getServletPath();
			String param = req.getQueryString();
			if(param != null){
				goURL = goURL+"?"+param;
			}
//			System.out.println("goURL:"+goURL);
//			System.out.println("path:"+req.getServletPath());
			req.getSession().setAttribute("goURL", goURL);
			req.getSession().setAttribute("error", "非法请求，请先登陆");
			res.sendRedirect(req.getContextPath()+"/ulogin.jsp");
		}
		else {
//			System.out.println("有用户信息");
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
