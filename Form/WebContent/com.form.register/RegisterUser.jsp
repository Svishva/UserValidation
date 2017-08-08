<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<style>
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
input[type="number"] {
    -moz-appearance: textfield;
}
</style>

<script type="text/javascript">
function validate() {
	
	var p1 = document.forms["form1"]["name"].value;
	var p3 = document.forms["form1"]["phonenumber"].value;

	if(p1.length >=4 ){}
	else {
		alert("Name should be atleast 4 characters")
		return false;
	}
	
	if(p1 === "admin"){alert("Username cannot be admin")
		   return false;}
	else{ }
	
	if(p3.length===10){}
	else{
		alert("Please Enter exactl 10 numbers")
		return false;
	}
		
	
}



</script>

<body>
<center>

Register Here <br><br>
<form name="form1" action="controller" method="post" onsubmit="return validate()" >
Enter Name <input type="text" name="name" ><br>
Enter Email <input type="email" name="email" ><br>
Enter Phone Number  <input type="number" name="phonenumber" ><br>
<input type="submit"  name="register"> 
</form>
<br>
<br>
<a href="login"> Login</a>

</center>
</body>
</html>