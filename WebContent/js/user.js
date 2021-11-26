 
      
function displayError(msg){	
	if(msg.hasOwnProperty('message')){
		if( msg.message.length > 0){			
			msgs = msg.message.split('|');
			errorMsg.innerHTML = msgs[msgs.length-1].replaceAll("\n","<br>");
		}
	}else{
		errorMsg.innerHTML = ""
		displayProfile4View(msg);
	}	
}

function insertUser(pseudo, name, firstName, email, telephone, street, zipCode, town, pwd) {
    const data = {pseudo, name, firstName, email, telephone, street, zipCode, town, pwd};
    insertData(`user/signup`, data);
    getSession();
}

function updateUser() {
	const data = {};
    ["pseudo", "name", "firstName", "email", "telephone", "street", "zipCode", "town", "pwd"].forEach(attribute => {
    	input = document.querySelector(`#i${attribute}`);
    	data[`${attribute}`] = input.value;
    })
    //console.log(data);
    updateData(`user/modify`, displayError, data);   
}


function getUser (pseudo_or_id) {
	user = getData("user/" + pseudo_or_id, loadUsers);
}

function displayProfile4View(user) {
    ["pseudo", "name", "firstName", "email", "telephone", "street", "zipCode", "town", "pwd"].forEach(attribute => {
        document.querySelector(`#${attribute}`).innerHTML = user[attribute] ? user[attribute] : ""; // vide car non renseigné en base
    })
    SwitchMode(false);
}

function displayProfile4Edit() {
    ["pseudo", "name", "firstName", "email", "telephone", "street", "zipCode", "town", "pwd"].forEach(attribute => {
    	span = document.querySelector(`#${attribute}`);
    	value = span.innerHTML;
    	span.innerHTML = `<input id="i${attribute}" type="text" value="${value}">`;
    })
}
/*
function authenticate(pseudo, password, rememberMe) {
    let authenticated = getData(`user/signin?pseudo=${pseudo}&pwd=${password}&rememberMe=${rememberMe}`, displayProfile);
    getSession();
    return authenticated;
}
*/
function loadUsers(user) {
	displayProfile4View(user);	
    if (user["id"] === connectedUserId) {
        document.title = "Mon profil";
        
        saveBtn.onclick = () => {  
        	updateUser();        	   	
        }

        //pwdh4.style.display = "none";
        //pwdspn.style.display = "none";
             
        editBtn.onclick = () => {
        	displayProfile4Edit();  
        	SwitchMode(true);
        }
        SwitchMode(false);
    }
}


function SwitchMode(isEdit){


    if(isEdit){
    	cancBtn.style.display = "block";
    	saveBtn.style.display = "block";
    	pwdh4.style.display = "block";
    	pwdspn.style.display = "block";
    	editBtn.style.display = "none";
	}else{
		cancBtn.style.display = "none";
		saveBtn.style.display = "none";
    	pwdh4.style.display = "none";
    	pwdspn.style.display = "none";
    	editBtn.style.display = "block";
	}
}