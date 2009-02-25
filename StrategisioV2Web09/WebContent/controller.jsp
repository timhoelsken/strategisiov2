<%@ page import="strategisio.*"%>
<%@ page import="strategisio.elements.Placeable"%>
<%@ page import="strategisio.elements.Combat"%>
<%@ page import="strategisio.elements.figures.Figure"%>
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

        if (tmpRequestAction.equals("view")) {

          // the action that follows on the hover
          tmpOutput += "+++view+++";

          ArrayList<int[]> tmpArea = tmpGame.getViewArea(Integer.parseInt(tmpSelectedCoordinates[0]), Integer
              .parseInt(tmpSelectedCoordinates[1]));
          int[] tmpPossibleCoordinates;

          // write the viewArea into the output
          for (int i = 0; i < tmpArea.size(); i++) {
            tmpPossibleCoordinates = tmpArea.get(i);
            tmpOutput += ";" + tmpPossibleCoordinates[0] + "/" + tmpPossibleCoordinates[1];
          }

          tmpOutput += ";";

          // print output
          out.println(tmpOutput);
        }

        // if a placed figure is clicked
        else if (tmpRequestAction.equals("placed")) {

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

            if (!tmpGame.fieldIsSetByPlaceable(Integer.parseInt(tmpSelectedCoordinates[0]), Integer
                .parseInt(tmpSelectedCoordinates[1]))
                || (tmpRequestData.equals(tmpRequestExtendedAttribute))) {

              // the performed action was a move
              tmpOutput += "+++Moved+++";

              String[] tmpMovingFigureCoordinates = tmpRequestExtendedAttribute.split("/");

              // do the move
              tmpGame.move(Integer.parseInt(tmpMovingFigureCoordinates[0]), Integer
                  .parseInt(tmpMovingFigureCoordinates[1]), Integer.parseInt(tmpSelectedCoordinates[0]),
                  Integer.parseInt(tmpSelectedCoordinates[1]));

              // return the effected fields
              tmpOutput += tmpRequestExtendedAttribute + ";" + tmpRequestData + ";";

            } else if (tmpGame.fieldIsSetByPlaceable(Integer.parseInt(tmpSelectedCoordinates[0]), Integer
                .parseInt(tmpSelectedCoordinates[1]))) {
              Placeable tmpPlaceable = tmpGame.getPlayMap().getField(
                  Integer.parseInt(tmpSelectedCoordinates[0]), Integer.parseInt(tmpSelectedCoordinates[1]))
                  .getSetter();

              if (tmpPlaceable instanceof Figure) {
                tmpOutput += "+++initFight+++;" + tmpRequestExtendedAttribute + ";" + tmpRequestData;
              } else {
                tmpOutput += "+++Item+++";
              }
            }
            // print output
            out.println(tmpOutput);

			// TODO Der refresh fehlt noch.
			// TODO playerId an allen notwendigen Stellen umsetzen => auslagern
            // change current player
            String tmpCurrentPlayer = (String) application.getAttribute("currentPlayer");
            if ("A".equals(tmpCurrentPlayer)) {
              application.setAttribute("currentPlayer", "B");
            } else if ("B".equals(tmpCurrentPlayer)) {
              application.setAttribute("currentPlayer", "A");
            }
          }
        } else if (tmpRequestAction.equals("placedInView")) {
          // the action that follows on the click
          tmpOutput += "+++markedForMoveWhileInView+++";

          // print output
          out.println(tmpOutput);
        } else if (tmpRequestAction.equals("Combat")) {
          String tmpRequestExtendedAttribute = request.getParameter("extendedAttribute");
          String[] tmpAttackerAndDefender = tmpRequestExtendedAttribute.split(";");
          String[] tmpAttackerCoordinates = tmpAttackerAndDefender[1].split("/");
          String[] tmpDefenderCoordinates = tmpAttackerAndDefender[2].split("/");

          Figure tmpAttacker = (Figure) tmpGame.getPlayMap().getField(
              Integer.parseInt(tmpAttackerCoordinates[0].trim()),
              Integer.parseInt(tmpAttackerCoordinates[1].trim())).getSetter();
          Figure tmpDefender = (Figure) tmpGame.getPlayMap().getField(
              Integer.parseInt(tmpDefenderCoordinates[0].trim()),
              Integer.parseInt(tmpDefenderCoordinates[1].trim())).getSetter();

          Combat tmpCombat = new Combat(tmpAttacker, tmpDefender);

          application.setAttribute("globalCombat", tmpCombat);

          if (tmpCombat.evaluate() == null) {
            tmpOutput += "+++continueCombat+++";
          } else {
            int[] tmpWinnerCoordinates = tmpCombat.evaluate().getCurrentCoordinates();
            String tmpWinnerString = "";
            String tmpLoserString = "";

            if (tmpAttacker.getCurrentCoordinates()[0] == tmpWinnerCoordinates[0]
                && tmpAttacker.getCurrentCoordinates()[1] == tmpWinnerCoordinates[1]) {
              tmpWinnerString = tmpAttacker.getFigureType() + "_" + tmpAttacker.getId();
              tmpLoserString = tmpDefender.getFigureType() + "_" + tmpDefender.getId();

              // do the move
              tmpGame.move(tmpAttacker.getCurrentCoordinates()[0], tmpAttacker.getCurrentCoordinates()[1],
                  tmpDefender.getCurrentCoordinates()[0], tmpDefender.getCurrentCoordinates()[1]);

              tmpOutput += "+++combatWinner+++" + tmpWinnerCoordinates[0] + "/" + tmpWinnerCoordinates[1]
                  + ";" + tmpRequestData + "+++" + tmpWinnerString + "+++" + tmpLoserString + "+++attacker";
              tmpGame.removePlaceableFromTeam(tmpDefender, tmpDefender.getId());
            } else {
              tmpWinnerString = tmpDefender.getFigureType() + "_" + tmpDefender.getId();
              tmpLoserString = tmpAttacker.getFigureType() + "_" + tmpAttacker.getId();

              tmpOutput += "+++combatWinner+++" + tmpWinnerCoordinates[0] + "/" + tmpWinnerCoordinates[1]
                  + ";" + tmpAttacker.getCurrentCoordinates()[0] + "/"
                  + tmpAttacker.getCurrentCoordinates()[1] + "+++" + tmpWinnerString + "+++" + tmpLoserString
                  + "+++defender";
              tmpGame.removePlaceableFromTeam(tmpAttacker, tmpAttacker.getId());
            }

            application.setAttribute("globalCombat", null);
          }

          out.println(tmpOutput);
        } else if (tmpRequestAction.equals("continueCombat")) {
          // Here is a loop set output to +++continueCombat+++ for that

          // TODO Problem: data from 2 users is needed! switch with the playerID?!
        }
      }
%>