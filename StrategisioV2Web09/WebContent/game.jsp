<%@ page import="strategisio.visualization.WebDisplay"%>
<%@ page import="strategisio.*"%>
<%@ page import="strategisio.exceptions.MapTooLargeException"%>
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
          try {
          	tmpGame = new Game(tmpFile, "TeamA", "TeamB");
          } catch (MapTooLargeException e) {
            // TODO tell player that the mapSize is invalid
          }
          WebDisplay tmpDisplayer = new WebDisplay();
          tmpGame.setDisplayer(tmpDisplayer);
          // position figures and items automatically on the map
          tmpGame.generateMapAutomatically();
          application.setAttribute("globalGame", tmpGame);

          // mark me as TeamA
          session.setAttribute("playerId", "A");
          application.setAttribute("currentPlayer", session.getAttribute("playerId"));
        } else {
          // mark me as TeamB
          session.setAttribute("playerId", "B");
        }
      }

      int tmpXDimension = tmpGame.getPlayMap().getXDimension();
      int tmpYDimension = tmpGame.getPlayMap().getYDimension();

      //generate html output
      //BEGIN HTML-FILE
      out
          .println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n\n");
      out.println("<html>\n<head>");
      out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/includes/standard.css\">");
      out.println("<script type=\"text/javascript\" src=\"resources/includes/script.js\"></script>");
      tmpPlayerId = (String) session.getAttribute("playerId");
      String tmpCurrentPlayer = (String) application.getAttribute("currentPlayer");
      boolean tmpRefreshMap = true;
      if (tmpPlayerId.equals(tmpCurrentPlayer)) {
        tmpRefreshMap = false;
      }
      out.println("<title>StrategisioDEV</title>\n</head>\n\n<body onload='doRefreshRequest();refresh(" + tmpRefreshMap
          + ");'>");

      //HTML CONTENT
      out.println("<div id='gameContent'><div id='messageBox'></div>");
      out.println("<table ><tr><td><div class='field'></div></td><td>");
      // TODO someday I'll kill you for this static coordinates... *kopfschüttel*
      // Du machst extra variable Map-Größe aber dann statische Koordinaten. Das ist doch verrückt!
      out.println("<table style='border-collapse:collapse;border:none;'><tr><td><div class='field' style='text-align:center;'>A</div></td><td><div class='field'>B</div></td><td><div class='field'>C</div></td><td><div class='field'>D</div></td><td><div class='field'>E</div></td><td><div class='field'>F</div></td><td><div class='field'>G</div></td><td></td><td><div class='field'>H</div></td></tr></table>");
      out.println("</td></tr><tr><td>");
      out.println("<table style='border-collapse:collapse;'><tr><td><div class='field'>1</div></td></tr><tr><td><div class='field'>2</div></td></tr><tr><td><div class='field'>3</div></td></tr><tr><td><div class='field'>4</div></td></tr><tr><td><div class='field'>5</div></td></tr><tr><td><div class='field'>6</div></td></tr><tr><td><div class='field'>7</div></td></tr><tr><td><div class='field'>8</div></td></tr></table>");
      out.println("</td><td style='text-align:left;vertical-align:top;'>");
      out.println("<div class='map' style='width:" + (tmpXDimension*34.75) + "px;height:" + (tmpYDimension*34.75) + "px;' id='map' ></div>");
      out.println("</td></tr></table>");

      //CLOSE HTML
      out.println("</div></body>\n</html>");
%>
