// global variables for the combat
var attack = -1;
var defend = -1;

// to set the attack
function setAttack(number){
	attack = number;
	checkAttacksAndDefends();
}

// to set the defend
function setDefend(number){
	defend = number;
	checkAttacksAndDefends();
}

// check if attack and defend is set. When yes, close box and do Request to controller.jsp
function checkAttacksAndDefends(){

		alert("attack: " + attack + " - defend: " + defend);
	if (attack != -1 && defend != -1){
		closeCombatDialog();
		sendRequest("controller.jsp", "continueCombat", "+++" + attack + "+++" + defend, null);
	}
}

// opens the Dialog. Sets onclick events on the attack / defend words
function openCombatDialog(){
	var objOverlay = document.getElementById("overlay");
	objOverlay.style.width = "100%";
	objOverlay.style.height = "100%";

	var mapHeightString = document.getElementById("map").style.height;
	var mapHeightNumber = mapHeightString.slice(0, mapHeightString.indexOf('p'));
	var BoxHeight = (parseFloat(mapHeightNumber) + 40);

	var mapWidthString = document.getElementById("map").style.width;
	var mapWidthNumber = mapWidthString.slice(0, mapWidthString.indexOf('p'));
	var BoxWidth = (parseFloat(mapWidthNumber) + 40);

	objOverlay.style.paddingLeft = BoxWidth + "px";
	objOverlay.style.visibility = "visible";

	var combatDialogHTML = "";
	combatDialogHTML += "<div id=\"dialogBox\" style=\"background-color:#fff;\">"
	combatDialogHTML += "<table><tr><td>Attack:</td></tr>";
	combatDialogHTML += "<tr><td><table><tr>";
	combatDialogHTML += "<td onclick='setAttack(\'0\')'>punsh</td><td onclick='setAttack(\'1\')'>kick</td><td onclick='setAttack(\'2\')'>knife</td><td onclick='setAttack(\'3\')'>shot</td>";
	combatDialogHTML += "</tr></table>";
	combatDialogHTML += "</td></tr>";
	combatDialogHTML += "<tr><td>Defend:</td></tr>";
	combatDialogHTML += "<tr><td><table><tr>";
	combatDialogHTML += "<td onclick='setDefend(\'0\')'>punsh</td><td onclick='setDefend(\'1\')'>kick</td><td onclick='setDefend(\'2\')'>knife</td><td onclick='setDefend(\'3\')'>shot</td>";
	combatDialogHTML += "</tr></table>";
	combatDialogHTML += "</td></tr></table>";
	combatDialogHTML += "</div>";

	objOverlay.innerHTML = combatDialogHTML;
}

// closes the dialog
function closeCombatDialog(){
	var objOverlay = document.getElementById("overlay");
	objOverlay.style.visibility="hidden";
	objOverlay.style.width="1px";
	objOverlay.style.height="1px";
}