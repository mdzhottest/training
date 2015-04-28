<%@page import="com.gcit.training.lws.domain.Borrower"%>
<%@page import="com.gcit.training.lws.service.AdministratorService"%>


<%String borrowerEditId = request.getParameter("borrowerToEdit");
Borrower b = new AdministratorService().getBorrower(Integer.parseInt(borrowerEditId));
%>


<div class="modal-body">

	<form action="editBorrower" method="post">
		Enter Borrower name:<input type="text" name="borrowerName" value="<%=b.getBorrowerName()%>"/>
		Enter Borrower Address: <input type="text" name="borrowerAddress" value = "<%b.getBorrowerAddress()%>"/>
		Enter Borrower Phone: <input type="text" name="borrowerPhone" value=<%b.getBorrowerPhone()%>/>
		<input type="hidden" value = "<%=b.getCardNo()%>" name ="cardNo"/>
		<input type="submit"/>
	</form>


</div>