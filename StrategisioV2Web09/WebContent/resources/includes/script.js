// <=== global Variables

// HTTP-Request Variable
var req;
// ID of the figure that is moving
var movingFigureId;

var viewField = new Array();
// global Variables ===>

// general mouseOver call that refers to the specific function
function hoverOn(me){
	globalHoverOn(me);
	moveHoverOn(me);
}

// general mouseOut call that refers to the specific function
function hoverOff(me){
	globalHoverOff(me);
	moveHoverOff(me);
}

// when nothing is selected
function globalHoverOn(me, color){
	if ((me.attributes['status'].value=="placed" || me.attributes['status'].value=="placedInView") && me.attributes['filled'].value=="figure"){
		//TODO get server power
		//resolveViewField(me);
		me.style.borderColor=me.attributes['placablecolor'].value;
	}
}

// when nothing is selected
function globalHoverOff(me){
	if ((me.attributes['status'].value=="placed" || me.attributes['status'].value=="placedInView") && me.attributes['filled'].value=="figure"){
		//TODO get server power
		//unmarkField(me);
		me.style.borderColor='#FFFFFF';
	}
}

// when figure is selected for move
function moveHoverOn(me){
	if (me.attributes['status'].value=="markedForMove"){
		me.style.borderColor='#113322';
	}
}

// when figure is selected for move
function moveHoverOff(me){
	if (me.attributes['status'].value=="markedForMove"){
		me.style.borderColor=document.getElementById(movingFigureId).attributes['placablecolor'].value;
	}
}

function resolveViewField(me){
	sendRequest("view", me.attributes['id'].value, "");
}

function unmarkField(me){
	for (var i = 1; i < viewField.length-1;i++){
		document.getElementById(viewField[i]).style.borderColor = "#FFFFFF";
		document.getElementById(viewField[i]).style.borderStyle = "solid";
	}
}

// standard MessageBox
function openMessageBox(text){
	var objMessageBox = document.getElementById("messageBox");
	objMessageBox.onclick = function () {objMessageBox.style.visibility="hidden";objMessageBox.style.width="1px";objMessageBox.style.height="1px";location.reload(true);};
	objMessageBox.style.width = "100%";
	objMessageBox.style.height = "100%";
	objMessageBox.style.visibility = "visible";
	objMessageBox.innerHTML = "<div id=\"centeredText\"><table><tr><td><img src=\"resources/pictures/wait.gif\"></td><td>" + text + "</td></tr></table></div>";
}

// standard MessageBox
function openUncloseableMessageBox(text){
	var objMessageBox = document.getElementById("messageBox");
	objMessageBox.style.width = "65%";
	objMessageBox.style.height = "50%";
	objMessageBox.style.visibility = "visible";
	objMessageBox.innerHTML = "<div id=\"centeredText\"><table><tr><td><img src=\"resources/pictures/wait.gif\"></td><td>" + text + "</td></tr></table></div>";
}

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

	// on hover over a figure
	if (dataSegments[1] == "view"){
		tmpArrayCoordinates = dataSegments[2].split(';');

		// mark the specific fields
		for (var i = 1; i < tmpArrayCoordinates.length-1;i++){
			document.getElementById(tmpArrayCoordinates[i]).style.borderStyle = "dashed";
			document.getElementById(tmpArrayCoordinates[i]).style.borderColor = "#000000";
		}

		viewField = tmpArrayCoordinates;
	} // if a figure is marked
	else if (dataSegments[1] == "markedForMove"){
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
	else if (dataSegments[1] == "Moved"){

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

		location.reload(true);

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
			Create dialog here,where user enters attack and defence.
			When user has entered both, call controller.jsp again with action = "Combat"


		*/

		alert('fight!');
	}

}

// <=== AJAX Request
function sendRequest( action, data, extendedAttribute ) {
	try {
		if( window.XMLHttpRequest ) {
  			req = new XMLHttpRequest();
    	} else if( window.ActiveXObject ) {
    		req = new ActiveXObject( "Microsoft.XMLHTTP" );
    	} else {
       		alert( "Your Browser doesn't support AJAX!" );
    	}
    	req.open( "POST", "controller.jsp", true );
    	req.onreadystatechange = getAnswer;
		req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		req.send( 'action=' + action + '&data=' + data + '&extendedAttribute=' + extendedAttribute );
    } catch( e ) {
      	alert( "Error: " + e );
    }
}

function getAnswer() {
	if( 4 == req.readyState ) {
        if( 200 != req.status ) {
    		alert( "Request Error " + req.status + ": " + req.statusText );
        } else {
	    	buildAnswer(req.responseText);
        }
    }
}

function setRefreshedMap() {
	if( 4 == req.readyState ) {
        if( 200 != req.status ) {
    		alert( "Request Error " + req.status + ": " + req.statusText );
        } else {
	    	document.getElementById("map").innerHTML = req.responseText.replace(/^\s+/,"").replace(/\s+$/,"");
        }
    }
}

function doRefreshRequest() {
	try {
		if( window.XMLHttpRequest ) {
  			req = new XMLHttpRequest();
    	} else if( window.ActiveXObject ) {
    		req = new ActiveXObject( "Microsoft.XMLHTTP" );
    	} else {
       		alert( "Your Browser doesn't support AJAX!" );
    	}
    	req.open( "POST", "refresh.jsp", true );
    	req.onreadystatechange = setRefreshedMap;
    	req.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    	req.send( null );
    } catch( e ) {
      	alert( "Error: " + e );
    }
}

function refresh(doRefresh){
	if (doRefresh) {
		openUncloseableMessageBox("Please wait for the other player...");
		doRefreshRequest();
		setTimeout("refresh(true)",5000);
	}else{

	}
}
// AJAX Request ===>