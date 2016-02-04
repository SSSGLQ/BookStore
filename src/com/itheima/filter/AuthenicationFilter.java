package com.itheima.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Customer;

/**
 * 拦截管理后台未登录的操作
 * @author yewmf
 *
 */
public class AuthenicationFilter implements Filter {

    private HttpServletRequest request;
	private HttpServletResponse response;

	public AuthenicationFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		String op = req.getParameter("op");
		request = (HttpServletRequest) req;
		response = (HttpServletResponse) resp;
		//1.判断
		if(!"loginManager".equals(op)){//如果不是登陆的操作。我就拦

			Customer c = (Customer) request.getSession().getAttribute("loginedUser");
			if(c==null){
				response.sendRedirect(request.getContextPath()+"/manager");
				return;
			}
			
			
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
