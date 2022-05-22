<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="logic.StudentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Chosen</title>
</head>
<body>
	<h1>Student List</h1>

	<%
	StudentBean std = new StudentBean();
	std = (StudentBean) session.getAttribute("studentbean");
	%>

	<h4>
		Student ID:
		<%=std.getStudentID()%>
	</h4>

	<h4>
		Name:
		<%=std.getName()%>
	</h4>

	<h4>
		Address:
		<%=std.getAddress()%>
	</h4>

	<h4>
		City:
		<%=std.getCity()%>
	</h4>

	<h4>
		State:
		<%=std.getState()%>
	</h4>

	<h4>
		ZipCode:
		<%=std.getZipcode()%>
	</h4>

	<h4>
		Telephone:
		<%=std.getTelephone()%>
	</h4>

	<h4>
		Email:
		<%=std.getEmail()%>
	</h4>

	<h4>
		URL:
		<%=std.getURL()%>
	</h4>
	
	<h4>
		InterestedArea:
		<%=std.getInterestedArea()%>
	</h4>
	
	<h4>
		highestDiff:
		<%=std.gethighestDiff()%>
	</h4>

	<h4>
		Date of Survey:
		<%=std.getDOS()%>
	</h4>


</body>
</html>