<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success</title>
</head>
<body>
<center><br><br><br>
Hai <c:out value="${sessionScope.UserName}"></c:out><br><br>

Your Password has Successfully changed<br> <br><br><br>
Go <a href="logged"> Home</a>
</center>
</body>
</html>