package com.itheima.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itheima.domain.Book;
import com.itheima.domain.Category;
import com.itheima.domain.Customer;
import com.itheima.service.BookService;
import com.itheima.service.CategoryService;
import com.itheima.service.CustomerService;
import com.itheima.service.impl.BookServiceImpl;
import com.itheima.service.impl.CategoryServiceImpl;
import com.itheima.service.impl.CustomerServiceImpl;
import com.itheima.utils.IDGenerator;
import com.itheima.utils.PageBean;
import com.itheima.utils.SendMailTreadUtils;
import com.itheima.utils.WebUtils;
import com.itheima.web.form.Cart;

public class ClientServlet extends HttpServlet {
	
	private BookService bs = new BookServiceImpl();
	private CategoryService cs = new CategoryServiceImpl();
	private CustomerService customerservice = new CustomerServiceImpl();
	private static String SHOWINDEX = "showIndex";
	private static String BUYBOOK = "buyBook";
	private static String SHOWCATEGORYPAGERECORDS = "showCategoryPageRecords";
	private static String LOGIN = "login";
	private static String LOGOUT = "logout";
	private static String REGIST = "regist";
	private static String ACTIVED = "actived";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			String op = request.getParameter("op");
			
			if(SHOWINDEX.equals(op)){
				showIndex(request,response);
			}else if(SHOWCATEGORYPAGERECORDS.equals(op)){
				showCategoryPageRecords(request,response);
			}else if(BUYBOOK.equals(op)){
				buyBook(request,response);
			}else if(LOGIN.equals(op)){
				login(request,response);
			}else if(LOGOUT.equals(op)){
				logout(request,response);
			}else if(REGIST.equals(op)){
				regist(request,response);
			}else if(ACTIVED.equals(op)){
				actived(request,response);
			}
	}

	/**
	 * ע���û�ʱ�����û�
	 * @param request
	 * @param response
	 */
	private void actived(HttpServletRequest request,
			HttpServletResponse response) {
		
	}

	/**
	 * ҳ��ע��
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1.��װע��ҳ��Ĳ���
		Customer c = WebUtils.fillBean(request, Customer.class);//ע�������ֵ��ȫ��һ����ֵ��Ҫ�ֶ���ֵ
		
		//2.����������Ϣ
		c.setId(IDGenerator.genId());
		c.setCode(IDGenerator.genCode());
		c.setActived(0);//δ����
		c.setRole(0);//��ͨ�û�
		
		Boolean flag = customerservice.regist(c);
		
		if(flag){
			//ע��ɹ�
			//1.���������ʼ����߳�
			SendMailTreadUtils sendMain = new SendMailTreadUtils(c);
			sendMain.start();
			
			//2.��ʾ�û�������뵽��ҳ��
			response.getWriter().write("ע��ɹ������¼������м��2�����ת����¼��");
			response.setHeader("Refresh", "2;URL="+request.getContextPath()+"/login.jsp");
		}else{
			//ע��ʧ��
			response.getWriter().write("ע��ʧ�ܣ��û������ܴ��ڣ�2�����ת��ע��ҳ������ע��");
			response.setHeader("Refresh", "2;URL="+request.getContextPath()+"/regist.jsp");
		}
	}

	/**
	 * ע��
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//���session
		request.getSession().removeAttribute("user");
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * �û���½
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//1.��ȡ����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2.����ҵ���߼���ʵ�ֲ�ѯ����
		Customer c = customerservice.login(username,password);
		//3.�жϽ��
		if(c==null){
			//5.���ʧ�ܣ����ص�½ҳ��������½
			response.getWriter().write("��½ʧ�ܣ��������û��������룬δ����ԭ�������");
			response.setHeader("Refresh", "2;URL="+request.getContextPath()+"/login.jsp");
			return ;
		}else{
			//4.�����ȷ�����û���Ϣ���浽session,user������Ҫ��header.jsp�б���һ��
			request.getSession().setAttribute("user", c);
			response.sendRedirect(request.getContextPath());//�ص���ҳ
		}
	}

	/**
	 * �������ҳ�治�����û������ֶ�������ﳵȥ�鿴��¼��������ʹ�õ���ˢ�¼���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void buyBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.�õ����id��ת��Ϊһ��Book����
		String bookId = request.getParameter("bookId");
		Book book = bs.findOne(bookId);
		//2.������ӵ����ﳵ
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");//��һ��ʹ��ʱ��������
		if(cart == null){
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		//3.��book������빺�ﳵ
		cart.addBooks2Cart(book, 1);
	
		//4.�ٵ��ò�ѯ�����鼮�Ĳ���
		showIndex(request, response);
	}

	private void showCategoryPageRecords(HttpServletRequest request,
			HttpServletResponse response) {
		
	}

	private void showIndex(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.�õ���ҳ��Ϣ�б�
		List<Category> list = cs.findAllCategorys();
		//2.�õ�ͼ���б�Ҫ��ҳ
		PageBean pb = new PageBean();
		String pageNo = request.getParameter("pageNo");
		if(pageNo!=null && !"".equals(pageNo)){
			pb.setPageNo(Integer.valueOf(pageNo));
		}
		
		//3.��ҳ��ѯ
		bs.findBooksByPageBean(pb);
		pb.setUrl(request.getContextPath()+"/servlet/ClientServlet?op=showIndex");
		
		//4.�ŵ�����
		request.setAttribute("cs", list);
		request.setAttribute("page", pb);
		
		//5.ת��
		request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
