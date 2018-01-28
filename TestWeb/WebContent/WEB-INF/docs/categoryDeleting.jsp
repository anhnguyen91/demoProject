<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete category</title>
</head>
<body>

<jsp:include page="menu.jsp"></jsp:include>
 
      <h3>Delete Category</h3>
 
      <p style="color: red;">${errString}</p>
      
		<form method="POST" action="${pageContext.request.contextPath}/deleteCategory">
		    <table border="1" cellpadding="5">
		       <tr>
		          <th>Name</th>
		       </tr>
		          <tr>
		             <td>${category.ctgName}</td>
		          </tr>
		    </table>
		    <br>
		    <input type="hidden" value="${category.ctgCode}" name="code" />
            <input type="submit" value="Submit" />
            <a href="${pageContext.request.contextPath}/categoryList">Cancel</a>
		</form>
</body>
</html>