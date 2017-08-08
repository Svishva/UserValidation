<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change password</title>
</head>
<script type="text/javascript">
function validate() {
	
	
	var p1 = document.forms["form2"]["newpassword1"].value;
	var p2 = document.forms["form2"]["newpassword2"].value;

	if(p1.length > 6){}
	else {
		alert("Please enter password greater than 6 characters")
		return false;
	}
	if(p2.length > 6){}
	else {
		alert("Please enter password greater than 6 characters")
		return false;
	}
		
	
	if(p1 == p2){}
	else{
		alert("Passwords Do not Match");
		return false;
		
	}
	  
	 }

</script>

<body>
<center><br><br><br>
Hai <c:out value="${sessionScope.UserName}"></c:out><br>
Please change your password<br><br>


<form name="form2" action="controller" method="post" onsubmit="return validate()">

Enter Old Password:<input type="password" name="oldpassword"><br>
Create New password:<input type="password" name="newpassword1"><br>
Enter New Password:<input type="password" name="newpassword2"><br>
<input type="submit" name="updatepassword"><br>
</form>


</center>
</body>
</html>