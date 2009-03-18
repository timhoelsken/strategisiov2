
// standard MessageBox
function openStandardMessageBox(text){
	openMessageBox()
	document.getElementById("messageBoxIconPlaceholder").innerHTML = "<img src=\"resources/pictures/exclamationmark.gif\" />";
	document.getElementById("messageBoxTextPlaceholder").innerHTML = text;

	//TODO does not work in IE, but in FF... dont know why
	document.getElementById("overlay").setAttribute('onclick', 'closeMessageBox();');
	document.getElementById("messageBox").setAttribute('onclick', 'closeMessageBox();');
}

// uncloseable MessageBox
function openUncloseableMessageBox(text){
	openMessageBox()
	document.getElementById("messageBoxIconPlaceholder").innerHTML = "<img src=\"resources/pictures/wait.gif\" />";
	document.getElementById("messageBoxTextPlaceholder").innerHTML = text;
}

function openMessageBox(){
	var objOverlay = document.getElementById("overlay");
	objOverlay.style.width = "100%";
	objOverlay.style.height = "100%";

	var mapHeightString = document.getElementById("map").style.height;
	var mapHeightNumber = mapHeightString.slice(0, mapHeightString.indexOf('p'));
	var BoxHeight = (parseFloat(mapHeightNumber) + 40);

	var mapWidthString = document.getElementById("map").style.width;
	var mapWidthNumber = mapWidthString.slice(0, mapWidthString.indexOf('p'));
	var BoxWidth = (parseFloat(mapWidthNumber) + 40);

	objOverlay.style.paddingTop = BoxHeight + "px";
	objOverlay.style.visibility = "visible";
	var messageBoxHTML = "";
	messageBoxHTML += "<div id=\"messageBox\"><table id=\"messageBoxContentTable\">";
	messageBoxHTML += "<tr><td class=\"messageBoxContent TopL\"></td><td class=\"messageBoxContent TopC\"></td><td class=\"messageBoxContent TopR\"></td></tr>";
	messageBoxHTML += "<tr><td class=\"messageBoxContent MiddleL\"></td><td id=\"messageBoxContentPlaceholder\" class=\"messageBoxContent MiddleC\">";
	messageBoxHTML += "<table><tr><td id=\"messageBoxIconPlaceholder\" class=\"messageBoxPlaceholder Icon\"></td><td id=\"messageBoxTextPlaceholder\" class=\"messageBoxPlaceholder Text\"></td></tr></table></td>";
	messageBoxHTML += "<td class=\"messageBoxContent MiddleR\"></td></tr>";
	messageBoxHTML += "<tr><td class=\"messageBoxContent BottomL\"></td><td class=\"messageBoxContent BottomC\"></td><td class=\"messageBoxContent BottomR\"></td></tr>";
	messageBoxHTML += "</table></div>";
	objOverlay.innerHTML = messageBoxHTML;
	document.getElementById("messageBoxContentPlaceholder").style.width = BoxWidth;
}
// closes the messageBox
function closeMessageBox(){
	var objOverlay = document.getElementById("overlay");
	objOverlay.style.visibility="hidden";
	objOverlay.style.width="1px";
	objOverlay.style.height="1px";
}