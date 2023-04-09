<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="model.Flight,model.FlightDAOImpl,java.text.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table {
	border: 2px solid black;
	border-collapse: collapse;
	width: 50%;
}

th, td {
	font-size: 20px;
	padding: 10px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: coral;
}

table.center {
    max-width: 500px;
  	margin: auto;

}
</style>

<meta charset="ISO-8859-1">
<title>Admin Flight List</title>
</head>
<body background = "img2.jpg">
 <jsp:include page="header.jsp" />
	<%!String Source = null, Destination = null;
	java.util.Date schdate = null;%>

	<%
		FlightDAOImpl flightDAOImpl = new FlightDAOImpl();
		FlightDAOImpl.initDataBase();
		List f = flightDAOImpl.SearchAllFlight();
		Iterator i = f.iterator();
		System.out.println(f);
	%>
	<h1 style="margin-left: 50px; mamargin-top: 50px;">
		<h1>All Flights List</h1>
	</h1>

	<button type="button" onclick="location.href='AdminWelcome.html'">Back
		to Previous Page</button>
	<TABLE class='center' BORDER="1">
		<TR>
			<Th>Flight Number</Th>
			<Th>Departure Time</Th>
			<Th>Duration in hours</Th>
			<Th>Arrival Time</Th>
			<th>Available Tickets</th>
			<Th>Price in Rs</Th>
			

		</TR>
				<%
			while (i.hasNext()) {
				%>
			<tr>
				<%
				Flight f2 = (Flight) i.next();
				%>
			<td>
				<%
					out.print(f2.getFly_Num());
				%>
			</td>
			<td>
				<%
					out.print(f2.getDeparture_time());
				%>
			</td>
			<td>
				<%
					out.print(f2.getDuration());
				%>
			</td>
			<td>
				<%
					out.print(f2.getArrival_time());
				%>
			</td>
			<td>
				<%
					out.print(f2.getSeats());
				%>
			</td>
			<td>
				<%
					out.print(f2.getPrice());
				%>
			</td>
			<td><form action="AdminUpdateFrontEnd.jsp">
					<input type="hidden" name="flynum" value="<%=f2.getFly_Num()%>" />
					<input type="submit" value="Update">
				</form></td>
			<td><form action="AdminDelete.jsp">
			
			<input type="hidden" name="flynum" value="<%=f2.getFly_Num()%>" />
					<input type="submit" value="Delete">
				</form></td>
		</tr>
		<%
			}
		%>
	</TABLE>
</body>
</html>