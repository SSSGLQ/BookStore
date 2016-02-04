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
 * 后台管理
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
	 * 多条件模糊搜索实现订单查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void ordersSelect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1.取出三个框的值
		String username = request.getParameter("username");
		String ordernum = request.getParameter("ordernum");
		String status = request.getParameter("status");
		//2.调用业务，根据组合条件，进行分页查询
		PageBean pb = new PageBean();
		String pageNo = request.getParameter("pageNo");
		if(pageNo!=null){
			pb.setPageNo(Integer.valueOf(pageNo));
		}
		//难点
		os.getOrdersByMultiSelect(pb,username,ordernum,status);
		pb.setUrl(request.getContextPath()+"/servlet/ManagerServlet?op=ordersSelect&username="+username+"&ordernum="+ordernum+"&status="+status);
		//3.将查询结果保存到request域中，
		request.setAttribute("page", pb);
		//为了让下拉框等回显数据
		if(!"null".equals(username)){
			request.setAttribute("username",username);
		}
		if(!"null".equals(ordernum)){
			request.setAttribute("ordernum",ordernum);
		}
		if(!"null".equals(status)){
			request.setAttribute("status",status);
		}
		//4.转发
		request.getRequestDispatcher("/manager/ordersManager.jsp").forward(request, response);
		
	}

	/**
	 * 添加分类
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
		//1.得到index.jsp里的值
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2.调用业务进行查询
		Customer c = customerservice.login(username, password);
		//3.判断是否为管理员
		if(c!=null && c.getRole()==1){
			//管理员身份
			request.getSession().setAttribute("loginedUser",c);
		}
		response.sendRedirect(request.getContextPath()+"/manager");//回到后台主页
		
	}

	/**
	 * 后台添加图书（文件上传）
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void addBook(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//1.设置文件上传的保存目录
		String storePath = getServletContext().getRealPath("/images");
		File file = new File(storePath);
		if(!file.exists()){
			file.mkdirs();
		}
		//2.判断是否为enctype=multipart/form-data
		boolean flag = ServletFileUpload.isMultipartContent(request);
		if(!flag){
			response.getWriter().write("没脑子，文件上传时表单的enctype一定是multipart/form-data");
			response.setHeader("Refresh", "2;URL="+request.getContextPath()+"/manager");
			return ;
		}
		//3.得到文件上传对象
		 DiskFileItemFactory factory = new DiskFileItemFactory();
		 ServletFileUpload upload = new ServletFileUpload(factory);
		//4.转化请求，得到List<FileItem>
		Book book = new Book();
		try {
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){
					//普通字段
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					BeanUtils.copyProperty(book, fieldName, fieldValue);
				}else{
					//上传字段
					String fileName = item.getName();
					fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
					String newFileName = UUID.randomUUID().toString()+"_"+fileName;
					//存入数据库中和本地中的文件名都是加了UUID，以防文件重名
					book.setImageName(newFileName);
					
					//保存文件
					InputStream is = item.getInputStream();
					OutputStream os = new FileOutputStream(storePath+File.separator+newFileName);
					IOUtils.copy(is,os);
					is.close();
					os.close();
					item.delete();
				}
				
			}
			
			//遍历完毕后，给book加入到数据库中
			book.setId(IDGenerator.genId());
			bs.addBook(book);
			showAllBooks(request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//5.遍历
	}

	/**
	 * 添加图书--->处理完毕后转发到addBook.jsp（解决展示图书类型数据的展示）
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
