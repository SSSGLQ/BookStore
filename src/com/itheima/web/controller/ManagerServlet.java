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
import com.itheima.utils.PageBean;
import com.itheima.utils.WebUtils;

/**
 * 后台管理
 * @author yewmf
 *
 */
public class ManagerServlet extends HttpServlet {
	
	private static String ADDCATEGORY = "addCategory";
	private static String SHOWALLCATEGORIES = "showAllCategories";
	private static String SHOWALLBOOKS = "showAllBooks";
	
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
		}else if(SHOWALLBOOKS.equals(op)){
			showAllBooks(request,response);
		}
	}

	/**
	 * 查询图书---并且分页展示
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showAllBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//2.得到图书列表，要分页
		PageBean pb = new PageBean();
		String pageNo = request.getParameter("pageNo");
		if(pageNo!=null && !"".equals(pageNo)){
			pb.setPageNo(Integer.valueOf(pageNo));
		}
		
		//3.分页查询
		bs.findBooksByPageBean(pb);
		pb.setUrl(request.getContextPath()+"/servlet/ManagerServlet?op=showAllBooks");
		
		//4.放到域中
		request.setAttribute("page", pb);
		
		//5.转发
		request.getRequestDispatcher("/manager/listBooks.jsp").forward(request, response);
	}

	/**
	 * 查看所有订单
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showAllCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> list = cs.findAllCategorys();
		request.setAttribute("cs", list);
		//转发到页面
		request.getRequestDispatcher("/manager/listAllCategories.jsp").forward(request, response);
		
	}

	/**
	 * 后台管理：分类管理---添加分类
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
		
		//保存完毕后，显示所有的分类信息（查询所有分类）
		showAllCategories(request,response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
