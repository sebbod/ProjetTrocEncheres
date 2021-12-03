/* Bid details page JS */
var backBtn = document.getElementById("back"),
	setCollectedButton = document.getElementById("set-collected"),
	submitOfferBtn = document.getElementById("submit-offer"),
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
if(submitOfferBtn !== null) {
	submitOfferBtn.onclick = function() {
		/* Get data form */
		let bid = {
			"itemId": productId,
			"amount": parseInt(document.getElementById("bid-offer").value)
		}
		
		console.log(bid);
		
		/* Send bid */
		console.log(insertData(`bid/new`, displayError, bid));
	}
}

/* Return home button */
backBtn.onclick = function() {
	console.log("click")
	window.location.href = "../";
}