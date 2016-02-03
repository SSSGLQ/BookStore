<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/manager/header.jsp"%>
<center>
    <table border="1" width="438">
    	<tr>
    		<th nowrap="nowrap">书名</th>
    		<th nowrap="nowrap">作者</th>
    		<th nowrap="nowrap">单价</th>
    		<th nowrap="nowrap">描述</th>
    		<th nowrap="nowrap">所属分类</th>
    		<th nowrap="nowrap">查看图片</th>
    		<th nowrap="nowrap">操作</th>
    	</tr>
    	<c:forEach items="${page.recordes}" var="b" varStatus="vs">
    		<tr class="${vs.index%2==0?'odd':'even'}">
	    		<td nowrap="nowrap">
	    			${b.name}
	    		</td>
	    		<td nowrap="nowrap">${b.author}</td>
	    		<td nowrap="nowrap">${b.price}</td>
	    		<td nowrap="nowrap">${b.description}</td>
	    		<td nowrap="nowrap">${myfn:getCategoryName(b.categoryid)}</td>
	    		<td nowrap="nowrap">
	    			<a href="${pageContext.request.contextPath}/images/${b.imageName}">看看</a>
	    		</td>
	    		<td nowrap="nowrap">
	    			<a href="javascript:alert('略')">修改</a>
	    			<a href="javascript:alert('略')">删除</a>
	    		</td>
	    	</tr>
    	</c:forEach>
    </table>
    </center>
    <%@include file="/commons/page.jsp"%>
    </body>
</html>
