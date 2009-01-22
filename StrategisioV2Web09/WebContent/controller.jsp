<%@ page import="strategisio.*"%>
<%@ page import="java.util.ArrayList"%>

<%
       /*
       * File for doing the game logic
       *
       * The seperator for the concatinated data in the output is set as first
       * character, because there is a linebreak, or an object or similar
       * that causes errors when reading the output. Therefore, when splitting
       * the output in the JavaScript, the first entry in the array is not read!
       *
       */

      // data and action parameter have to be set
      if (request.getParameter("data") != null && request.getParameter("action") != null) {
        String tmpRequestData = request.getParameter("data");
        String tmpRequestAction = request.getParameter("action");

        // split the coordinates of the _clicked field_ to x and y
        String[] tmpSelectedCoordinates = tmpRequestData.split("/");

        Game tmpGame = (Game) application.getAttribute("globalGame");

        // output String that is printed at the end
        String tmpOutput = "";

        // if a placed figure is clicked
        if (tmpRequestAction.equals("placed")) {

          // the action that follows on the click
          tmpOutput += "+++markedForMove+++";

          ArrayList<int[]> tmpArea = tmpGame.getMovingArea(Integer.parseInt(tmpSelectedCoordinates[0]),
              Integer.parseInt(tmpSelectedCoordinates[1]));
          int[] tmpPossibleCoordinates;

          // write the movingArea into the output
          for (int i = 0; i < tmpArea.size(); i++) {
            tmpPossibleCoordinates = tmpArea.get(i);
            tmpOutput += ";" + tmpPossibleCoordinates[0] + "/" + tmpPossibleCoordinates[1];
          }

          tmpOutput += ";";

          // print output
          out.println(tmpOutput);

          // if a figure is moving and will be placed on another field
        } else if (tmpRequestAction.equals("markedForMove")
            && request.getParameter("extendedAttribute") != null) {

          // The extended Attribute is needed to know the coordinates of the old position
          String tmpRequestExtendedAttribute = request.getParameter("extendedAttribute");

          if (!tmpRequestExtendedAttribute.equals("")) {

            // the performed action was a move
            tmpOutput += "+++Moved+++";

            String[] tmpMovingFigureCoordinates = tmpRequestExtendedAttribute.split("/");

            // do the move
            tmpGame.move(Integer.parseInt(tmpMovingFigureCoordinates[0]), Integer
                .parseInt(tmpMovingFigureCoordinates[1]), Integer.parseInt(tmpSelectedCoordinates[0]), Integer
                .parseInt(tmpSelectedCoordinates[1]));

            // return the effected fields
            tmpOutput += tmpRequestExtendedAttribute + ";" + tmpRequestData + ";";

            // print output
            out.println(tmpOutput);
          }
        }
      }
%>