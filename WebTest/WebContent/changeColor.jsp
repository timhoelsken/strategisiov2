<%@ page import="webtest.ColorContainer"%>

<%
	ColorContainer tmpColor = (ColorContainer) application.getAttribute("color");
	if (tmpColor == null) {
		tmpColor = new ColorContainer();
		application.setAttribute("color", tmpColor);
	}
	tmpColor.switchColor();
	out.print(tmpColor.getColor());
%>