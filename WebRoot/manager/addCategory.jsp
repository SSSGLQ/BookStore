<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/manager/header.jsp"%>
    <h1>添加分类</h1>
    <center>
    <form action="${pageContext.request.contextPath}/servlet/ManagerServlet?op=addCategory" method="post">
    	<table border="1">
    		<tr>
    			<td>*分类名称：</td>
    			<td>
    				<input type="text" name="name"/>
    			</td>
    		</tr>
    		<tr>
    			<td>分类描述：</td>
    			<td>
    				<textarea rows="3" cols="38" name="description"></textarea>
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
