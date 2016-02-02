<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
<br/>
<br/>
	<h1>新用户注册</h1>
	<center>
	<form action="${pageContext.request.contextPath}/servlet/ClientServlet?op=regist" method="post">
	    <table width="438" border="1">
	    	<tr>
	    		<td>*用户名：</td>
	    		<td>
	    			<input type="text" name="username"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>*密码：</td>
	    		<td>
	    			<input type="text" name="password"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>*电话：</td>
	    		<td>
	    			<input type="text" name="phone"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>*地址：</td>
	    		<td>
	    			<input type="text" name="address"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>*邮箱：</td>
	    		<td>
	    			<input type="text" name="email"/>
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
