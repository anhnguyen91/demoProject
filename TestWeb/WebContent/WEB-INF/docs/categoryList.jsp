<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Category List</title>
 </head>
 <body>
    <jsp:include page="menu.jsp"></jsp:include>
 
    <h2>Category List</h2>
 
    <p style="color: red;">${errString}</p>
    
    <c:set var="count" value="1" scope="page" />
    
    <table border="1" cellpadding="5">
       <tr>
          <th>#</th>
          <th>Name</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${lstCategory}" var="category" >
          <tr>
             <td>${count}</td>
             <td><a href="editCategory?code=${category.ctgCode}">${category.ctgName}</a></td>
             <td>
                <a href="deleteCategory?code=${category.ctgCode}">Delete</a>
             </td>
          </tr>
          <c:set var="count" value="${count + 1}" scope="page"/>
       </c:forEach>
    </table>
 <br>
    <a href="createCategory" > >>Add New Category</a>
 </body>
</html>