/**
 * 
 */
var url = "";

function setUrl(url){
	this.url = url;
}

function getUrl(){
	return this.url;
}

function getParameterByName(name) {
	var url = getUrl();
	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
			.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}