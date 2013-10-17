<%@page import="java.util.ArrayList"%>
<%@page import="org.baum.app.edu.eduweb.controller.DBAccessController.UserData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

Object temp = (String)request.getAttribute("OUTPUT");

String name = "";
if(temp!=null){
	name = (String) temp;
} else {
	name = "none";
}


temp = request.getAttribute("USERLIST"); 


List<UserData> list; 
if(temp!=null){
	list = (List<UserData>) request.getAttribute("USERLIST");;
} else {
	list = new ArrayList<UserData>();
}

%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Hello User!</h2>
<h3><%=name %></h3>

<br>

<%for (UserData userData : list) {%>
	KYE:<%=userData.getKey() %><br>
	NAME:<%=userData.getName() %><br>
	AGE:<%=userData.getAge() %><br>
<%}%>

</body>
</html>