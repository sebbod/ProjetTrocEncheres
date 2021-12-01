// FUNCTIONS

function getDate(date) {
    return new Date(Date.UTC(date["year"], date["monthValue"] - 1, date["dayOfMonth"], date["hour"], date["minute"], date["second"]));
}

function getDateFormated(date) {
	// jeudi 30 décembre 2021
	//const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
	
	//  RangeError: Value numeric out of range for Date.prototype.toLocaleDateString options property weekday
	//const options = { weekday: 'numeric', year: 'numeric', month: 'numeric', day: 'numeric' };
	
	//return getDate(date).toLocaleDateString("fr-FR",options);
	
	// 30/12/2021
	return getDate(date).toLocaleDateString("fr-FR");
}

function getTimeUntil(date) {
    let milliseconds = date - Date.now();
    let days = Math.floor(milliseconds / (1000 * 60 * 60 * 24));
    milliseconds -= (days * 1000 * 60 * 60 * 24)
    let hours = Math.floor(milliseconds / (1000 * 60 * 60));
    milliseconds -= (hours * 1000 * 60 * 60)
    let minutes = Math.floor(milliseconds / (1000 * 60));
    milliseconds -= (minutes * 1000 * 60)
    let seconds = Math.floor(milliseconds / 1000);
    return {days, hours, minutes, seconds};
}