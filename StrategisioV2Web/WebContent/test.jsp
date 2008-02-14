<%
if (request.getParameter("message") != null)
{
	if (request.getParameter("message").equals("1")){
		out.println("Eine Nachricht wurde verarbeitet!");
	} else {
 		out.println("Es wurde etwas anderes als \"1\" eingegeben.");
 	}
} else {
 	out.println("Keinen Parameter bekommen");
}
%>