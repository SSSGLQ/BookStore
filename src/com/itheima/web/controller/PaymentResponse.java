package com.itheima.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import com.itheima.service.impl.OrdersServiceImpl;
import com.itheima.utils.PaymentUtil;
import com.itheima.utils.PropertiesUtil;

/**
 * 处理第三方支付
 * @author yewmf
 *
 */
public class PaymentResponse extends HttpServlet {
	private static String PAY_SUCCESS = "1";//支付成功
	private static String REDIRECTION = "1";//交易结果返回类型：浏览器重定向
	private static String P2PCONECTION = "2";//交易结果返回类型：服务器点对点通讯
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.获取第三方支付发来的请求参数
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd= request.getParameter("r0_Cmd");
		String r1_Code= request.getParameter("r1_Code");//1表示支付成功
		String r2_TrxId= request.getParameter("r2_TrxId");
		String r3_Amt= request.getParameter("r3_Amt");
		String r4_Cur= request.getParameter("r4_Cur");
		String r5_Pid= request.getParameter("r5_Pid");
		String r6_Order= request.getParameter("r6_Order");//订单号
		String r7_Uid= request.getParameter("r7_Uid");
		String r8_MP= request.getParameter("r8_MP");
		String r9_BType= request.getParameter("r9_BType");//如果是2是点对点
		String hmac = request.getParameter("hmac");
		
		//2.验证请求数据是否被篡改过
		boolean b = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, PropertiesUtil.getValue("keyValue"));
		if(!b){
			response.getWriter().write("返回结果信息有可能 被篡改");
			return;
		}
		if(PAY_SUCCESS.equals(r1_Code)){
			if(P2PCONECTION.equals(r9_BType) ||REDIRECTION.equals(r9_BType)){
				OrdersService os = new OrdersServiceImpl();
				os.update(r6_Order);
			}
			response.sendRedirect(request.getContextPath()+"/servlet/ClientServlet?op=showOrders");//重定向到显示订单的页面
		}else{
			response.getWriter().write("支付失败");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
