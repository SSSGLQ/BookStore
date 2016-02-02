<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/header.jsp"%>
<br/>
<br/>
	<h1>用户登录</h1>
	<center>
	<form action="${pageContext.request.contextPath}/servlet/ClientServlet?op=login" method="post">
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
	    			<input type="password" name="password"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td colspan="2">
	    			<input type="submit" value="登陆"/>
	    		</td>
	    	</tr>
	    </table>
	   </form>
	   </center>
  </body>
</html>
