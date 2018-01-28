<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Product List</title>
 </head>
 <body>
    <jsp:include page="menu.jsp"></jsp:include>
 
    <h2>Product List</h2>
 
    <p style="color: red;">${errString}</p>
    
    <c:set var="count" value="1" scope="page" />
    
    <table border="1" cellpadding="5">
       <tr>
          <th>#</th>
          <th>Name</th>
          <th>Category</th>
          <th>Price</th>
          <th>Number of remain items</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${lstProduct}" var="product" >
          <tr>
             <td>${count}</td>
             <td><a href="editProduct?code=${product.proCode}">${product.proName}</a></td>
             <td>${product.ctgName}</td>
             <td>${product.price}</td>
             <td>${product.numberOfRemain}</td>
             <td>
                <a href="deleteProduct?code=${product.proCode}">Delete</a>
             </td>
          </tr>
          <c:set var="count" value="${count + 1}" scope="page"/>
       </c:forEach>
    </table>
 <br>
    <a href="createProduct" > >>Add New Product</a>
 
 </body>
</html>