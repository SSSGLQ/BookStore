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
	 * 用于从池中获取一个连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		return ds.getConnection();
	}
	
	/**
	 * 返回一个数据源
	 * @return
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 关闭资源
	 * 
	 * @param rs
	 * @param st
	 * @param con
	 */
	public static void release(ResultSet rs, Statement st, Connection con) {
		// 6.关闭资源
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
				con.close();//不用担心了，肯定还回池中
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;//线程池中也要回收
		}

	}
}
