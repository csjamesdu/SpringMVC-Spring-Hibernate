<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Category Page</title>
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
<h1>Category List</h1>

<form name="categoryList" action="" method="post">
	<table>
		<tr>
			<th>ID</th>
			<th>NAME</th>
			<th>GRADE</th>
			<th>ISLEAF</th>
			<th>ADD Category</th>
			<th>EDIT</th>
			<th>DELETE</th>
			<th>ADD PPRODUCTS</th>
		</tr>
		
		<c:forEach items="${categoryList}" var="category">
			<tr>
				<td>${category.id}</td>
				<td>${category.name}</td>
				<td>${category.grade}</td>
				<td>${category.isleaf}</td>	
				<td><a href="addcategory?parent_id=${category.id}">ADD Sub-Category</a></td>
				<td><a href="editcategory?id=${category.id}">Edit</a></td>
				<td>
					<c:if test="${category.id == 2}">
						<a href="deletecategory?id=${category.id}">Add Default Categories</a>
					</c:if>
					<c:if test="${category.id != 2}">
						<a href="deletecategory?id=${category.id}">Delete</a>
					</c:if>
				</td>
				<td>
				<c:if test="${category.isleaf==1}">
					<a href="showproductsundercategory?category_id=${category.id}">ADD Products</a>
				</c:if>
				</td>				
			</tr>
		</c:forEach>
	</table>
</form>
<br>
<br>
<a href="index">[INDEX]</a>
<br>
<br>
<a href="listproduct">[PRODUCT]</a>

