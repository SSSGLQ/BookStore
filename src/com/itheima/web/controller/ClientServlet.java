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
import com.itheima.service.BookService;
import com.itheima.service.CategoryService;
import com.itheima.service.impl.BookServiceImpl;
import com.itheima.service.impl.CategoryServiceImpl;
import com.itheima.utils.PageBean;
import com.itheima.web.form.Cart;

public class ClientServlet extends HttpServlet {
	
	private BookService bs = new BookServiceImpl();
	private CategoryService cs = new CategoryServiceImpl();
	
	private static String SHOWINDEX = "showIndex";
	private static String BUYBOOK = "buyBook";
	private static String SHOWCATEGORYPAGERECORDS = "showCategoryPageRecords";
	
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
