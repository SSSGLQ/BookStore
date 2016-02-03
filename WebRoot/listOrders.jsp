<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
<br/>
<br/>
	<center>
	    <table width="438" border="1">
	    	<tr>
	    		<th>订单号</th>
	    		<th>数量</th>
	    		<th>应付金额</th>
	    		<th>订单状态</th>
	    		<th>明细</th>
	    		<th>操作</th>
	    	</tr>
	    	
	    	<c:forEach items="${list}" var="o" varStatus="vs">
	    		<tr class="${vs.index%2==0?'odd':'even'}">
		    		<td>${o.ordernum}</td>
		    		<td>${o.num}</td>
		    		<td>${o.price}</td>
		    		<td>${o.status==0?'未付款':(o.status==1?'已付款':'已发货')}
		    			<c:if test="${o.status==0}">
		    				<c:url value="/servlet/ClientServlet" var="url">
		    					<c:param name="op" value="payUI"></c:param>
		    					<c:param name="ordernum" value="${o.ordernum}"></c:param>
		    				</c:url>
		    				 <a href="${url}">付款</a>
		    			</c:if>
		    		</td>
		    		<td>查看</td>
		    		<td>操作</td>
		    	</tr>
	    	</c:forEach>
	    	
	    </table>
	    </center>
  </body>
</html>
