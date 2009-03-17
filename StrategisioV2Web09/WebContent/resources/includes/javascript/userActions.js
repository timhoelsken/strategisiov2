
// general function that is called onClick, refers to AJAX Request
function checkUserAction(me){
	var extendedAttribute = "";

	if (movingFigureId != ""){
		extendedAttribute = movingFigureId;
	}

	sendRequest(me.attributes['status'].value, me.attributes['id'].value, extendedAttribute);
}

// === Main-Function for processing the output of the action ===
function buildAnswer(data){
	// split the output to performed action [1] and coordinates [2]
	var dataSegments = data.split('+++');

	// if a figure is marked
	if (dataSegments[1] == "markedForMove"){
		// get the Map
		var map = document.getElementById("map");

		// disable all fields on the map
		for (var i = 0; i < map.childNodes.length; i++){
			map.childNodes[i].style.borderStyle = "solid";
			map.childNodes[i].style.borderColor = "#FFFFFF";
			map.childNodes[i].attributes['status'].value ="disabled";
		}

		tmpArrayCoordinates = dataSegments[2].split(';');
		movingFigureId = document.getElementById(tmpArrayCoordinates[1]).attributes['id'].value;

		// mark the specific fields
		for (i = 1; i < tmpArrayCoordinates.length-1;i++){
			var tmpColor = document.getElementById(movingFigureId).attributes['placablecolor'].value;
			document.getElementById(tmpArrayCoordinates[i]).style.borderColor = tmpColor;
			document.getElementById(tmpArrayCoordinates[i]).attributes['status'].value ="markedForMove";
			document.getElementById(tmpArrayCoordinates[i]).attributes['placablecolor'].value = tmpColor;
		}
	} // if a figure is moved to a field
	else if (dataSegments[1] == "Moved" || dataSegments[1] == "MovedToSameField"){

		// get the map
		var map = document.getElementById("map");

		// set all fields back to normal --> no border, status back to placed / empty
		for (i = 0; i < map.childNodes.length;i++){
			if (map.childNodes[i].attributes['filled'].value != "no"){
				map.childNodes[i].attributes['status'].value ="placed";
			}
			else{
				map.childNodes[i].attributes['status'].value ="empty";
				map.childNodes[i].attributes['placablecolor'].value ="#000000";
			}
			map.childNodes[i].style.borderColor='#FFFFFF';
		}

		tmpArrayCoordinates = dataSegments[2].split(';');

		// means != 'MovedToSameField'
		if (dataSegments[1] == "Moved") {
			// clear old field, paint figure on new field, set attributes of fields
			tmpFieldData = document.getElementById(tmpArrayCoordinates[0]).innerHTML;
			document.getElementById(tmpArrayCoordinates[0]).innerHTML = "";
			document.getElementById(tmpArrayCoordinates[1]).innerHTML = tmpFieldData;
			document.getElementById(tmpArrayCoordinates[0]).attributes['filled'].value = "no";
			document.getElementById(tmpArrayCoordinates[0]).attributes['status'].value = "empty";
			document.getElementById(tmpArrayCoordinates[1]).attributes['placablecolor'].value = document.getElementById(tmpArrayCoordinates[0]).attributes['placablecolor'].value;
			document.getElementById(tmpArrayCoordinates[0]).attributes['placablecolor'].value = "#000000";
			document.getElementById(tmpArrayCoordinates[1]).attributes['filled'].value = "figure";
			document.getElementById(tmpArrayCoordinates[1]).attributes['status'].value = "placed";

			refresh();
		}

	} else if(dataSegments[1] == "markedForMoveWhileInView"){
		return;
	} else if(dataSegments[1] == "initFight"){
		openMessageBox('Now a fight starts!');
		var attackerAndDefender = dataSegments[2].split(';');

		sendRequest("Combat", attackerAndDefender[2], dataSegments[2]);
	} else if(dataSegments[1] == "Item") {
		openMessageBox('You moved on an item!');
	} else if(dataSegments[1] == "combatWinner") {
		openMessageBox(dataSegments[3] + " won the fight against " + dataSegments[4] + " easily!");

		// get the map
		var map = document.getElementById("map");

		// set all fields back to normal --> no border, status back to placed / empty
		for (i = 0; i < map.childNodes.length;i++){
			if (map.childNodes[i].attributes['filled'].value != "no"){
				map.childNodes[i].attributes['status'].value ="placed";
			}
			else{
				map.childNodes[i].attributes['status'].value ="empty";
				map.childNodes[i].attributes['placablecolor'].value ="#000000";
			}
			map.childNodes[i].style.borderColor='#FFFFFF';
		}

		tmpArrayCoordinates = dataSegments[2].split(";");

		if (dataSegments[5]=="attacker"){
		// clear old field, paint figure on new field, set attributes of fields
		tmpFieldData = document.getElementById(tmpArrayCoordinates[0]).innerHTML;
		document.getElementById(tmpArrayCoordinates[0]).innerHTML = "";
		document.getElementById(tmpArrayCoordinates[1]).innerHTML = tmpFieldData;
		document.getElementById(tmpArrayCoordinates[0]).attributes['filled'].value = "no";
		document.getElementById(tmpArrayCoordinates[0]).attributes['status'].value = "empty";
		document.getElementById(tmpArrayCoordinates[1]).attributes['placablecolor'].value = document.getElementById(tmpArrayCoordinates[0]).attributes['placablecolor'].value;
		document.getElementById(tmpArrayCoordinates[0]).attributes['placablecolor'].value = "#000000";
		document.getElementById(tmpArrayCoordinates[1]).attributes['filled'].value = "figure";
		document.getElementById(tmpArrayCoordinates[1]).attributes['status'].value = "placed";
		}
		else{
		document.getElementById(tmpArrayCoordinates[1]).innerHTML = "";
		document.getElementById(tmpArrayCoordinates[1]).attributes['filled'].value = "no";
		document.getElementById(tmpArrayCoordinates[1]).attributes['status'].value = "empty";
		document.getElementById(tmpArrayCoordinates[1]).attributes['placablecolor'].value = "#000000";
		}
	} else if(dataSegments[1] == "continueCombat") {
		/*
			TODO
			Create dialog here,where user enters attack and defence.
			When user has entered both, call controller.jsp again with action = "Combat"

			Also use refresh.jsp for this!
		*/

		alert('fight!');
	}

}

// TODO write function to refresh MessageBox Text and Icon, ie when enemy got on item or figure
// TODO create combat.jsp?
// TODO write function to log movements in the game
// TODO write the log functions to the relevant positions in the buildAnswer function
// TODO add Log box to game.jsp

// TODO make the new map appear before the messageBox opens...
// uses initially when a player has to wait!
function refresh(){
		openUncloseableMessageBox("Please wait for the other player...");
		doRefreshRequest();
}