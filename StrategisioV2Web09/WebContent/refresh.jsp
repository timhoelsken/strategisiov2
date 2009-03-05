<%@ page import="strategisio.*"%>

<%
       /*
       * File for refreshing the browser page
       *
       * Shows the current globalGame-map
       *
       */

	  // ID of player who is calling this refresh
	  String tmpPlayerId = (String) session.getAttribute("playerId");

	  // ID of player who is doing an action on the map right now
	  String tmpCurrentPlayerId = (String) application.getAttribute("currentPlayer");

	  // is the moving player my player?
	  if (tmpPlayerId.equals(tmpCurrentPlayerId)){

	      // YES! So I have to get the current map and the messageBox has to close!
		  Game tmpGame = (Game) application.getAttribute("globalGame");
		  out.println("+++true+++" + tmpGame.display(tmpPlayerId));
	  }
	  else{

		  // NO! I have to wait! I do not need the current map
		  // TODO do one more refesh for the waiting player after her/his turn
		  out.println("+++false+++null");
	  }

%>