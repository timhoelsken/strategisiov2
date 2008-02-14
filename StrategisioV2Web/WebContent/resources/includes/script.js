var http_request = false;

	// Get the URL to the JSP that produces the output, get message (just for test)
    function macheRequest(url, message) {

        http_request = false;

		//This is for differentiation between IE and others
		//does not work properly for others than IE, don't know why! have to google a bit
        if (window.XMLHttpRequest) { // Mozilla, Safari,...
            http_request = new XMLHttpRequest();
            if (http_request.overrideMimeType) {
                http_request.overrideMimeType('text/xml');
            }
        } else if (window.ActiveXObject) { // IE
            try {
                http_request = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    http_request = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {}
            }
        }

        if (!http_request) {
            alert('Ende :( Kann keine XMLHTTP-Instanz erzeugen');
            return false;
        }
        
    //now work with XMLHTTP instance
	http_request.onreadystatechange = alertInhalt;
	
	//data is send via POST to page in URL
    http_request.open('POST', url, true);
    http_request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	//Parameters
	http_request.send('message=' + message);
}

    
    function alertInhalt() {

		//readyState changes from 1 to 4 ( 4 means page loaded completly
		//FF stops at 2 or so
        if (http_request.readyState == 4) {
            if (http_request.status == 200) {
            //this is the place where the response is inserted (output)
		document.all.output.innerHTML = http_request.responseText;
            } else {
                alert('Bei dem Request ist ein Problem aufgetreten.');
            }
        }

    }

function hoverOn(me){
me.style.borderColor='#ff0000';
}

function hoverOff(me){
me.style.borderColor='#ffffff';
}