// Setzen Sie eine geeignete URL ein
// (beachten Sie dazu die Hinweise im Text):
// Z.B. http://checkip.dyndns.org
// oder http://www.myjavaserver.com/~torstenhorn/ajax/checkiptxt.jsp
// oder checkiptxt.jsp
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
	me.style.borderColor=color;
}

function hoverOff(me){
	me.style.borderColor='#ffffff';
}

function checkUserAction(me){
sendRequest(me.attributes['id'].value);
}

function buildAnswer(data){
alert(data);
}