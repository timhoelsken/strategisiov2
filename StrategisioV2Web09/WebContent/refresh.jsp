<%@ page import="strategisio.*"%>

<%
       /*
       * File for refreshing the browser page
       *
       * Shows the current globalGame-map
       *
       */

      Game tmpGame = (Game) application.getAttribute("globalGame");

	  out.println(tmpGame.display("map"));
%>