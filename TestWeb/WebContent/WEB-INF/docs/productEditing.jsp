<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit product</title>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
 
      <h3>Edit Product</h3>
 
      <p style="color: red;">${errString}</p>
 
      <c:if test="${not empty product}">
         <form method="POST" action="${pageContext.request.contextPath}/editProduct">
            <input type="hidden" name="code" value="${product.proCode}" />
            <table border="0">
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${product.proName}" /></td>
            </tr>
            <tr>
               <td>Category</td>
               <td><select name="category">
                       <c:forEach items="${lstCategory}" var="ctg">
                           <option value="${ctg.ctgCode}" ${product.ctgCode == ctg.ctgCode ? "selected" : ""}> ${ctg.ctgName} </option>
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
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/productList">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>
</body>
</html>