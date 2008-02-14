// Setzen Sie eine geeignete URL ein
// (beachten Sie dazu die Hinweise im Text):
// Z.B. http://checkip.dyndns.org
// oder http://www.myjavaserver.com/~torstenhorn/ajax/checkiptxt.jsp
// oder checkiptxt.jsp
var req;

function macheRequest( url, message ) {
	try {
		if( window.XMLHttpRequest ) {
  			req = new XMLHttpRequest();
    	} else if( window.ActiveXObject ) {
    		req = new ActiveXObject( "Microsoft.XMLHTTP" );
    	} else {
       		alert( "Your Browser doesn't support AJAX!" );
    	}
    	req.open( "POST", url, true );
    	req.onreadystatechange = schreibeAntwort;
		req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		req.send( 'message=' + message );
    } catch( e ) {
      	alert( "Fehler: " + e );
    }
}

function schreibeAntwort() {
	if( 4 == req.readyState ) {
        if( 200 != req.status ) {
    		alert( "Request Error " + req.status + ": " + req.statusText );
        } else {
	    	document.getElementById("output").innerHTML = req.responseText;
        }
    }
}

function hoverOn(me, color){
	me.style.borderColor=color;
}

function hoverOff(me){
	me.style.borderColor='#ffffff';
}