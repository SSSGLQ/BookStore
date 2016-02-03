<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/manager/header.jsp"%>
<center>
    <table border="1" width="438">
    	<tr>
    		<th nowrap="nowrap">选择</th>
    		<th nowrap="nowrap">序号</th>
    		<th nowrap="nowrap">分类名称</th>
    		<th nowrap="nowrap">描述</th>
    		<th nowrap="nowrap">操作</th>
    	</tr>
    	<c:forEach items="${cs}" var="c" varStatus="vs">
    		<tr class="${vs.index%2==0?'odd':'even'}">
	    		<td nowrap="nowrap">
	    			<input type="checkbox" name="ids" value="${c.id}"/>
	    		</td>
	    		<td nowrap="nowrap">${vs.count}</td>
	    		<td nowrap="nowrap">${c.name}</td>
	    		<td nowrap="nowrap">${c.description}</td>
	    		<td nowrap="nowrap">
	    			<a href="javascript:alert('略')">修改</a>
	    			<a href="javascript:alert('略')">删除</a>
	    		</td>
	    	</tr>
    	</c:forEach>
    </table>
    </center>
  </body>
</html>
