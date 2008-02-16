
var req;

var globalHover = true;
var moveHover = false;
var globalMapFields = new Array();
var globalMoveArray = new Array();

function load(){
	var map = document.getElementById("map");
	
	for (i = 0; i < map.childNodes.length;i++){
		globalMapFields[i] = map.childNodes[i].attributes['id'].value;
		globalMoveArray[i] = false;
	}
}

function sendRequest( data ) {
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
		req.send( 'data=' + data );
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

function hoverOn(me, color){
	globalHoverOn(me, color);
	moveHoverOn(me);
}

function hoverOff(me, color){
	globalHoverOff(me);
	moveHoverOff(me, color);
}

function globalHoverOn(me, color){
	if (globalHover){
		me.style.borderColor=color;
	}
}

function globalHoverOff(me){
	if (globalHover){
	me.style.borderColor='#ffffff';
	}
}

function moveHoverOn(me){
	if (moveHover){
		for (i=0;i<globalMapFields.length;i++){
			if(globalMapFields[i] == me.attributes['id'].value && globalMoveArray[i]){
				me.style.borderColor='#ffffff';
			}
		}
	}
}

function moveHoverOff(me, color){
	if (moveHover){
		for (i=0;i<globalMapFields.length;i++){
			if(globalMapFields[i] == me.attributes['id'].value && globalMoveArray[i]){
				me.style.borderColor=color;
			}
		}
	}
}

function checkUserAction(me){
sendRequest(me.attributes['id'].value);
}

function buildAnswer(data){

	tmpArrayCoordinates = data.split(';');
	globalHover = false;
	moveHover = true;
	
	color = document.getElementById(tmpArrayCoordinates[1]).style.borderColor;
	
	for (i = 1; i < tmpArrayCoordinates.length-1;i++){
		document.getElementById(tmpArrayCoordinates[i]).style.borderColor=color;
		for (j = 0; j < globalMapFields.length; j++){
			if (globalMapFields[j] == tmpArrayCoordinates[i]){
				globalMoveArray[j] = true;
			}
		}
	}
	
	
}
