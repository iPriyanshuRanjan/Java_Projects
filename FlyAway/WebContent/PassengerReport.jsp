<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%><%@page import="model.Passenger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.Flight,model.PaymentInfo,model.PassengerDAOImpl,model.FlightDAOImpl,java.util.*,model.PaymenInfoDAOImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Passenger Report</title>
</head>
<body>
<body background = "img2.jpg">
 <jsp:include page="header.jsp" />


<%
String id = request.getParameter("userId");
String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3308/";
String dbName = "Airlines";
String userId = "root";
String password = "root";

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<button type="button" onclick="location.href='AdminWelcome.html'">Back
		to Previous Page</button>
<h2 align="center"><font><strong>Retrieved Booked Details</strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>Flight Number</b></td>
<td><b>First Name</b></td>
<td><b>Last Name</b></td>
<td><b>Phone Number</b></td>
<td><b>Email ID</b></td>
<td><b>No of tickets</b></td>
<td><b>Booked From</b></td>
<td><b>Booked To</b></td>
</tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM Record";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr bgcolor="#DEB887">

<td><%=resultSet.getString("flyNum") %></td>
<td><%=resultSet.getString("firstName") %></td>
<td><%=resultSet.getString("lastName") %></td>
<td><%=resultSet.getString("phoNo") %></td>
<td><%=resultSet.getString("EmailID") %></td>
<td><%=resultSet.getString("passengers") %></td>
<td><%=resultSet.getString("Source") %></td>
<td><%=resultSet.getString("Destination") %></td>

</tr>

<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
</body>
</html>