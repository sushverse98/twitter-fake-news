<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String name=request.getParameter("name");
String pass=request.getParameter("pass");

if(name.equalsIgnoreCase("admin")&&pass.equalsIgnoreCase("admin"))
{
	%>
	<script type="text/javascript">
	alert("Login Sucessfuly   "+name);
	</script>
	<%
	RequestDispatcher rd=request.getRequestDispatcher("AddDataset.jsp");
	rd.include(request, response);
}
else
{
	%>
	<script type="text/javascript">
	alert("Invalid Username or Password");
	</script>
	<%
	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
	rd.include(request, response);
}

%>
</body>
</html>