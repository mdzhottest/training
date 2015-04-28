<%@page import = "com.gcit.training.lws.domain.Author" %>
<%@page import = "java.util.*" %>
<%@page import= "com.gcit.training.lws.domain.Publisher" %>
<%@page import="com.gcit.training.lws.service.AdministratorService"%>

<% 
	List<Author> authors = new AdministratorService().getAuthors();
	List<Publisher> publishers = new AdministratorService().getPublishers();
%>

<%@include file="include.html"%>
	<form action = "addBook" method="post">
		<table class = "table">
			<tr>
				<td>Enter Book Name: </td>
				<td><input type="text" name = "title"/></td>
			
			</tr>
			<tr>
				<td>Enter Book Name: </td>
				<td>
					<select name="authorId" multiple="multiple">
						<% for(Author a : authors) { %>
							<option value="<%=a.getAuthorId() %>"><%=a.getAuthorName()%></option>
						<% } %>
					</select>
				</td>
			</tr>
			<tr> 
				<td>Enter Book Publisher: </td>
				<td>
					<select name = "publisherId">
					<% for (Publisher p: publishers){ %>
						<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
					<%} %>
					</select>
				</td>			
			</tr>
		</table>
		<input type="submit"/>
	
	</form>