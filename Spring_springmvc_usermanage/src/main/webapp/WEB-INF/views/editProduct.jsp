<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>

<div style="margin:0px auto; width:500px">


<form action="updateproduct" method="post">

name: <input name="name" value="${product.name}"> <br>
price: <input name="price" value="${product.price }"><br>
<input name="id" type="hidden" value="${product.id}"> 
<input type="submit" value="Update"/>

</form>
</div>