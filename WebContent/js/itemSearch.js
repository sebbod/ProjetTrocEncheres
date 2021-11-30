
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


function updateResults() {
	divItems.innerHTML = '<i></i>';
    items => {
    	itemLst = items ? items : [];
    	divItems.innerHTML = "";
    	itemLst.forEach(item => { createItem(item) })   
    };
}


function createItem(item) {
    let card = document.createElement("div");
    card.classList.add("card", "mx-auto", "border-dark", "col-12", 'col-sm-6', "col-lg-4", "col-xl-3");
    card.style.width = "18rem";
    let image = document.createElement("img");
    image.src = "https://us.123rf.com/450wm/pavelstasevich/pavelstasevich1811/pavelstasevich181101028/112815904-stock-vector-no-image-available-icon-flat-vector-illustration.jpg?ver=6";
    image.width = 300;
    image.className = "card-img-top";
    card.appendChild(image);
    let header = document.createElement("div");
    header.className = "card-body";
    
    let title = setUpTitle(item);
    header.appendChild(title);
    
    let description = document.createElement("p");
    description.className = "card-text";
    description.textContent = item["description"];
    header.appendChild(description);
    card.appendChild(header);
    
    let details = document.createElement("ul");
    details.classList.add("list-group", "list-group-flush");
    
    let price = document.createElement("li");
    price.className = "list-group-item";
    price.textContent = `Mise à prix: ${item["miseAPrix"]} point(s)`;
    details.appendChild(price);
    /*
     * TODO
    let countdown = setUpCountdown(item);
    details.appendChild(countdown);
    */
    let seller = setUpSeller(item);
    details.appendChild(seller);
    
    card.appendChild(details);
    divItems.appendChild(card);
}

function setUpTitle(item) {
    let title = document.createElement("h5"); // Add link and loadComponent();
    title.className = "card-title";
    title.textContent = item["name"];
    /*
    title.onclick = () => {
    TODO
        getHighestBid(item["id"])
    }
    */
    return title;
}

function setUpSeller(item) {
    let seller = document.createElement("li");
    seller.className = "list-group-item";
    let pseudoSeller = item["seller"]["pseudo"];
    seller.textContent = `Vendeur: ${pseudoSeller}`;
    /*
     * TODO
    seller.onclick = () => {
        loadComponent("user")            
    }*/
    return vendeur;
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