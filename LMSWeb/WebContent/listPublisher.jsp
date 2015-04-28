<%@page import ="com.gcit.training.lws.domain.Publisher" %>
<%@page import ="java.util.List" %>
<%@page import ="com.gcit.training.lws.service.AdministratorService"%>

<%List<Publisher> pubs = new AdministratorService().getPublishers();%>

<%@include file="include.html"%>
${result}

<table class="table">
	<tr>
		<th>Author Id</th>
		<th>Author Name</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<% for(Publisher a: pubs){ %>
		<tr>
			<td><%= a.getAuthorId() %></td>
			<td><%=a.getAuthorName() %></td>
			<td><button class="btn btn-success" href="editAuthor.jsp?authorIdToEdit=<%= a.getAuthorId()%>"
				data-target = "#myModall" data-toggle = "modal">Edit</button></td>
				
				<td> <button class="btn btn-danger" onclick = "javaascript:location.href = 'deleteAuthor?authorId=<%=a.getAuthorId()%>';">Delete</button></td>
		</tr>

	<%} %>

</table>

<div id="myModal" class = "modal fade">
	<div class = "modal-dialog">
		<div class="modal-content"></div>
	</div>
</div>