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

function actionAfterDelete(msg){	
	if(msg.hasOwnProperty('message')){
		if( msg.message.length > 0){			
			msgs = msg.message.split('|');
			errorMsg.innerHTML = msgs[msgs.length-1].replaceAll("\n","<br>");
		}
	}else{
		errorMsg.innerHTML = ""
		window.location.href = loginPage;
	}		
}

function getFormData(){
	const data = {};
    ["pseudo", "name", "firstName", "email", "telephone", "street", "zipCode", "town", "pwd"].forEach(attribute => {
    	input = document.querySelector(`#i${attribute}`);
    	data[`${attribute}`] = input.value;
    })
    //console.log(data);
    return data;
}

function checkPwd(){
	pwd1 = document.querySelector("#ipwd")
    pwd2 = document.querySelector("#ipwdbis")
   	return pwd1.value === pwd2.value;    
}
function insertUser() {
	if(checkPwd()){
		insertData(`user/signup`, displayError, getFormData());
	}else{
		errorMsg.innerHTML = "La confirmation du mot de passe n'est pas correct"
	}
}

function updateUser() {
	if(checkPwd()){
		updateData(`user/modify`, displayError, getFormData());   
	}else{
		errorMsg.innerHTML = "La confirmation du mot de passe n'est pas correct"
	}    
}

function deleteUser(id) {
	deleteData(`user/delete/` + id, actionAfterDelete);   
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
    ["pseudo", "name", "firstName", "email", "telephone", "street", "zipCode", "town", "pwd", "pwdbis"].forEach(attribute => {
    	span = document.querySelector(`#${attribute}`);
    	value = span.innerHTML;
    	if(span.id == "pwd" || span.id == "pwdbis"){
    		span.innerHTML = `<input id="i${attribute}" type="password" value="">`;
    	}else{
    		span.innerHTML = `<input id="i${attribute}" type="text" value="${value}">`;	
    	} 
    })
    cancBtn.onclick = () => {  
    	window.history.back();        	   	
    }
    delBtn.onclick = () => {  
    	deleteUser(connectedUserId);        	   	
    }
}

function displayProfile4Add() {
    ["pseudo", "name", "firstName", "email", "telephone", "street", "zipCode", "town", "pwd", "pwdbis"].forEach(attribute => {
    	span = document.querySelector(`#${attribute}`);
    	value = span.innerHTML;
    	if(span.id == "pwd" || span.id == "pwdbis"){
    		span.innerHTML = `<input id="i${attribute}" type="password" value="">`;
    	}else{
    		span.innerHTML = `<input id="i${attribute}" type="text" value="">`;	
    	}    	
    })
    SwitchMode('add');
    addBtn.onclick = () => {  
    	insertUser();        	   	
    }
    cancBtn.onclick = () => {  
    	window.history.back();        	   	
    }
}

function loadUsers(user) {
	displayProfile4View(user);	
    if (user["id"] === parseInt(connectedUserId)) {
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
    	delBtn.style.display = "inline";
    	cancBtn.style.display = "inline";
    	saveBtn.style.display = "inline";
    	pwdh4.style.display = "inline";
    	pwdspn.style.display = "inline";
    	pwdbish4.style.display = "inline";
    	pwdbisspn.style.display = "inline";
    	editBtn.style.display = "none";
    	break;
    case 'view':
    	addBtn.style.display = "none";
    	delBtn.style.display = "none";
		cancBtn.style.display = "inline";
		saveBtn.style.display = "none";
    	pwdh4.style.display = "none";
    	pwdspn.style.display = "none";
    	pwdbish4.style.display = "none";
    	pwdbisspn.style.display = "none";
    	editBtn.style.display = "inline";
    	break;
    case 'add':
    	addBtn.style.display = "inline";
    	delBtn.style.display = "none";
    	cancBtn.style.display = "inline";
		saveBtn.style.display = "none";
    	pwdh4.style.display = "inline";
    	pwdspn.style.display = "inline";
    	pwdbish4.style.display = "inline";
    	pwdbisspn.style.display = "inline";
    	editBtn.style.display = "none";
    	break;
	}
}