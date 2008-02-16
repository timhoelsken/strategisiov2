
<%@ page import="strategisio.*"%>
<%@ page import="java.util.ArrayList" %>

<%
if (request.getParameter("data") != null)
{
  	String tmpRequestData = request.getParameter("data");
  	
  	String[] tmpCurrentCoordinates = tmpRequestData.split("/");
  	Game tmpGame = null;
	tmpGame = (Game) session.getAttribute("globalGame");
	
	
	
	if (tmpGame.fieldIsSetByPlaceable(Integer.parseInt(tmpCurrentCoordinates[0]), Integer.parseInt(tmpCurrentCoordinates[1]))){
	  
	  String tmpOutput = "";		
	  
	  ArrayList <int[]> tmpArea = tmpGame.getMovingArea(Integer.parseInt(tmpCurrentCoordinates[0]), Integer.parseInt(tmpCurrentCoordinates[1]));
	  int[] tmpPossibleCoordinates;

	  for (int i=0; i<tmpArea.size();i++){
	    
	    	tmpPossibleCoordinates = tmpArea.get(i);
		  tmpOutput += tmpPossibleCoordinates[0] + "/" + tmpPossibleCoordinates[1] + ";";
		}
	
	  out.println(tmpOutput);
	}
}
%>