package com.itheima.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.utils.PaymentUtil;
import com.itheima.utils.PropertiesUtil;

/**
 * 封装数据请求第三方支付
 * @author yewmf
 *
 */
public class PayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.获取pay.jsp传来的参数
		String orderid = request.getParameter("orderid");
		String money = request.getParameter("money");
		String pd_FrpId = request.getParameter("pd_FrpId");
		
		//2.补全封装要请求第三方支付接口的参数（具体参考第三方支付文档（易宝yeebay））
		String p0_Cmd = "Buy";
		String p1_MerId = PropertiesUtil.getValue("p1_MerId");
		String p2_Order = orderid;
		String p3_Amt = money;
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = PropertiesUtil.getValue("responseURL");//告知第三方在支付完成后，交给网站的哪个页面
		String p9_SAF = "1";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, PropertiesUtil.getValue("keyValue"));
		
		request.setAttribute("p0_Cmd",p0_Cmd );
		request.setAttribute("p1_MerId",p1_MerId );
		request.setAttribute("p2_Order",p2_Order );
		request.setAttribute("p3_Amt", p3_Amt);
		request.setAttribute("p4_Cur",p4_Cur );
		request.setAttribute("p5_Pid",p5_Pid );
		request.setAttribute("p6_Pcat",p6_Pcat );
		request.setAttribute("p7_Pdesc",p7_Pdesc );
		request.setAttribute("p8_Url",p8_Url );
		request.setAttribute("p9_SAF",p9_SAF );
		request.setAttribute("pa_MP",pa_MP );
		request.setAttribute("pd_FrpId",pd_FrpId );
		request.setAttribute("pr_NeedResponse",pr_NeedResponse );
		request.setAttribute("hmac",hmac );
		request.getRequestDispatcher("/sure.jsp").forward(request, response);		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
