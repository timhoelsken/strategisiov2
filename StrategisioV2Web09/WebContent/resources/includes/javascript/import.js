// checks if global variable does exist, if not it is created
if (typeof(anArrayOfScriptsToLoad) == 'undefined'){
    var anArrayOfScriptsToLoad = new Array();
}

// use this function to add the URL to the JS File
// to the list of scripts that have to be imported
function addScriptToLoadList(FileURL){
    anArrayOfScriptsToLoad.push(FileURL);
}

// imports the scripts according to the URLs in anArrayOfScriptsToLoad
function importScripts() {
    // if there are no URLs, finish here
    if (anArrayOfScriptsToLoad == null) return;

    // this array is needed to avoid adding the scripts in non chronologically order
    var tmpScriptList = new Array();

    for (var i = 0; i < anArrayOfScriptsToLoad.length; i++){
        // create an HTML script element and set the attributes
        var aScriptElement = document.createElement('script');
        aScriptElement.type = 'text/javascript';
        aScriptElement.src = anArrayOfScriptsToLoad[i];

        // add script element to the array
        tmpScriptList.push(aScriptElement);
    }

	// change order of elements
    tmpScriptList.reverse();

    // add script elements to the head tag
    // because each element is placed as 1st child, the order
    // changes again and is now correct again
    for (var j = 0; j < tmpScriptList.length; j++){
        document.getElementsByTagName('head')[0].appendChild(tmpScriptList[j]);
    }
}

addScriptToLoadList("resources/includes/javascript/requests.js");
addScriptToLoadList("resources/includes/javascript/movements.js");
addScriptToLoadList("resources/includes/javascript/messageBox.js");
addScriptToLoadList("resources/includes/javascript/userActions.js");
addScriptToLoadList("resources/includes/javascript/combat.js");

importScripts();