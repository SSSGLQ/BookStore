<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
商品分类：
<c:forEach items="${cs}" var="c">
	<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=showCategoryPageRecords&categoryId=${c.id}">${c.name}</a>
</c:forEach>
<br/>
<center>
<br/>
    <table width="400">
    	<tr>
    		<c:forEach items="${page.recordes}" var="b">
    		<td>
    			<img src="${pageContext.request.contextPath}/images/${b.imageName}" width="120" height="160"/><br/>
    			书名：${b.name}<br/>
    			作者：${b.author}<br/>
    			原价:<strike>1000</strike><br/>
    			跳楼价：${b.price}<br/>
    			<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=buyBook&bookId=${b.id}">购买</a>
    		</td>
    		</c:forEach>
    	</tr>
    </table>
</center>
<%@include file="/commons/page.jsp" %>
  </body>
</html>
