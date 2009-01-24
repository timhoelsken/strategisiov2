<%@ page import="strategisio.*"%>

<%
       /*
       * File for refreshing the browser page
       *
       * Shows the current globalGame-map
       *
       */

      Game tmpGame = (Game) application.getAttribute("globalGame");

	  String tmpPlayerId = (String) session.getAttribute("playerId");

      out.println(tmpGame.display(tmpPlayerId));
%>