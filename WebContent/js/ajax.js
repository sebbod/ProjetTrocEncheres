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
	
function executerRequete(method, endpoint = "", data = null)
{
    var xhr = createXHR();
    xhr.onreadystatechange = function()
    {
        if (xhr.readyState == 4)
        {
            if (xhr.status == 200)
            {
            	succes(xhr.responseText);//xhr.responseXML si réponse au format XML
            }
            else
            {
                echec(xhr.status, xhr.responseText);
            }
        }
    };

    xhr.open(method, "/ProjetTrocEncheres/rest/"+endpoint, true);
    xhr.setRequestHeader("Accept","application/json");
    xhr.send(data);
}	

executerRequete("GET", "user/1");

function succes(reponse)
{
	alert(reponse);
	//document.getElementById("succes").innerHTML=reponse;
	//document.getElementById("echec").innerHTML="";
}

function echec(codeReponse, reponse)
{
	alert(reponse);
	//document.getElementById("echec").innerHTML=reponse;
	//document.getElementById("succes").innerHTML="";
}