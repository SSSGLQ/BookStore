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
 * ���ع����̨δ��¼�Ĳ���
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
		//1.�ж�
		if(!"loginManager".equals(op)){//������ǵ�½�Ĳ������Ҿ���

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
