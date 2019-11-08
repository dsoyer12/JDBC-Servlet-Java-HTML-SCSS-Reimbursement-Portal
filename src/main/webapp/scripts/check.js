let user = {};

window.onload = function() {
	populateUser();
}

function populateUser() {
	// send a GET request to SessionServlet to obtain session information
	fetch("/servletDemo2/session").then(function(response) {
		return response.json(); // parsing json data in the response as a JS object
	}).then(function(data) {
		console.log(data);
		// check whether there is a valid session
		//define behavior for when there is no valid user
		if(data.session === null) {
			window.location = "/servletDemo2/login"
		} else {
			//define behavior for when a user is returned
			user = data;
			document.getElementById("firstname").innerText =user.firstName + " " + user.lastName;
			
		}
	})
}