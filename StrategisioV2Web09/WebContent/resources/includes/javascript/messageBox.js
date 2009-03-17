
// standard MessageBox
function openMessageBox(text){
	//TODO function has an error
	var objMessageBox = document.getElementById("messageBox");
	objMessageBox.onclick = closeMessageBox();
	objMessageBox.style.width = "100%";
	objMessageBox.style.height = "100%";

	var mapHeightString = document.getElementById("map").style.height;
	var mapHeightNumber = mapHeightString.slice(0, mapHeightString.indexOf('p'));
	var BoxHeight = (parseFloat(mapHeightNumber) + 40);

	var mapWidthString = document.getElementById("map").style.width;
	var mapWidthNumber = mapWidthString.slice(0, mapWidthString.indexOf('p'));
	var BoxWidth = (parseFloat(mapWidthNumber) - 31);

	objMessageBox.style.paddingTop = BoxHeight + "px";
	objMessageBox.style.visibility = "visible";
	objMessageBox.innerHTML = "<div><table><tr><td><div id=\"centeredTextLeft\"></div></td><td><div id=\"centeredText\"><table><tr><td><img src=\"resources/pictures/exclamationmark.gif\"></td><td>" + text + "</td></tr></table></div></td><td><div id=\"centeredTextRight\"></div></td>";
	document.getElementById("centeredText").style.width = BoxWidth;
}

// uncloseable MessageBox
function openUncloseableMessageBox(text){
	var objMessageBox = document.getElementById("messageBox");
	objMessageBox.style.width = "100%";
	objMessageBox.style.height = "100%";

	var mapHeightString = document.getElementById("map").style.height;
	var mapHeightNumber = mapHeightString.slice(0, mapHeightString.indexOf('p'));
	var BoxHeight = (parseFloat(mapHeightNumber) + 40);

	var mapWidthString = document.getElementById("map").style.width;
	var mapWidthNumber = mapWidthString.slice(0, mapWidthString.indexOf('p'));
	var BoxWidth = (parseFloat(mapWidthNumber) + 40);

	objMessageBox.style.paddingTop = BoxHeight + "px";
	objMessageBox.style.visibility = "visible";
	objMessageBox.innerHTML = "<div><table style=\"border-collapse:collapse;\"><tr><td><div id=\"centeredTextLeft\"></div></td><td><div id=\"centeredText\"><table><tr><td><img src=\"resources/pictures/wait.gif\"></td><td>" + text + "</td></tr></table></div></td><td><div id=\"centeredTextRight\"></div></td>";
	document.getElementById("centeredText").style.width = BoxWidth;
}

// closes the messageBox
function closeMessageBox(){
	var objMessageBox = document.getElementById("messageBox");
	objMessageBox.style.visibility="hidden";
	objMessageBox.style.width="1px";
	objMessageBox.style.height="1px";
}