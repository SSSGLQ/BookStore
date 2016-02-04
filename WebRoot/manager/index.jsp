<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/manager/header.jsp"%>
    <h1>欢迎使用</h1>
    <br><br>
    <c:if test="${sessionScope.loginedUser==null }">
		    管理员登陆
		    <center>
		    <form action="${pageContext.request.contextPath }/servlet/ManagerServlet?op=loginManager" method="post">
		    	<table>
		    		<tr>
		    			<td>用户名:</td>
		    			<td><input type="text" name="username"/></td>
		    		</tr>
		    		<tr>
		    			<td>密码:</td>
		    			<td><input type="password" name="password"/></td>
		    		</tr>
		    		<tr>
		    			<td><input type="submit" value="登录"/></td>
		    		</tr>
		    	</table>
		    </form>
		    </center>
    </c:if>
    <c:if test="${!empty sessionScope.loginedUser }">
    	您好，${sessionScope.loginedUser.username }    登录成功，可以进行后台操作了 
    	 <a href="${pageContext.request.contextPath }/servlet/ManagerServlet?op=logout">注销</a>
    </c:if>
  </body>
</html>
