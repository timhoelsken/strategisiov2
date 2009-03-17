// ID of the figure that is moving
var movingFigureId;

// global Variables ===>

// general mouseOver call that refers to the specific function
function hoverOn(me){
	globalHoverOn(me);
	moveHoverOn(me);
}

// general mouseOut call that refers to the specific function
function hoverOff(me){
	globalHoverOff(me);
	moveHoverOff(me);
}

// when nothing is selected
function globalHoverOn(me, color){
	if ((me.attributes['status'].value=="placed" || me.attributes['status'].value=="placedInView") && me.attributes['filled'].value=="figure"){
		me.style.borderColor=me.attributes['placablecolor'].value;
	}
}

// when nothing is selected
function globalHoverOff(me){
	if ((me.attributes['status'].value=="placed" || me.attributes['status'].value=="placedInView") && me.attributes['filled'].value=="figure"){
		me.style.borderColor='#FFFFFF';
	}
}

// when figure is selected for move
function moveHoverOn(me){
	if (me.attributes['status'].value=="markedForMove"){
		me.style.borderColor='#113322';
	}
}

// when figure is selected for move
function moveHoverOff(me){
	if (me.attributes['status'].value=="markedForMove"){
		me.style.borderColor=document.getElementById(movingFigureId).attributes['placablecolor'].value;
	}
}