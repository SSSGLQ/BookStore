package com.itheima.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {

	private static DataSource ds =new ComboPooledDataSource("mysql");
	
	/**
	 * ���ڴӳ��л�ȡһ������
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		return ds.getConnection();
	}
	
	/**
	 * ����һ������Դ
	 * @return
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * �ر���Դ
	 * 
	 * @param rs
	 * @param st
	 * @param con
	 */
	public static void release(ResultSet rs, Statement st, Connection con) {
		// 6.�ر���Դ
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			st = null;
		}
		if (con != null) {
			try {
				con.close();//���õ����ˣ��϶����س���
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;//�̳߳���ҲҪ����
		}

	}
}
