<%@ page import="strategisio.visualization.WebDisplay"%>
<%@ page import="strategisio.*"%>
<%@ page import="java.io.File"%>

<%
      //path to Map-File
      final String REAL_PATH = application.getRealPath("resources/map_config.xml");
      File tmpFile = new File(REAL_PATH);

      Game tmpGame = (Game) application.getAttribute("globalGame");

      //refresh page possibility
      String tmpPlayerId = (String) session.getAttribute("playerId");
      if (tmpGame == null || "".equals(tmpPlayerId) || tmpPlayerId == null) {

        if (tmpGame == null) {
          // create new Game and set Displayer to Webdisplay
          tmpGame = new Game(tmpFile, "TeamA", "TeamB");
          WebDisplay tmpDisplayer = new WebDisplay();
          tmpGame.setDisplayer(tmpDisplayer);
          // position figures and items automatically on the map
          tmpGame.generateMapAutomatically();
          application.setAttribute("globalGame", tmpGame);

          // mark me as TeamA
          session.setAttribute("playerId", "A");
        } else {
          // mark me as TeamB
          session.setAttribute("playerId", "B");
        }
      }

      //generate html output
      //BEGIN HTML-FILE
      out
          .println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n\n");
      out.println("<html>\n<head>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/includes/standard.css\">");
      out.println("<script type=\"text/javascript\" src=\"resources/includes/script.js\"></script>");
      out.println("<title>StrategisioDEV</title>\n</head>\n\n<body onload='refresh()'><div id=\"messageBox\"></div>");

      //HTML CONTENT
      out.println("<div class=\"map\" id=\"map\" style=\"width:278.0px; height:278.0px;\"></div>");

      //CLOSE HTML
      out.println("</body>\n</html>");
%>
