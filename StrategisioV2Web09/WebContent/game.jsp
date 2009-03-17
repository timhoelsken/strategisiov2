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
            out
                .println("<script>alert('Due to an error in the map size the game could not be loaded. You will be redirected to the lobby.');location.href='#';</script>");
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
          if (tmpPlayerId == null || "".equals(tmpPlayerId)) {
            if (tmpGame.isFull()) {
              // third player
              out
                  .println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n\n");
              out.println("<html>\n<head>");
              out.println("<title>StrategisioDEV</title>\n</head>\n\n<body>\n");
              out.println("<p>FUCK YOU!</p>\n");
              out.println("</body>\n</html>");
            } else {
              // mark me as TeamB
              session.setAttribute("playerId", "B");
              tmpGame.setFull(true);
            }
          }
        }
      }

      tmpPlayerId = (String) session.getAttribute("playerId");
      if (tmpPlayerId != null && !"".equals(tmpPlayerId)) {
        int tmpXDimension = tmpGame.getPlayMap().getXDimension();
        int tmpYDimension = tmpGame.getPlayMap().getYDimension();

        // BEGIN HTML-FILE
        out
            .println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n\n");
        out.println("<html>\n<head>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/includes/standard.css\">");
        out
            .println("<script type=\"text/javascript\" src=\"resources/includes/javascript/import.js\"></script>");
        tmpPlayerId = (String) session.getAttribute("playerId");
        String tmpCurrentPlayer = (String) application.getAttribute("currentPlayer");
        boolean tmpRefreshMap = true;
        if (tmpPlayerId.equals(tmpCurrentPlayer)) {
          tmpRefreshMap = false;
        }
        out.println("<title>StrategisioDEV</title>\n</head>\n\n<body onload='doRefreshRequest();");

        // TODO currently the waiting player does not see a map at the beginning
        // start the refresh for the waiting player
        if (tmpRefreshMap) {
          out.println("refresh();");
        }
        out.println("'>");

        // HTML CONTENT
        out.println("<div id='overlay'></div>");

        out.println("<table><tr><td><div class='field'></div></td><td>");
        out.println("<table style='border-collapse:collapse;border:none;'><tr>");

        // coordinates
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
        out.println("</body>\n</html>");
      }
%>
