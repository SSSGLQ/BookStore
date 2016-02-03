<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/manager/header.jsp"%>
    <h1>添加书籍</h1>
    <center>
    <form action="${pageContext.request.contextPath}/servlet/ManagerServlet?op=addBook" method="post" enctype="multipart/form-data">
    	<table border="1">
    		<tr>
    			<td>*书名：</td>
    			<td>
    				<input type="text" name="name"/>
    			</td>
    		</tr>
    		<tr>
    			<td>作者：</td>
    			<td>
    				<input type="text" name="author"/>
    			</td>
    		</tr>
    		<tr>
    			<td>单价：</td>
    			<td>
    				<input type="text" name="price"/>
    			</td>
    		</tr>
    		<tr>
    			<td>图片：</td>
    			<td>
    				<input type="file" name="image"/>
    			</td>
    		</tr>
    		<tr>
    			<td>描述：</td>
    			<td>
    				<textarea cols="38" rows="3" name="description"></textarea>
    			</td>
    		</tr>
    		<tr>
    			<td>所属分类</td>
    			<td>
    				<select name="categoryid">
    					<c:forEach items="${cs}" var="c">
    						<option value="${c.id}">${c.name}</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="保存"/>
    			</td>
    		</tr>
    	</table>
    </form>
    </center>
  </body>
</html>
