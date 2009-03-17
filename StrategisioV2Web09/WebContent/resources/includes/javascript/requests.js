// HTTP-Request Variable
var req;

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

// Request is calculated in setRefreshedMap()
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

function getAnswer() {
	if( 4 == req.readyState ) {
        if( 200 != req.status ) {
    		alert( "Request Error " + req.status + ": " + req.statusText );
        } else {
	    	buildAnswer(req.responseText);
        }
    }
}

// recalls the doRefreshRequest() if the player is not on turn
function setRefreshedMap() {
	if( 4 == req.readyState ) {
        if( 200 != req.status ) {
    		alert( "Request Error " + req.status + ": " + req.statusText );
        } else {
        	//what tells me the refresh.jsp? [1] = true/false; [2] = map/null
	    	var responseArray = req.responseText.split('+++');


	    	// Is it my turn?
	    	if (responseArray[1] == "false"){

	    		// No it's not, I have to wait and do the request again
	    		setTimeout("doRefreshRequest()",1000);
	    	}
	    	else{

	    	// YEAH! It's my turn! I load the map and close the messageBox()
	    	document.getElementById("map").innerHTML = responseArray[2].replace(/^\s+/,"").replace(/\s+$/,"");
	    	closeMessageBox();
	    	}
        }
    }
}