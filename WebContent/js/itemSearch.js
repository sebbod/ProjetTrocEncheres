
function search () {
    if (filter === "buy") {
        let {saleIsOpen, isCurrentUser, saleIsWon} = checkboxes;
        return getArticlesToBuy(userSearch, category, saleIsOpen, isCurrentUser, saleIsWon);
    } else if  (filter === "sell") {
        let {saleIsOnGoing, saleIsCreated, saleIsOver} = checkboxes;
        return getArticlesToSell(userSearch, category, saleIsOnGoing, saleIsCreated, saleIsOver);
    }
}

function getArticlesToBuy(userSearch = "", category = "", saleIsOpen = false, isCurrentUser = false, saleIsWon = false){
    const data = {userSearch, category, saleIsOpen, isCurrentUser, saleIsWon};
    let endpoint = "item/search4buy?"
    for (let [pointer, value] of Object.entries(data)) { endpoint += `${pointer}=${value}&`; }
    getData(endpoint, updateResults);
}

function getArticlesToSell(userSearch = "", category = "", saleIsOnGoing = false, saleIsCreated = false, saleIsOver = false) {
    const data = {userSearch, category, saleIsOnGoing, saleIsCreated, saleIsOver};
    let endpoint = "item/search4sell?"
    for (let [pointer, value] of Object.entries(data)) { endpoint += `${pointer}=${value}&`; }
    getData(endpoint, updateResults);
}


function updateResults(items) {
	divItems.innerHTML = '<i></i>';
	itemLst = items ? items : [];
	if(itemLst.length > 0){
		divItems.innerHTML = "";
    	itemLst.forEach(item => { createItem(item) })
	}
}


function createItem(item) {
    let card = document.createElement("div");
    card.classList.add("item");

    let image = document.createElement("img");
    image.src = "https://us.123rf.com/450wm/infadel/infadel1712/infadel171200119/91684826-a-black-linear-photo-camera-logo-like-no-image-available-.jpg?ver=6";
    card.appendChild(image);
    
    let text = document.createElement("div");
    text.className = "item-text";
    
    let title = document.createElement("h5");
	title.textContent = item.name;
    //linkItem.textContent = item.name;
    //title.appendChild(linkItem);
    text.appendChild(title);
    
    /*
    let description = document.createElement("p");
    description.className = "card-text";
    description.textContent = item["description"];
    header.appendChild(description);    
    */
    //
    
    let details = document.createElement("ul");
        
    let price = document.createElement("li");    
    price.textContent = `Mise à prix : ${item["priceSeller"]} crédit(s)`;
    details.appendChild(price);

    let datEnd = document.createElement("li");    
    strDate = getDateFormated(item["dateEnd"]);
    datEnd.textContent = `Fin de l'enchère : ${strDate}`;
    details.appendChild(datEnd);
    
    let seller = document.createElement("li");    
    if(connectedUserId != ""){
    	seller.textContent = "Vendeur : ";
    	let linkUser = document.createElement("a");
    	linkUser.href = "user/profil?id="+item.userIdSeller;
    	linkUser.textContent = item.seller.pseudo;
    	seller.appendChild(linkUser);
    }
    else{
    	seller.textContent = "Vendeur : " + item.seller.pseudo;
    }
    details.appendChild(seller);
    
    let status = document.createElement("li");    
    status.textContent = `status (debug) : ${item["status"]}`;
    details.appendChild(status);
    
    text.appendChild(details);
    card.appendChild(text);
    let linkItem = document.createElement("a");
    linkItem.href = "item/"+item.id;
	linkItem.appendChild(card);
    divItems.appendChild(linkItem);
}
    

function updateCheckboxes () {
    ["#saleIsOpen", "#isCurrentUser", "#saleIsWon"].forEach(enabledCheckbox => {
        let checkbox = document.querySelector(enabledCheckbox);
        checkbox.disabled = filter === "sell";
        checkbox.checked = false;
    });
    ["#saleIsOnGoing", "#saleIsCreated", "#saleIsOver"].forEach(disabledCheckbox => {
        let checkbox = document.querySelector(disabledCheckbox);
        checkbox.disabled = filter === "buy";
        checkbox.checked = false;
    });
}

function activeCheckbox(){
	for (let checkbox of Object.keys(checkboxes)) {
	    document.querySelector(`#${checkbox}`).onclick = $event => {
	        checkboxes[checkbox] = $event.target.checked;
	        search();
	    }
	}
}

function loadCategories(){
	getData(`category`, updateCategories);
}
function updateCategories (categories) {
	categories.forEach(c => { createOption(cateLst, c) })  
}
function createOption(select, category){
    var opt = document.createElement('option');
    opt.value = category.id;
    opt.innerHTML = category.libelle;
    select.appendChild(opt);
}


function refresh(){
	getData("item/refresh", autoSearch);
}
function autoSearch(retVal){
	search();
}