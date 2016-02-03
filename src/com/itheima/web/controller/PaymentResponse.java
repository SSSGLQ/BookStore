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
 * ���������֧��
 * @author yewmf
 *
 */
public class PaymentResponse extends HttpServlet {
	private static String PAY_SUCCESS = "1";//֧���ɹ�
	private static String REDIRECTION = "1";//���׽���������ͣ�������ض���
	private static String P2PCONECTION = "2";//���׽���������ͣ���������Ե�ͨѶ
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1.��ȡ������֧���������������
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd= request.getParameter("r0_Cmd");
		String r1_Code= request.getParameter("r1_Code");//1��ʾ֧���ɹ�
		String r2_TrxId= request.getParameter("r2_TrxId");
		String r3_Amt= request.getParameter("r3_Amt");
		String r4_Cur= request.getParameter("r4_Cur");
		String r5_Pid= request.getParameter("r5_Pid");
		String r6_Order= request.getParameter("r6_Order");//������
		String r7_Uid= request.getParameter("r7_Uid");
		String r8_MP= request.getParameter("r8_MP");
		String r9_BType= request.getParameter("r9_BType");//�����2�ǵ�Ե�
		String hmac = request.getParameter("hmac");
		
		//2.��֤���������Ƿ񱻴۸Ĺ�
		boolean b = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, PropertiesUtil.getValue("keyValue"));
		if(!b){
			response.getWriter().write("���ؽ����Ϣ�п��� ���۸�");
			return;
		}
		if(PAY_SUCCESS.equals(r1_Code)){
			if(P2PCONECTION.equals(r9_BType) ||REDIRECTION.equals(r9_BType)){
				OrdersService os = new OrdersServiceImpl();
				os.update(r6_Order);
			}
			response.sendRedirect(request.getContextPath()+"/servlet/ClientServlet?op=showOrders");//�ض�����ʾ������ҳ��
		}else{
			response.getWriter().write("֧��ʧ��");
			return;
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
