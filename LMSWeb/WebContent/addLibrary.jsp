<%@page import = "com.gcit.training.lws.domain.LibraryBranch"%>
<%@page import = "com.gcit.training.lws.service.AdministratorService"%>


<%@include file = "include.html"%>

	<form action="addBranch" method="post">
		<table class = "table">
			<tr>
				<td>Enter Branch Name: </td> 
				<td> <input type="text" name="branchName"/></td>
			</tr>			
			<tr>
				<td>Enter Branch Address: </td>
				<td><input type = "text" name="branchAddress"/></td>
			</tr>
		</table>
		<input type="submit"/>
	
	</form>
	
		