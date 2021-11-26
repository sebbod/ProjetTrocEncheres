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

function getData(){
	const data = {};
    ["pseudo", "name", "firstName", "email", "telephone", "street", "zipCode", "town", "pwd"].forEach(attribute => {
    	input = document.querySelector(`#i${attribute}`);
    	data[`${attribute}`] = input.value;
    })
    //console.log(data);
    return data;
}

function insertUser() {
    insertData(`user/signup`, displayError, getData());
}

function updateUser() {
    updateData(`user/modify`, displayError, getData());   
}

function getUser (pseudo_or_id) {
	user = getData(`user/` + pseudo_or_id, loadUsers);
}

function displayProfile4View(user) {
    ["pseudo", "name", "firstName", "email", "telephone", "street", "zipCode", "town", "pwd"].forEach(attribute => {
        document.querySelector(`#${attribute}`).innerHTML = user[attribute] ? user[attribute] : ""; // vide car non renseigné en base
    })
    SwitchMode('view');
}

function displayProfile4Edit() {
    ["pseudo", "name", "firstName", "email", "telephone", "street", "zipCode", "town", "pwd"].forEach(attribute => {
    	span = document.querySelector(`#${attribute}`);
    	value = span.innerHTML;
    	span.innerHTML = `<input id="i${attribute}" type="text" value="${value}">`;
    })
}

function displayProfile4Add() {
    ["pseudo", "name", "firstName", "email", "telephone", "street", "zipCode", "town", "pwd"].forEach(attribute => {
        document.querySelector(`#${attribute}`).innerHTML = ""; // vide
    })
    SwitchMode('add');
}

function loadUsers(user) {
	displayProfile4View(user);	
    if (user["id"] === connectedUserId) {
        document.title = "Mon profil";
        
        saveBtn.onclick = () => {  
        	updateUser();        	   	
        }

        editBtn.onclick = () => {
        	displayProfile4Edit();  
        	SwitchMode('edit');
        }
        SwitchMode('view');
    }
}


function SwitchMode(mode){
    switch(mode){
    case 'edit':
    	addBtn.style.display = "none";
    	cancBtn.style.display = "block";
    	saveBtn.style.display = "block";
    	pwdh4.style.display = "block";
    	pwdspn.style.display = "block";
    	editBtn.style.display = "none";
    	break;
    case 'view':
    	addBtn.style.display = "none";
		cancBtn.style.display = "none";
		saveBtn.style.display = "none";
    	pwdh4.style.display = "none";
    	pwdspn.style.display = "none";
    	editBtn.style.display = "block";
    	break;
    case 'add':
    	addBtn.style.display = "block";
    	cancBtn.style.display = "block";
		saveBtn.style.display = "none";
    	pwdh4.style.display = "block";
    	pwdspn.style.display = "block";
    	editBtn.style.display = "none";
    	break;
	}
}