package com.itheima.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.service.BookService;
import com.itheima.service.CategoryService;
import com.itheima.service.CustomerService;
import com.itheima.service.OrdersService;
import com.itheima.service.impl.BookServiceImpl;
import com.itheima.service.impl.CategoryServiceImpl;
import com.itheima.service.impl.CustomerServiceImpl;
import com.itheima.service.impl.OrdersServiceImpl;
import com.itheima.utils.IDGenerator;
import com.itheima.utils.WebUtils;

/**
 * ��̨����
 * @author yewmf
 *
 */
public class ManagerServlet extends HttpServlet {
	
	private static String ADDCATEGORY = "addCategory";
	private static String SHOWALLCATEGORIES = "showAllCategories";
	
	private BookService bs = new BookServiceImpl();
	private CategoryService cs = new CategoryServiceImpl();
	private CustomerService customerservice = new CustomerServiceImpl();
	private OrdersService os = new OrdersServiceImpl();
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String op = request.getParameter("op");
		
		if(ADDCATEGORY.equals(op)){
			addCategory(request,response);
		}else if(SHOWALLCATEGORIES.equals(op)){
			showAllCategories(request,response);
		}
	}

	/**
	 * �鿴���ж���
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showAllCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> list = cs.findAllCategorys();
		request.setAttribute("cs", list);
		//ת����ҳ��
		request.getRequestDispatcher("/manager/listAllCategories.jsp").forward(request, response);
		
	}

	/**
	 * ��̨�����������---��ӷ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Category c = WebUtils.fillBean(request, Category.class);
		c.setId(IDGenerator.genId());
		cs.addCategory(c);
		
		//������Ϻ���ʾ���еķ�����Ϣ����ѯ���з��ࣩ
		showAllCategories(request,response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
