<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/manager/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	  </head>
  <body>
    <br>
    <center>
	    <form action="${pageContext.request.contextPath}/servlet/ManagerServlet?op=ordersSelect" method="post">
	    		 用户名:<input type="text" name="username" value="${requestScope.username }" />
	    		 订单号:<input type="text" name="ordernum" value="${requestScope.ordernum }" />
	    		 订单状态:
	    		 <select name="status">
	    		 	<option value="0" ${requestScope.status=='0' ? "selected":"" }>未付款</option>
	    		 	<option value="1" ${requestScope.status=='1' ? "selected":"" }>已付款</option>
	    		 	<option value="2" ${requestScope.status=='2' ? "selected":"" }>已发货</option>
	    		 </select>
	    		 <input type="submit"  value="搜索" />
	    </form>
	    
	    <c:if test="${empty requestScope.page.recordes }">
	    		<center>没有搜索到相关记录</center>
	    </c:if>
	    <c:if test="${! empty requestScope.page.recordes }">
	    <table border="1" width="600">
	    		<tr>
	    		<th>订单号</th>
	    		<th>数量</th>
	    		<th>应付金额</th>
	    		<th>订单状态</th>
	    		<th>明细</th>
	    		<th>操作</th>
	    	</tr>
	    	<c:forEach items="${page.recordes}" var="o" varStatus="vs">
	    		<tr class="${vs.index%2==0?'odd':'even'}">
		    		<td>${o.ordernum}</td>
		    		<td>${o.num}</td>
		    		<td>${o.price}</td>
		    		<td>${o.status==0?'未付款':(o.status==1?'已付款':'已发货')}
		    			
		    		</td>
		    		<td>查看明细</td>
		    		<td>修改状态</td>
		    	</tr>
		    </c:forEach>
	    </table>
	    </c:if>
	  <%@include file="/commons/page.jsp" %>
	  </center>
  </body>
</html>
