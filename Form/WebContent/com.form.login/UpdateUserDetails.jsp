<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<style>
 body {
    height: 200px;
    width: 400px;

    position: fixed;
    top: 50%;
    left: 50%;
    margin-top: -100px;
    margin-left: -200px;
}
</style>
<body>
<center>
<form action="controller" method="post" >

<table>
<tbody>

<tr>

	Update Details
	</tr>
	<br><br>
<tr>
<td> Gender</td>
<td>
 <input type="radio" name="gender" value="MALE" checked="checked"> Male<br>
  <input type="radio" name="gender" value="FEMALE"  > Female<br>
  <input type="radio" name="gender" value="OTHER"  > Other<br><br>
 
</td>
</tr>


<tr>
	<td>Highest Qualification</td>
	<td>
	<select name="education" required>
  <option value="">None</option>
  <option value="B.E"  >  B.E  </option>
  <option value="M.E"  >  M.E  </option>
  <option value="M.Phil" >  M.Phil  </option>
</select>
</td><br>
</tr>


</tbody>

</table><br><br>
<input type="submit" name="update" value="Update">
</form>
</center>
</body>
</html>