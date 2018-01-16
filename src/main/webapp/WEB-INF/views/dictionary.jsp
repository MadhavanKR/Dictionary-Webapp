<%@include file="commons/header.jspf" %>	
<div class="container">
<form method="post" action="dictionary" target="_self">
<h1 align="center"> Dictionary </h1>
<table border = 1 class="table table-striped">
<tr> <td> <label> Word:</label> </td> <td> <input type="text" name="word" class="form-control"/> 
 <%
	if(request.getParameter("errorMessage")!=null)
	{
		out.println("<div class=\"alert alert-danger\">"+request.getParameter("errorMessage")+"</div>");
	}
%> </td> </tr> 
<tr> <td colspan=2>	<input align="center" type="submit" class="form-control"/> </td> </tr>
</form>
</table>

<h3> Meanings for word ${word} are: </h3>
<ul class="list-group">
${meaning}
</ul>
</div>
<%@include file="commons/footer.jspf" %>	