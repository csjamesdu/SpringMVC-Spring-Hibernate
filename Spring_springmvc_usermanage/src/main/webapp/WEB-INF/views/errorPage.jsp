<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <title>ERROR PAGE</title>
  <body>
    Ooops...an Error: ${errorMessage} occurred... <br>
    <br>
    <a href="index">[INDEX]</a>
    <br>
    <br>    
    <a href="listproduct">[PRODUCT]</a>
    <br>
    <br>
    <a href="listcategory">[CATEGORY]</a>
    <br>
    <br>
  </body>
</html>
