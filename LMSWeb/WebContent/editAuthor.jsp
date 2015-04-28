<%@page import = "com.gcit.training.lws.domain.Author" %>
<%@page import = "com.gcit.training.lws.service.AdministratorService" %>

<%
	String authorToEdit =  request.getParameter("authorIdToEdit");
	Author a = new AdministratorService().getAuthor(Integer.parseInt(authorToEdit));
%>

<div class="modal-body">
	<form action="editAuthor" method ="get">
		Enter Author Name: <input type="text" name = "authorName" value="<%=a.getAuthorName()%>"/>
		<input type="hidden" value = "<%=a.getAuthorId()%>" name ="authorId"/>
		<input type="submit"/>
	</form>

</div>



<!-- </div>
	<form action ="editAuthor" method="get">
		Enter Author ID: <input type="text" name= "authorId">
		Enter New Author Name:<input type="text" name ="authorName"/>
		<input type = "submit"/>
		
	
	</form> -->