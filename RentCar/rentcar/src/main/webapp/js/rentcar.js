function post_to_url(path, method) {
    method = method || "post"; // Set method to post by default if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    document.body.appendChild(form);
    form.submit();
}
function post_to_url_params(path, params, method) {
    method = method || "post"; // Set method to post by default if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);
            form.appendChild(hiddenField);
         }
    }

    document.body.appendChild(form);
    form.submit();
}
function getSelect(paramName) {
	var select;
	var result;
	select=document.getElementsByName(paramName)[0];
	result=select.options[select.selectedIndex].value;
	return result;
}

function setSelect(paramName,paramValue) {
	var select;
	select=document.getElementsByName(paramName)[0];
	for (var i=0;i<select.options.length;i++) {
		if(select.options[i].value==paramValue){
			select.selectedIndex=i;
			return;
		}
	}
}
function getInputValue(paramName) {
	var input;
	var result;
	input=document.getElementsByName(paramName)[0];
	result=input.value;
	return result;
}

function setInputValue(paramName,value) {
	var input;
	var result;
	input=document.getElementsByName(paramName)[0];
	input.value=value;	
}
