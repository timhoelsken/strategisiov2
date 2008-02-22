// <=== global Variables

// HTTP-Request Variable
var req;
// ID of the figure that is moving
var movingFigureId;

// global Variables ===>

// general mouseOver call that refers to the specific function
function hoverOn(me, color){
	globalHoverOn(me, color);
	moveHoverOn(me);
}

// general mouseOut call that refers to the specific function
function hoverOff(me, color){
	globalHoverOff(me);
	moveHoverOff(me, color);
}

// when nothing is selected
function globalHoverOn(me, color){
	if (me.attributes['status'].value=="placed" && me.attributes['filled'].value=="figure"){
		me.style.borderColor=color;
	}
}

// when nothing is selected
function globalHoverOff(me){
	if (me.attributes['status'].value=="placed" && me.attributes['filled'].value=="figure"){
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
function moveHoverOff(me, color){
	if (me.attributes['status'].value=="markedForMove"){
		me.style.borderColor=document.getElementById(movingFigureId).style.borderColor;
	}
}

// generel function that is called onClick, refers to AJAX Request
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
		for (i = 0; i < map.childNodes.length;i++){
			map.childNodes[i].attributes['status'].value ="disabled";
		}
		
		tmpArrayCoordinates = dataSegments[2].split(';');
		
		movingFigureId = document.getElementById(tmpArrayCoordinates[1]).attributes['id'].value;
		
		// mark the specific fields
		for (i = 1; i < tmpArrayCoordinates.length-1;i++){
			document.getElementById(tmpArrayCoordinates[i]).style.borderColor=document.getElementById(movingFigureId).style.borderColor;
			document.getElementById(tmpArrayCoordinates[i]).attributes['status'].value ="markedForMove";
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
		document.getElementById(tmpArrayCoordinates[1]).attributes['filled'].value = "figure";
		document.getElementById(tmpArrayCoordinates[1]).attributes['status'].value = "placed";
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

// AJAX Request ===>