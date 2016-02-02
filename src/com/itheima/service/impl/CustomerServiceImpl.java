package com.itheima.service.impl;

import com.itheima.dao.CustomerDao;
import com.itheima.dao.impl.CustomerDaoImpl;
import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.itheima.utils.Md5Utils;

public class CustomerServiceImpl implements CustomerService{
	private CustomerDao dao = new CustomerDaoImpl();
	public Customer login(String username, String password) {
		//�����Ǿ������ܺ������
		Customer c = dao.findCustomerByUseruameAndPassword(username,Md5Utils.encode(password));
		return c;
	}
	public Boolean regist(Customer c) {
		Customer customer = dao.findCustomerByName(c.getUsername());//��ѯ�û����Ƿ��Ѿ���ע��
		if(customer==null){
			c.setPassword(Md5Utils.encode(c.getPassword()));//ע��ʱ�����ݿ��д����������
			dao.saveCustomer(c);
			return true;
		}else{
			return false;
		}
	}
	public boolean active(String code) {
		//1.��ѯ���ݿ��Ƿ��������������û�
		Customer c = dao.findCustomerByCode(code);
		//2.����У������û�
		if(c!=null){
			//˵���ü�������ȷ������Ӧ���û���Ϣ
			c.setActived(1);
			dao.updateCustomer(c);//�޸ļ���״̬
		}else{
			//����������
			return false;
		}
		return true;
	}

}
