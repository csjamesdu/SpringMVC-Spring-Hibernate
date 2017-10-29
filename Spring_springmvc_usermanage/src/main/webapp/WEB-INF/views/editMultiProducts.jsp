<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<script type="text/javascript">
function updateMultiProducts(){
 	document.productList.submit();
}
</script>

<div style="margin:10px auto; width:500px">
<h1>Product Info</h1>

<form name="productList" action="${pageContext.request.contextPath}/updatemultiproducts" method="post">
	<table>
		<tr>
			
			<td>ID</td>
			<td>NAME</td>
			<td>PRICE</td>
			
		</tr>
		
		<c:forEach items="${productList}" var="product" varStatus="status">
			<tr>
				
				<td><input type="text" name="multiProducts[${status.index}].id" value="${product.id}" readonly="true"/></td>
				<td><input type="text" name="multiProducts[${status.index}].name" value="${product.name}"></td>
				<td><input type="text" name="multiProducts[${status.index}].price" value="${product.price}"></td>				
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="Update" onclick="updateMultiProducts()"/>
</form>


</div>	