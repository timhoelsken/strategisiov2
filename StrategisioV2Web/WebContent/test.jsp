<%@ page import="strategisio.*"%>

<%
if (request.getParameter("message") != null)
{
	if (request.getParameter("message").equals("1")){
		Game tmpGame = null;
	  tmpGame = (Game) session.getAttribute("globalGame");
	  
	  out.println(tmpGame.display("map"));
	} else {
 		out.println("Es wurde etwas anderes als \"1\" eingegeben.");
 	}
} else {
 	out.println("Keinen Parameter bekommen");
}
%>