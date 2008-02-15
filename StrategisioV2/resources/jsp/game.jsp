<%@ page import="strategisio.visualization.WebDisplay"%>
<%@ page import="strategisio.*"%>
<%@ page import="java.io.File"%>

<%
	
    //for first testing i entered my absolute path, relativ didnt work, why ever!
  	File tmpFile = new File("C:/Program Files/Java/Tomcat/webapps/strategisio/map_config.xml");
    
    //create new Game and set Displayer to Webdisplay
  	Game tmpGame = new Game(tmpFile, "TeamA", "TeamB");
  	WebDisplay tmpDisplayer = new WebDisplay();
    tmpGame.setDisplayer(tmpDisplayer);
  	
    //position figures and items automatically on the map
  	tmpGame.generateMapAutomatically();

    //generate html output
    //BEGIN HTML-FILE
  	out.println("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"../standard.css\">");
    out.println("<script language=\"JavaScript\" src=\"../script.js\"></script><title>Test page</title></head><body>");
  	
    //HTML CONTENT
    out.println(tmpGame.display("map"));
	
    //CLOSE HTML
    out.println("</body></html>");
    
%>
  