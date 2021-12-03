/* Sell page JS */
var submitBtn = document.getElementById("form-submit"),
	cancelBtn = document.getElementById("form-cancel"),
	errorContainer = document.getElementsByClassName("container-error");

/* Utils functions */
function displayError(message) {
	if(message.hasOwnProperty('message')) {
		if(message.message.length > 0) {
			messages = message.message.split('|');
			errorContainer.innerHTML = messages[messages.length-1].replaceAll("\n","<br>");
		}
	} else {
		errorContainer.innerHTML = "";
	}
}

/* Submit form */
submitBtn.onclick = function() {
	/* Get data from form */
	let productData = {
		"name": document.getElementById("product-name").value,
		"description": document.getElementById("product-description").value,
		"category": document.getElementById("product-category").value,
		"priceSeller": document.getElementById("product-price").value,
		"dateStart": document.getElementById("product-start-date").value,
		"dateEnd": document.getElementById("product-end-date").value,
		"street": document.getElementById("pickup-street").value,
		"zipCode": document.getElementById("pickup-zip").value,
		"town": document.getElementById("pickup-town").value
	}
	
	console.log(productData);
	
	/* Create bid item */
	console.log(insertData(`item/new`, displayError, productData));
}

/* Cancel creation */
cancelBtn.onclick = function() {
	window.location.href = "./";
}