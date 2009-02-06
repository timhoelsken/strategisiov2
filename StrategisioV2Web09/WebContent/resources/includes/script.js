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
		resolveViewField(me);
		me.style.borderColor=me.attributes['placablecolor'].value;
	}
}

// when nothing is selected
function globalHoverOff(me){
	if ((me.attributes['status'].value=="placed" || me.attributes['status'].value=="placedInView") && me.attributes['filled'].value=="figure"){
		unmarkField(me);
		me.style.borderColor='#ffffff';
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

	if (viewField[1] == "-1/-1"){
			return;
			}

	for (var i = 1; i < viewField.length-1;i++){
			document.getElementById(viewField[i]).style.borderColor = "#FFFFFF";
		}
}
// standard MessageBox
function openMessageBox(text){
var objBody = document.getElementsByTagName("body").item(0);

var objOverlay = document.createElement("div");
objOverlay.setAttribute('id','overlay');
objOverlay.onclick = function () {document.getElementsByTagName("body").item(0).removeChild(document.getElementsByTagName("body").item(0).firstChild); return false;}
objBody.insertBefore(objOverlay, objBody.firstChild);
objMessagebox = document.createElement("div");
objMessagebox.setAttribute('id','Messagebox');
objMessagebox.innerHTML = text;
objBody.insertBefore(objMessagebox, objOverlay.nextSibling);
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

		if (tmpArrayCoordinates[1] == "-1/-1"){
			return;
			}


		// mark the specific fields
		for (var i = 1; i < tmpArrayCoordinates.length-1;i++){
			document.getElementById(tmpArrayCoordinates[i]).style.borderColor = "FF00FF";
		}

		viewField = tmpArrayCoordinates;
	}
	// if a figure is marked
	else if (dataSegments[1] == "markedForMove"){

		// get the Map
		var map = document.getElementById("map");

		// disable all fields on the map
		for (i = 0; i < map.childNodes.length;i++){
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
	}
	// if a figure is moved to a field
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
			map.childNodes[i].style.borderColor='#ffffff';
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
	}
	else if(dataSegments[1] == "markedForMoveWhileInView"){
		return;
	}
	else if(dataSegments[1] == "Fight"){
		alert('Now a fight starts!');
		}
	else if(dataSegments[1] == "Item") {
		alert('You moved on an item!');
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

function refresh(){
	doRefreshRequest();
	setTimeout("refresh()",5000);
}
// AJAX Request ===>