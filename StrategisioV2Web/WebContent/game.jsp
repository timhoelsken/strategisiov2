<%@ page import="strategisio.visualization.WebDisplay"%>
<%@ page import="strategisio.*"%>
<%@ page import="java.io.File"%>

<%

    //for first testing i entered my absolute path, relativ didnt work, why ever!
  	File tmpFile = new File("workspace/StrategisioV2Web/WebContent/resources/map_config.xml");

    //create new Game and set Displayer to Webdisplay
  	Game tmpGame = new Game(tmpFile, "TeamA", "TeamB");
  	WebDisplay tmpDisplayer = new WebDisplay();
    tmpGame.setDisplayer(tmpDisplayer);

    //position figures and items automatically on the map
  	tmpGame.generateMapAutomatically();

    //generate html output
    //BEGIN HTML-FILE
  	out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n\n");
    out.println("<html>\n<head>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/includes/standard.css\">");
    out.println("<script language=\"JavaScript\" src=\"resources/includes/script.js\"></script>");
    out.println("<title>Test page</title>\n</head>\n\n<body>");

    //HTML CONTENT
    out.println(tmpGame.display("map"));

    out.println("<div class=\"field\" onClick=\"macheRequest('test.jsp', '1');\">hier AJAX</div><br><br>");
    
    out.println("<p>AJAX-OutPut HIER:<br><div id=\"output\"></div></p>");
    //CLOSE HTML
    out.println("</body>\n</html>");

%>
