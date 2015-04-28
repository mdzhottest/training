<%@page import="com.gcit.training.lws.service.AdministratorService"%>
<%@page import="com.gcit.training.lws.domain.Book" %>
<%@page import= "java.util.List" %>


<%
	List<Book> bks = new AdministratorService().getBooks(); 

%>
<%@include file = "include.html" %>

${result}

<table class = "table">
	<tr>
		<th>Book Id</th>
		<th>Title</th>
		<th>Publisher Id</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
<%for (Book b: bks){ %>
	<tr>
		<td><%=b.getBookid()%></td>
		<td><%=b.getTitle() %></td>
		<td><%=b.getPublisher()%></td>		
		<td><button class="btn btn-success"
				href="editBook.jsp?bookIdToEdit=<%=b.getBookid()%>"
				data-target="#myModal1" data-toggle="modal">Edit</button></td>
		<td><button class="btn btn-danger" onclick = "javascript:location.href='deleteBook?bookId=<%=b.getBookid()%>';">Delete</button></td>
		
	</tr>
	
<%} %>
</table>

<div id="myModal1" class "modal fade">
	<div class="modal-dialog">
		<div class="modal-content"></div>
	</div>

</div>


