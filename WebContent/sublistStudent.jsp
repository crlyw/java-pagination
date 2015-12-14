<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息</title>
</head>
<%
	// 获取上下文
	String context = request.getContextPath();
%>
<script type="text/javascript">
	
	//当前显示第几页数据
	var currentPage = ${result.currentPage};
	//一共多少页数据
	var totalPage = ${result.totalPage};
	
	function submitForm(actionUrl){
		var formElement = document.getElementById("stuForm");
		formElement.action = actionUrl;
		formElement.submit();
	}
	
	function firstPage(){
		if(currentPage == 1){
			alert("已经是第一页数据");
			return false;
		}else{
			submitForm("<%=context %>/sublist/SublistServlet?pageNum=1");
			return true;
		}
	}
	
	function nextPage(){
		if(currentPage == totalPage){
			alert("已经是最后一页数据了");
			return false;
		}else{
			submitForm("<%=context %>/sublist/SublistServlet?pageNum=" + (currentPage+1));
			return true;
		}
	}
	
	function previousPage(){
		if(currentPage == 1){
			alert("已经是第一页数据");
			return false;
		}else{
			submitForm("<%=context %>/sublist/SublistServlet?pageNum=" +(currentPage-1));
			return true;
		}
	}
	
	function lastPage(){
		if(currentPage == totalPage){
			alert("已经是最后一页数据");
			return false;
		}else{
			submitForm("<%=context %>/sublist/SublistServlet?pageNum=${result.totalPage}")
		}
	}
</script>
<body>
	
	<div style = "margin-left: 100px; margin-top:100px">
		<div>
			<font color="red">${errorMsg}</font>
		</div>
		<div>
			<form action="<%=context %>/sublist/SublistServlet" id="stuForm" method="post">
				姓名
				<input type = "text" name="stuName" id="stu_name" style="width:120px" value=""/>
				&nbsp;
				性别
				<select name = "gender" id="gender" style = "width:80px">
					<option value="0">全部</option>
					<option value="1">男</option>
					<option value="2">女</option>
				</select>
				&nbsp;&nbsp;
				<input type="submit" value="查询"/>
			</form>
		</div>
		<br>
		学生信息列表：<br>
		<br>
		
		<!-- 后台返回结果为空 -->
		<c:if test="${fn:length(result.dataList) eq 0}">
			<span>查询的结果不存在</span>
		</c:if>
		
		<!-- 后台返回结果不为空 -->
		<c:if test="${fn:length(result.dataList) gt 0}">
			<table border="1px" cellspacing="0px"
				style="border-collapse:collapse">
		
			<thead>
				<tr hegith="30">
					<th width="130">姓名</th>
					<th width="130">性别</th>
					<th width="130">年龄</th>
					<th width="190">家庭住址</th>
				</tr>
			</thead>
			<c:forEach items="${result.dataList}" var="student">
				<tr>
					<td><c:out value="${student.stuName}"></c:out></td>
					<td>
						<c:if test="${student.gender eq 1}">男</c:if>
						<c:if test="${student.gender eq 2}">女</c:if>
					</td>
					<td><c:out value="${student.age}"></c:out></td>
					<td><c:out value="${student.address}"></c:out></td>
				</tr>
			</c:forEach>		
		</table>
		<br>共${result.totalRecord}条纪录共${result.totalPage}页&nbsp;&nbsp; 当前第${result.currentPage}页&nbsp;&nbsp;
		<a href = "#" onclick="firstPage();">首页</a>&nbsp;&nbsp;
		<a href = "#" onclick="previousPage();">上一页</a>&nbsp;&nbsp;
		<a href = "#" onclick="nextPage();">下一页</a>&nbsp;&nbsp;
		<a href = "#" onclick="lastPage();">尾页</a>&nbsp;&nbsp;
		</c:if>
	</div>
</body>
</html>