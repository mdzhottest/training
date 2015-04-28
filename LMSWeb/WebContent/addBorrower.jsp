<%@page import = "com.gcit.training.lws.domain.Borrower"%>
<%@page import = "com.gcit.training.lws.service.AdministratorService"%>


<%@include file = "include.html"%>
	<form action="addBorrower" method="post">
		<table class = "table">
			<tr>
				<td>Enter Borrower Name: </td> 
				<td> <input type="text" name="BorrowerName"/></td>
			</tr>			
			<tr>
				<td>Enter Borrower Address: </td>
				<td><input type = "text" name="borrowerAddress"/></td>
			</tr>
			<tr>
				<td>Enter Borrower Phone: </td>
				<td><input type = "text" name="borrowerPhone"/></td>
				</tr>
		</table>
		<input type="submit"/>
	
	</form>
	
		