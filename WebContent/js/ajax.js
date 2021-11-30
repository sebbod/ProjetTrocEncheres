// FUNCTIONS
function createXHR() {
    if (window.XMLHttpRequest)    //  Objet standard
    {
        xhr = new XMLHttpRequest();     //  Firefox, Safari, ...
    }
    else if (window.ActiveXObject)      //  Internet Explorer
    {
        xhr = new ActiveXObject("Msxml2.XMLHTTP");
    }
    return xhr;
}
	
function ajax(method, endpoint = "", callback, data = null)
{
    var xhr = createXHR();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4)
        {
            if (xhr.status == 200)
            {
            	callback(eval('('+xhr.responseText+')'));
            }
            else
            {                
                alert("ajax::Error:" + xhr.status + "\n"+ xhr.responseText);
            }
        }
    };

    xhr.open(method, "/ProjetTrocEncheres/rest/"+endpoint, true);  
    
    if (data) {
    	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    	console.log("data");
    	console.log(data);
    	data = JSON.stringify(data);
    	console.log("JSON.stringify(data)");
    	console.log(data);
    }else{
    	xhr.setRequestHeader("Accept","application/json");
    }
    xhr.send(data);
}	


const getData = (endpoint, callback) => { return ajax("GET", endpoint, callback); }

const insertData = (endpoint, callback, data) => { ajax("POST", endpoint, callback, data); }

const updateData = (endpoint, callback, data) => { ajax("PUT", endpoint, callback, data); }

const deleteData = (endpoint, callback) => { ajax("DELETE", endpoint, callback); }
