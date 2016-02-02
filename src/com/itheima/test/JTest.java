package com.itheima.test;

import java.util.List;

import org.junit.Test;

import com.itheima.dao.BookDao;
import com.itheima.dao.CategoryDao;
import com.itheima.dao.impl.BookDaoImpl;
import com.itheima.dao.impl.CategoryDaoImpl;
import com.itheima.domain.Book;
import com.itheima.service.BookService;
import com.itheima.service.impl.BookServiceImpl;
import com.itheima.utils.PageBean;

public class JTest {
	/**
	 * 	private String id;
	private String name;
	private String author;
	private float price;
	private String imageName;//�����һ��Ŷ
	private String description;
	private String categoryid;
	 */
//	@Test
//	public void testBookAdd(){
//		BookDao dao = new BookDaoImpl();
////		dao.add(new Book("123","�ջ�����","��ʥ",100,"1.jpg","���˱���","34324"));
////		dao.add(new Book("11111","androidӦ�ÿ����ֲ�","���Ѿ�",100,"1.jpg","�ǳ�����һ��Ҫ��","43828"));
////		dao.add(new Book("11122","JavaEEӦ�ÿ���","�Ϸ�",10,"2.jpg","�ǳ�����һ��Ҫ��","34324"));
////		dao.add(new Book("11133","android","��ʥ",10,"3.jpg","�ǳ�����һ��Ҫ��","43828"));
////		dao.add(new Book("11144","androidӦ�ÿ����ֲ�2","С����",10,"4.jpg","�ǳ�����һ��Ҫ��","43828"));
////		dao.add(new Book("11155","androidӦ�ÿ����ֲ�3","С��ʥ",100,"5.jpg","�ǳ�����һ��Ҫ��","43828"));
//	}
//	
//	@Test
//	public void testBookDelete(){
//		BookDao dao = new BookDaoImpl();
//		dao.delete(11111);
//		dao.delete(11122);
//		dao.delete(11133);
//		dao.delete(11144);
//		dao.delete(1155);
//	}
	
//	@Test
//	public void testCategoryDelete(){
//		CategoryDao dao = new CategoryDaoImpl();
//		dao.delete(34324);
//		dao.delete(43828);
//	}

	@Test
	public void testfindBooksByPageBean(){
		BookService bs = new BookServiceImpl();
		PageBean pb = new PageBean();
		pb.setPageSize(2);
		pb.setPageNo(1);
		bs.findBooksByPageBean(pb);
		List<Book> list = pb.getRecordes();
		for (Book bk :list) {
			System.out.println(bk);
		}
		
	}
}
