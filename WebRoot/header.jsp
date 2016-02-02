<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>欢迎光临</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
  </head>
  
  <body>
  <br/>
  <br/>
  <h1>XX网店</h1>
  <br/>
  ${msg}
  <a href="${pageContext.request.contextPath}">首页</a>
  <c:if test="${sessionScope.user==null}">
	  <a href="#">注册</a>
	  <a href="#">登录</a>
  </c:if>
  <c:if test="${sessionScope.user!=null}">
  		欢迎您：${sessionScope.user.username}<a href="#">注销</a>
  </c:if>
  
  <a href="${pageContext.request.contextPath}">我的订单</a>
  <a href="${pageContext.request.contextPath}/showCart.jsp">购物车</a>
	<br/><br/><br/>