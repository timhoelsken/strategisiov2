// HTTP-Request Variable
var req;

// <=== AJAX Request
function doRequest(url) {
	try {
		if( window.XMLHttpRequest ) {
  			req = new XMLHttpRequest();
    	} else if( window.ActiveXObject ) {
    		req = new ActiveXObject( "Microsoft.XMLHTTP" );
    	} else {
       		alert( "Your Browser doesn't support AJAX!" );
    	}
    	req.open( "POST", url, true );
    	req.onreadystatechange = getAnswer;
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
			var tmpColor = req.responseText.replace(/^\s+/,"");
			document.getElementById("colordiv").style.backgroundColor = tmpColor;
        }
    }
}

function refresh(){
	doRequest("refresh.jsp");
	setTimeout("refresh()",1);
}

// AJAX Request ===>