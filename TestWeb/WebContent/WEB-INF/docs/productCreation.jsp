<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create new product</title>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
       
      <h3>Create Product</h3>
       
      <p style="color: red;">${errString}</p>
       
      <form method="POST" action="${pageContext.request.contextPath}/createProduct">
         <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${product.proName}" /></td>
            </tr>
            <tr>
               <td>Category</td>
               <td><select name="category">
                       <option value="">---------------</option>
                       <c:forEach items="${lstCategory}" var="ctg">
                           <option value="${ctg.ctgCode}">${ctg.ctgName}</option>
                       </c:forEach>
                   </select>
               </td>
            </tr>
            <tr>
               <td>Price</td>
               <td><input type="text" name="price" value="${product.price}" /></td>
            </tr>
            <tr>
               <td>Number of remain items</td>
               <td><input type="text" name="numOfRemain" value="${product.numberOfRemain}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="productList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
</body>
</html>