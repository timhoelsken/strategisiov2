<%@ page import="strategisio.visualization.WebDisplay"%>
<%@ page import="strategisio.*"%>
<%@ page import="java.io.File"%>

<%

	//path to Map-File
    final String REAL_PATH = application.getRealPath("resources/map_config.xml");
    File tmpFile = new File(REAL_PATH);

    //create new Game and set Displayer to Webdisplay
  	Game tmpGame = new Game(tmpFile, "TeamA", "TeamB");
  	WebDisplay tmpDisplayer = new WebDisplay();
    tmpGame.setDisplayer(tmpDisplayer);

    session.setAttribute("globalGame", tmpGame);
    //position figures and items automatically on the map
  	tmpGame.generateMapAutomatically();

    //generate html output
    //BEGIN HTML-FILE
  	out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n\n");
    out.println("<html>\n<head>");
    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/includes/standard.css\">");
    out.println("<script type=\"text/javascript\" src=\"resources/includes/script.js\"></script>");
    out.println("<title>Test page</title>\n</head>\n\n<body>");

    //HTML CONTENT
    out.println(tmpGame.display("map"));

    //CLOSE HTML
    out.println("</body>\n</html>");

%>
