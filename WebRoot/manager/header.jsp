<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.itheima.com/myjstl/myfns" prefix="myfn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我的网站后台管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
  </head>
  
  <body>
  <br/>
  <br/>
  <h1>书店后台管理</h1>
  <br/>
  ${msg}
  <a href="${pageContext.request.contextPath}/servlet/ManagerServlet?op=addCategoryUI">添加分类</a>
  <a href="${pageContext.request.contextPath}/servlet/ManagerServlet?op=showAllCategories">查询分类</a>
  <a href="${pageContext.request.contextPath}/servlet/ManagerServlet?op=showAddBookUI">添加图书</a>
  <a href="${pageContext.request.contextPath}/servlet/ManagerServlet?op=showAllBooks">查询图书</a>
  <a href="${pageContext.request.contextPath}/servlet/ManagerServlet?op=ordersSelect">订单管理</a>
	<br/><br/><br/>