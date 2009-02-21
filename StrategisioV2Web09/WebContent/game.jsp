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
            e.printStackTrace();
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
      out.println("<title>StrategisioDEV</title>\n</head>\n\n<body onload='doRefreshRequest();refresh("
          + tmpRefreshMap + ");'>");

      //HTML CONTENT
      out.println("<div id='gameContent'><div id='messageBox'></div>");
      out.println("<table ><tr><td><div class='field'></div></td><td>");
      // XXX someday I'll kill you for this static coordinates... *kopfschüttel*
      // Du machst extra variable Map-Größe aber dann statische Koordinaten. Das ist doch verrückt!
      // Fast 'ne Frechheit :D
      out.println("<table style='border-collapse:collapse;border:none;'><tr>");
      char[] tmpLetters = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
          'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
      for (int i = 0; i < tmpXDimension; i++) {
        out.println("<td><div class='coordinate'>" + tmpLetters[i] + "</div></td>");
      }
      out.println("</tr></table>");
      out.println("</td></tr><tr><td>");
      out.println("<table style='border-collapse:collapse;'>");
      for (int i = 1; i <= tmpYDimension; i++) {
        out.println("<tr><td><div class='coordinate'>" + i + "</div></td></tr>");
      }
      out.println("</table>");
      out.println("</td><td style='text-align:left;vertical-align:top;'>");
      out.println("<div class='map' style='width:" + (tmpXDimension * 34.75) + "px;height:"
          + (tmpYDimension * 34.75) + "px;' id='map' ></div>");
      out.println("</td></tr></table>");

      //CLOSE HTML
      out.println("</div></body>\n</html>");
%>
