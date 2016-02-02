<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
<br/>
<br/>
	<h1>你购买了以下商品</h1>
	<center>
	<c:if test="${empty sessionScope.cart.map}">
		对不起，您没有购买任何商品
	</c:if>
	<c:if test="${!empty sessionScope.cart.map}">
	    <table width="438" border="1">
	    	<tr>
	    		<th>商品名称</th>
	    		<th>作者</th>
	    		<th>单价</th>
	    		<th>数量</th>
	    		<th>小计</th>
	    		<th>操作</th>
	    	</tr>
	    	<c:forEach items="${sessionScope.cart.map}" var="me" varStatus="vs">
	    		<tr class="${vs.index%2==0?'odd':'even'}">
		    		<td>${me.value.book.name}</td>
		    		<td>${me.value.book.author}</td>
		    		<td>${me.value.book.price}</td>
		    		<td>${me.value.num}</td>
		    		<td>${me.value.totalPrice}</td>
		    		<td>操作</td>
		    	</tr>
	    	</c:forEach>
	    	<tr>
	    		<td colspan="6" align="right">
	    			总数量：${sessionScope.cart.totleNum}应付金额：${sessionScope.cart.totlePrice}
	    			<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=genOrders">去结算</a>
	    		</td>
	    	</tr>
	    </table>
    </c:if>
    </center>
  </body>
</html>
