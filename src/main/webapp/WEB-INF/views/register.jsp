

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Form</title>
</head>
<body>


<h1 align="center"> Register Here</h1>
<hr/>

<div align="center">

<form:form action="ProcessRegistration" method="POST"  modelAttribute="reg">

Username: <form:input type="text" path="username" name="username"/>
<br/>
<br/>

Password: <form:input type="text"  path="password" name="password"/>
<br/>
<br/>

<input type="submit" value="register">





</form:form>

</div>

</body>
</html>