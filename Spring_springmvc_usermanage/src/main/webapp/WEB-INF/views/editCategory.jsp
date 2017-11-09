<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Category Add Page</title>
<script type="text/javascript">
function deleteMultiProducts(){
 	document.productList.submit();
}

function editMultiProducts(){
	document.specialOps.action="${pageContext.request.contextPath}/editmultiproducts";
	document.specialOps.submit();
}

function insertMultiProducts(){
	document.specialOps.action="${pageContext.request.contextPath}/autoinsertproducts";
	document.specialOps.submit();
}

function insertProductsRollback(){
	document.specialOps.action="${pageContext.request.contextPath}/autoinsertrollback";
	document.specialOps.submit();
}
</script>
<style type="text/css">
table, th, td {
	border: 1px solid;
	
}

td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

th {
	font-family: Arial, sans-serif;
	font-size: 20px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}
</style>
</head>

<body>
<h1>Category Details</h1>
<form:form action="${pageContext.request.contextPath}/updateeditcategory" commandName="category">
		<table>
			<c:if test="${!empty category.name}">
				<tr>
					<td><form:label path="id">
							<spring:message text="ID" />
						</form:label></td>
					<td><form:input path="id" readonly="true" size="8"
							disabled="true" /> <form:hidden path="id" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${!empty category.name}">
						<input type="submit" value="<spring:message text="Edit Category"/>" />
					</c:if> <c:if test="${empty category.name}">
						<input type="submit" value="<spring:message text="Add Category"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>
