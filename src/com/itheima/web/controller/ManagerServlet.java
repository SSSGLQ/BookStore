package com.itheima.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.itheima.domain.Book;
import com.itheima.domain.Category;
import com.itheima.domain.Customer;
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
 * ��̨����
 * @author yewmf
 *
 */
public class ManagerServlet extends HttpServlet {
	
	private static String ADDCATEGORY = "addCategory";
	private static String SHOWALLCATEGORIES = "showAllCategories";
	private static String SHOWALLBOOKS = "showAllBooks";
	private static String SHOWADDBOOKUI = "showAddBookUI";
	private static String ADDBOOK = "addBook";
	private static String LOGINMANAGER = "loginManager";
	private static String LOGOUT = "logout";
	private static String ADDCATEGORYUI = "addCategoryUI";
	private static String ORDERSSELECT = "ordersSelect";
	
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
		}else if(SHOWADDBOOKUI.equals(op)){
			showAddBookUI(request,response);
		}else if(ADDBOOK.equals(op)){
			addBook(request,response);
		}else if(LOGINMANAGER.equals(op)){
			loginManager(request,response);
		}else if(LOGOUT.equals(op)){
			logout(request,response);
		}else if(ADDCATEGORYUI.equals(op)){
			addCategoryUI(request,response);
		}else if(ORDERSSELECT.equals(op)){
			ordersSelect(request,response);
		}
	}

	/**
	 * ������ģ������ʵ�ֶ�����ѯ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void ordersSelect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.ȡ���������ֵ
		String username = request.getParameter("username");
		String ordernum = request.getParameter("ordernum");
		String status = request.getParameter("status");
		//2.����ҵ�񣬸���������������з�ҳ��ѯ
		PageBean pb = new PageBean();
		String pageNo = request.getParameter("pageNo");
		if(pageNo!=null){
			pb.setPageNo(Integer.valueOf(pageNo));
		}
		//�ѵ�
		os.getOrdersByMultiSelect(pb,username,ordernum,status);
		pb.setUrl(request.getContextPath()+"/servlet/ManagerServlet?op=ordersSelect&username="+username+"&ordernum="+ordernum+"&status="+status);
		//3.����ѯ������浽request���У�
		request.setAttribute("page", pb);
		//Ϊ����������Ȼ�������
		if(!"null".equals(username)){
			request.setAttribute("username",username);
		}
		if(!"null".equals(ordernum)){
			request.setAttribute("ordernum",ordernum);
		}
		if(!"null".equals(status)){
			request.setAttribute("status",status);
		}
		//4.ת��
		request.getRequestDispatcher("/manager/ordersManager.jsp").forward(request, response);
		
	}

	/**
	 * ��ӷ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void addCategoryUI(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath()+"/manager/addCategory.jsp");
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("loginedUser");
		response.sendRedirect(request.getContextPath()+"/manager");
	}

	private void loginManager(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//1.�õ�index.jsp���ֵ
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2.����ҵ����в�ѯ
		Customer c = customerservice.login(username, password);
		//3.�ж��Ƿ�Ϊ����Ա
		if(c!=null && c.getRole()==1){
			//����Ա���
			request.getSession().setAttribute("loginedUser",c);
		}
		response.sendRedirect(request.getContextPath()+"/manager");//�ص���̨��ҳ
		
	}

	/**
	 * ��̨���ͼ�飨�ļ��ϴ���
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//1.�����ļ��ϴ��ı���Ŀ¼
		String storePath = getServletContext().getRealPath("/images");
		File file = new File(storePath);
		if(!file.exists()){
			file.mkdirs();
		}
		//2.�ж��Ƿ�Ϊenctype=multipart/form-data
		boolean flag = ServletFileUpload.isMultipartContent(request);
		if(!flag){
			response.getWriter().write("û���ӣ��ļ��ϴ�ʱ����enctypeһ����multipart/form-data");
			response.setHeader("Refresh", "2;URL="+request.getContextPath()+"/manager");
			return ;
		}
		//3.�õ��ļ��ϴ�����
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		 ServletFileUpload upload = new ServletFileUpload(factory);
		//4.ת�����󣬵õ�List<FileItem>
		Book book = new Book();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){
					//��ͨ�ֶ�
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					BeanUtils.copyProperty(book, fieldName, fieldValue);
				}else{
					//�ϴ��ֶ�
					String fileName = item.getName();
					fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
					String newFileName = UUID.randomUUID().toString()+"_"+fileName;
					//�������ݿ��кͱ����е��ļ������Ǽ���UUID���Է��ļ�����
					book.setImageName(newFileName);
					
					//�����ļ�
					InputStream is = item.getInputStream();
					OutputStream os = new FileOutputStream(storePath+File.separator+newFileName);
					IOUtils.copy(is,os);
					is.close();
					os.close();
					item.delete();
				}
				
			}
			
			//������Ϻ󣬸�book���뵽���ݿ���
			book.setId(IDGenerator.genId());
			bs.addBook(book);
			showAllBooks(request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//5.����
	}

	/**
	 * ���ͼ��--->������Ϻ�ת����addBook.jsp�����չʾͼ���������ݵ�չʾ��
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showAddBookUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> list = cs.findAllCategorys();
		request.setAttribute("cs", list);
		request.getRequestDispatcher("/manager/addBook.jsp").forward(request, response);
	}

	/**
	 * ��ѯͼ��---���ҷ�ҳչʾ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showAllBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//2.�õ�ͼ���б�Ҫ��ҳ
		PageBean pb = new PageBean();
		String pageNo = request.getParameter("pageNo");
		if(pageNo!=null && !"".equals(pageNo)){
			pb.setPageNo(Integer.valueOf(pageNo));
		}
		
		//3.��ҳ��ѯ
		bs.findBooksByPageBean(pb);
		pb.setUrl(request.getContextPath()+"/servlet/ManagerServlet?op=showAllBooks");
		
		//4.�ŵ�����
		request.setAttribute("page", pb);
		
		//5.ת��
		request.getRequestDispatcher("/manager/listBooks.jsp").forward(request, response);
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
