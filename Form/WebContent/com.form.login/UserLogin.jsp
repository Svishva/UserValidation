<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>

<script type="text/javascript">
function validate() {
	
	var p1 = document.forms["form2"]["password"].value;
	var p2 = document.forms["form2"]["email"].value;
	
	if(p2.length != 0 ){}
	else{
		alert("Please enter your email")
		return false;
		
	}
	
	if(p1.length > 6){}
	else {
		alert("Please enter password greater than 6 characters")
		return false;
	}
	
}

</script>
<body>
<center>
<br><br><br>
Login Here <br><br>
<form action="controller" method="post" name="form2" onsubmit="return validate()">
Enter email  <input type="email" name="email" ><br>
Enter Password <input type="password" name="password" ><br>
<input type="submit"  name="login"> 
</form>
<br><br><br>

<a href="home"> Register </a> Here
</center>
</body>
</html>