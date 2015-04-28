<%@page import="com.gcit.training.lws.domain.Borrower"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.lws.service.AdministratorService"%>


<% List<Borrower> borrowers = new AdministratorService().getBorrowers(); 
%>

<%@include file="include.html" %>

${result} 

<table class="table">
	<tr>
		<th>Card No</th>
		<th>Borrower Name</th>
		<th>Borrower Address</th>
		<th>Borrower phone</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>

	<%
		for (Borrower a : borrowers) {
	%>
	<tr>
		<td><%=a.getCardNo()%></td>
		<td><%=a.getBorrowerName()%></td>
		<td><%=a.getBorrowerAddress()%></td>
		<td><%=a.getBorrowerPhone()%></td>
		<td><button class="btn btn-success" href="editBorrower.jsp?cardNoToEdit=<%=a.getCardNo()%>"
				data-target="#myModal1" data-toggle="modal">Edit</button></td>
		<td><button class="btn btn-danger"
				onclick="javascript:location.href='deleteBorrower?cardNo=<%=a.getCardNo()%>';">Delete</button></td>
	</tr>
	<%
		}
	%>
</table>

<div id="myModal1" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>
