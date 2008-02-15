<%@page import="strategisio.exceptions.CoordinateOutOfIndexException"%>
<%@ page import="strategisio.*"%>

<%
if (request.getParameter("data") != null)
{
  	String tmpRequestData = request.getParameter("data");
  	
  	String[] tmpCurrentCoordinates = tmpRequestData.split("/");
  	Game tmpGame = null;
	tmpGame = (Game) session.getAttribute("globalGame");
	
	
	
	if (tmpGame.fieldIsSetByPlaceable(Integer.parseInt(tmpCurrentCoordinates[0]), Integer.parseInt(tmpCurrentCoordinates[1]))){
	  
	  String tmpOutput = "";		
	  out.println(tmpCurrentCoordinates[0] + " und " + tmpCurrentCoordinates[1]);
	  
	  // get the Area throws an error, but i dont know why!
	  /*
	  int[][] tmpArea = tmpGame.getMovingArea(Integer.parseInt(tmpCurrentCoordinates[0]), Integer.parseInt(tmpCurrentCoordinates[1]));
	  
		
	  for (int i=0; i<tmpArea.length;i++){
		  tmpOutput += tmpArea[i][0] + "/" + tmpArea[i][1] + ";";
		}*/
		
	}
}
%>