
var req;

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
	if (me.attributes['status'].value=="placed"){
		me.style.borderColor=color;
	}
}

function globalHoverOff(me){
	if (me.attributes['status'].value=="placed"){
	me.style.borderColor='#ffffff';
	}
}

function moveHoverOn(me){
	if (me.attributes['status'].value=="markedForMove"){
				me.style.borderColor='#ffffff';
	}
}

function moveHoverOff(me, color){
	if (me.attributes['status'].value=="markedForMove"){
				me.style.borderColor=color;
			
		
	}
}

function checkUserAction(me){
//me.attributes['class'].value = "field mountain";
sendRequest(me.attributes['id'].value);
}

function buildAnswer(data){

	tmpArrayCoordinates = data.split(';');
	
	color = document.getElementById(tmpArrayCoordinates[1]).style.borderColor;
	
	for (i = 1; i < tmpArrayCoordinates.length-1;i++){
		document.getElementById(tmpArrayCoordinates[i]).style.borderColor=color;
		document.getElementById(tmpArrayCoordinates[i]).attributes['status'].value ="markedForMove";
	}
	
	
}
