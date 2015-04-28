<%@page import="com.gcit.training.lws.domain.Book"%>
<%@page import="com.gcit.training.lws.domain.Author"%>
<%@page import="com.gcit.training.lws.service.AdministratorService"%>


<%String bookToEdit = request.getParameter("bookIdToEdit");
Book b = new AdministratorService().getBook(Integer.parseInt(bookToEdit));
%>


<div class="modal-body">

	<form action="editBook" method="get">
		Enter Book title:<input type="text" name="title" value="<%=b.getTitle()%>"/>
		<input type="hidden" value = "<%=b.getBookid()%>" name ="bookId"/>
		<input type="submit"/>
	</form>


</div>