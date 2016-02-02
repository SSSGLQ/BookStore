<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<br/>
<br/>
<!-- 分页开始 -->
    第${page.pageNo }页/共${page.totalPage }页&nbsp;&nbsp;
    <a href="${page.url}&pageNo=${page.prep}">上一页</a>
    <a href="${page.url}&pageNo=${page.nextp}">下一页</a>
  