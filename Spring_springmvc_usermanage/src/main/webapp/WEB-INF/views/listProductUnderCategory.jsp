<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Product Page</title>
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

function addProductUnderCategory(){
	document.productadd.action="{pageContext.request.contextPath}/addproductundercategory";
	document.productadd.submit();
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
<h1>Product List Under Category</h1>

<form name="productList" action="${pageContext.request.contextPath}/deletemultiproducts" method="post">
	<table>
		<tr>
			<th>SELECT</th>
			<th>ID</th>
			<th>NAME</th>
			<th>PRICE</th>
			<th>CATEGORY</th>
			<th>EDIT</th>
			<th>DELETE</th>
		</tr>
		
		<c:forEach items="${productList}" var="product">
			<tr>
				<td><input type="checkbox" name="products_id" value="${product.id}"/></td>
				<td>${product.id}</td>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.category.name}</td>
				<td><a href="editproduct?id=${product.id}">edit</a></td>
				<td><a href="deleteproduct?id=${product.id}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="Delete Selected" onclick="deleteMultiProducts()"/>
	<input type="button" value="Multi Edit" onclick="editMultiProducts()"/>
</form>

<h1> Add Product </h1>
<form:form name="productadd" action="addproductundercategory" commandName="productDTO">
	<table>
			<tr>
				<td><form:label path="product.name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="product.name" /></td>
			</tr>
			<tr>
				<td><form:label path="product.price">
						<spring:message text="Price" />
					</form:label></td>
				<td><form:input path="product.price" /></td>
			</tr>
			<tr>
				<td><form:label path="category_id">
						<spring:message text="CategoryID" />
					</form:label></td>
				<td><form:input path="category_id" readonly="true"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="<spring:message text="Add Product"/>" />
				</td>
			</tr>
		</table>
</form:form>
<br>
<a href="listcategory">[CATEGORY LIST]</a>
<br>
<br>
<a href="index">[INDEX]</a>

<%-- <h1> Special Operations </h1>
<form name="specialOps" method="post">	
	<input type="button" value="Auto Insert" onclick="insertMultiProducts()"/>
	<input type="button" value="Auto Insert Rollback" onclick="insertProductsRollback()"/>
</form> --%>
</body>
</html>	