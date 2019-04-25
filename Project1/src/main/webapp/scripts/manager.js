let user = {};
let reReq = {};
let reInbox = {};
let approvedReqs = {}; 
let deniedReqs = {}; 

window.addEventListener("load", populateUser, false)


function populateUser() {
	//send GET request to SessionSerlet to obtain session information 
	fetch("http://localhost:8086/Project1/session").then(function (response) {

		return response.json();

	}).then(function (data) {
		//check whether there was a valid session 
		//define behavior for no user returned 
		if (data.session === null) {
			window.location = "http://localhost:8086/Project1/login";
		} else {
			//define behavior for user returned
			user = data;

			//welcome message 
			document.getElementById("welcomeName").textContent = user.firstname + " !";

			//mouse event listeners 
			document.getElementById("col1").addEventListener("mouseover", function () {
				document.getElementById("col1").style.backgroundColor = "grey";
			})
			document.getElementById("col1").addEventListener("mouseout", function () {
				document.getElementById("col1").style.backgroundColor = "#a5fcda";
			})

			//click event to see employee record
			document.getElementById("col1").addEventListener("click", function () {

				//enable display if set to none
				if (document.getElementById("displayList").style.display = "none") {
					document.getElementById("displayList").style.display = "block"
				}

				//clear contents of list 
				while (displayList.firstChild) {
					displayList.removeChild(displayList.firstChild);
				}

				/*
				if (document.getElementById("userButton1").style.display = "none") {
					document.getElementById("userButton1").style.display = "block"
				}
				if (document.getElementById("userButton2").style.display = "none") {
					document.getElementById("userButton2").style.display = "block"
				}
				*/

				//heading
				let displayHeading = document.createElement("h4");
				displayHeading.innerText = "Employee Record";
				document.getElementById("displayList").appendChild(displayHeading);

				//fields
				let myId = document.createElement("li");
				myId.innerText = "usernmame: " + user.username;
				document.getElementById("displayList").appendChild(myId);

				let myFn = document.createElement("li");
				myFn.innerText = "firstname: " + user.firstname;
				document.getElementById("displayList").appendChild(myFn);

				let myLn = document.createElement("li");
				myLn.innerText = "lastname: " + user.lastname;
				document.getElementById("displayList").appendChild(myLn);

				let myEmail = document.createElement("li");
				myEmail.innerText = "email: " + user.email;
				document.getElementById("displayList").appendChild(myEmail);

				//buttons
				let myButton1 = document.createElement("button")
				myButton1.innerText = "update record";
				document.getElementById("displayList").appendChild(myButton1);

				myButton1.addEventListener("click", updateRecordClick);

				let myButton2 = document.createElement("button")
				myButton2.innerText = "close record";
				document.getElementById("displayList").appendChild(myButton2);

				//click event to close employee record view
				myButton2.addEventListener("click", function () {
					document.getElementById("displayList").style.display = "none";
					myButton1.style.display = "none";
					myButton2.style.display = "none";
				})

				/*
				document.getElementById("userButton1").innerText = "update record";
				document.getElementById("userButton2").innerText = "close view";
				*/

				/*
				document.getElementById("userRecordHeading").innerText = "Employee Record";  
				document.getElementById("username").innerText = "usernmame: " + user.username; 
				document.getElementById("firstname").innerText = "firstname: " + user.firstname; 
				document.getElementById("lastname").innerText = "lastname: " + user.lastname; 
				document.getElementById("email").innerText = "email: " + user.email; 
				document.getElementById("userButton1").innerText = "update record";
				document.getElementById("userButton2").innerText = "close view";
				*/
			})

			//click event to close employee record view
			/*
			document.getElementById("userButton2").addEventListener("click", function() {  
				document.getElementById("userDetails").style.display = "none"; 
				document.getElementById("userButton1").style.display = "none"; 
				document.getElementById("userButton2").style.display = "none";
			})	
			*/
		}
		//user is still in scope here
		//console.log(user); 
		populateReReq();
	});
}


function updateRecordClick() {
	
	//first name form 
	let updateForm = document.createElement("FORM");
	updateForm.action = "fnUpdate"
	updateForm.method = "POST"
	updateForm.innerText = "First name"
	document.getElementById("displayList").appendChild(updateForm);

	//padding for top of 1st name form
	updateForm.style.paddingTop = "2em";

	let nameField = document.createElement('INPUT');
	nameField.name="firstName"; 
	nameField.type="text"; 
	updateForm.appendChild(nameField);

	let submitButton = document.createElement('INPUT');
	submitButton.setAttribute("name", "updatFn");
	submitButton.setAttribute("type", "submit");
	updateForm.appendChild(submitButton);

	//fieldSet.appendChild(updateForm)

	//last last name 
	let updateForm2 = document.createElement("FORM");
	updateForm2.setAttribute("action", "lnUpdate");
	updateForm2.setAttribute("method", "POST");
	updateForm2.innerText = "Last name"
	document.getElementById("displayList").appendChild(updateForm2);

	updateForm2.style.paddingTop = "1em";

	let lnField = document.createElement('INPUT');
	lnField.setAttribute("name", `lastName `);
	lnField.setAttribute("type", "text");
	updateForm2.appendChild(lnField);

	let submitButton2 = document.createElement('INPUT');
	submitButton2.setAttribute("name", "updatLn");
	submitButton2.setAttribute("type", "submit");
	updateForm2.appendChild(submitButton2);

	//fieldSet.appendChild(updateForm2)

	//email
	let updateForm3 = document.createElement("FORM");
	updateForm3.setAttribute("action", "emailUpdate");
	updateForm3.setAttribute("method", "POST");
	updateForm3.innerText = "Email"
	document.getElementById("displayList").appendChild(updateForm3);

	updateForm3.style.paddingTop = "1em";

	let emField = document.createElement('INPUT');
	emField.setAttribute("name", `lastName `);
	emField.setAttribute("type", "text");
	updateForm3.appendChild(emField);

	let submitButton3 = document.createElement('INPUT');
	submitButton3.setAttribute("name", "updatLn");
	submitButton3.setAttribute("type", "submit");
	updateForm3.appendChild(submitButton3);

	//fieldSet.appendChild(updateForm3)
	

}



function populateReReq() {
	//send GET request to SessionSerlet to obtain session information 
	fetch(`http://localhost:8086/Project1/request?id=${user.id}`).then(function (response) {
		//http://localhost:8086/Project1/request?id=1

		return response.json();

	}).then(function (data) {
		//check whether there was a valid session 
		//define behavior for no user returned 
		if (data.session === null) {
			window.location = "http://localhost:8086/Project1/profile";
		} else {
			//define behavior for user returned

			reReq = data;

			console.log(reReq);

			getUserApprovedReqs(); 

			getUserDeniedReqs()

			//welcome message 
			//document.getElementById("welcomeName").textContent = user.firstname + " !"; 

			//mouse event listeners 
			document.getElementById("col2").addEventListener("mouseover", function () {
				document.getElementById("col2").style.backgroundColor = "grey";
			})
			document.getElementById("col2").addEventListener("mouseout", function () {
				document.getElementById("col2").style.backgroundColor = "#a5ebfc";
			})

			//click event to see reimbursement request records
			document.getElementById("col2").addEventListener("click", function () {


				//if diplay has been closed (set to none), enable display (set to block)
				if (document.getElementById("displayList").style.display = "none") {
					document.getElementById("displayList").style.display = "block"
				}

				//clear contents of list 
				while (displayList.firstChild) {
					displayList.removeChild(displayList.firstChild);
				}


				//heading 
				let displayHeading = document.createElement("h4");
				displayHeading.innerText = "Your Requests";
				document.getElementById("displayList").appendChild(displayHeading);

				//fields

				for (let i = 0; i < reReq.length; i++) {

					let myId = document.createElement("li")
					myId.innerText = "id: " + reReq[i].id;
					//myId.style = "font-weight: bold"
					document.getElementById("displayList").appendChild(myId)

					let cost = document.createElement("li")
					cost.innerText = "cost: " + reReq[i].cost;
					document.getElementById("displayList").appendChild(cost);

					let description = document.createElement("li")
					description.innerText = "description: " + reReq[i].description;
					document.getElementById("displayList").appendChild(description);

					let receiptImage = document.createElement("li")
					receiptImage.innerText = "receipt image: " + reReq[i].receiptImage;
					document.getElementById("displayList").appendChild(receiptImage);

					let requestState = document.createElement("li")
					requestState.innerText = "request state: " + reReq[i].requestState.requestStateName;
					document.getElementById("displayList").appendChild(requestState);

					let brealLine = document.createElement("BR")
					document.getElementById("displayList").appendChild(brealLine);
				}

				for (let i = 0; i < approvedReqs.length; i++) {

					let myId = document.createElement("li")
					myId.innerText = "id: " + approvedReqs[i].id;
					//myId.style = "font-weight: bold"
					document.getElementById("displayList").appendChild(myId)

					let cost = document.createElement("li")
					cost.innerText = "cost: " + approvedReqs[i].cost;
					document.getElementById("displayList").appendChild(cost);

					let description = document.createElement("li")
					description.innerText = "description: " + approvedReqs[i].description;
					document.getElementById("displayList").appendChild(description);

					let receiptImage = document.createElement("li")
					receiptImage.innerText = "receipt image: " + approvedReqs[i].receiptImage;
					document.getElementById("displayList").appendChild(receiptImage);

					let requestState = document.createElement("li")
					requestState.innerText = "request state: " + approvedReqs[i].requestState.requestStateName;
					document.getElementById("displayList").appendChild(requestState);

					let brealLine = document.createElement("BR")
					document.getElementById("displayList").appendChild(brealLine);

				}

				for (let i = 0; i < deniedReqs.length; i++) {

					let myId = document.createElement("li")
					myId.innerText = "id: " + deniedReqs[i].id;
					//myId.style = "font-weight: bold"
					document.getElementById("displayList").appendChild(myId)

					let cost = document.createElement("li")
					cost.innerText = "cost: " + deniedReqs[i].cost;
					document.getElementById("displayList").appendChild(cost);

					let description = document.createElement("li")
					description.innerText = "description: " + deniedReqs[i].description;
					document.getElementById("displayList").appendChild(description);

					let receiptImage = document.createElement("li")
					receiptImage.innerText = "receipt image: " + deniedReqs[i].receiptImage;
					document.getElementById("displayList").appendChild(receiptImage);

					let requestState = document.createElement("li")
					requestState.innerText = "request state: " + deniedReqs[i].requestState.requestStateName;
					document.getElementById("displayList").appendChild(requestState);

					let brealLine = document.createElement("BR")
					document.getElementById("displayList").appendChild(brealLine);

				}

				//buttons
				let reqButton1 = document.createElement("button")
				reqButton1.innerText = "make new request";
				document.getElementById("displayList").appendChild(reqButton1);

				let myButton2 = document.createElement("button")
				myButton2.innerText = "close record";
				document.getElementById("displayList").appendChild(myButton2);

				let mngrButton = document.createElement("button")
				mngrButton.innerText = "view request inbox";
				document.getElementById("displayList").appendChild(mngrButton);

				let mngrButton2 = document.createElement("button")
				mngrButton2.innerText = "view all manager options";
				document.getElementById("displayList").appendChild(mngrButton2);

				//click event to close employee record view
				myButton2.addEventListener("click", function () {
					document.getElementById("displayList").style.display = "none";
				})
				

				//add evevent listener here to pull function for making new request
				reqButton1.addEventListener("click", function () {
					window.location = "http://localhost:8086/Project1/requestForm.html";
				});

				//listener of button for pulling manager inbox
				mngrButton.addEventListener("click", populateInbox, false);

				//click evennt to see all manager options 
				mngrButton2.addEventListener("click", function () {
					window.location = "http://localhost:8086/Project1/managerOptions.html";
				})

			})

			//click event to close employee record view
			/*
			document.getElementById("userButton2").addEventListener("click", function() {  
				document.getElementById("requests").style.display = "none"; 
				document.getElementById("reqButton1").style.display = "none"; 
				document.getElementById("reqButton2").style.display = "none";
			})	
			*/
		}
	});

}

function populateInbox() {

	fetch(`http://localhost:8086/Project1/manager?id=${user.id}`).then(function (response) {

		return response.json();

	}).then(function (data) {
		//check whether there was a valid session 
		//define behavior for no user returned 
		if (data.session === null) {
			window.location = "http://localhost:8086/Project1/profile";
		} else {
			//define behavior for request list returned

			reInbox = data;
			console.log(reInbox);

		}

		//if diplay has been closed (set to none), enable display (set to block)
		if (document.getElementById("displayList").style.display = "none") {
			document.getElementById("displayList").style.display = "block"
		}

		//clear contents of list 
		while (displayList.firstChild) {
			displayList.removeChild(displayList.firstChild);
		}

		//heading
		let displayHeading = document.createElement("h4");
		displayHeading.innerText = "Manager Request Inbox";
		document.getElementById("displayList").appendChild(displayHeading);

		//fields 
		for (let i = 0; i < reInbox.length; i++) {

			let myId = document.createElement("li")
			myId.innerText = "id: " + reInbox[i].id;
			document.getElementById("displayList").appendChild(myId)

			let cost = document.createElement("li")
			cost.innerText = "cost: " + reInbox[i].cost;
			document.getElementById("displayList").appendChild(cost);

			let description = document.createElement("li")
			description.innerText = "description: " + reInbox[i].description;
			document.getElementById("displayList").appendChild(description);

			let receiptImage = document.createElement("li")
			receiptImage.innerText = "receipt image: " + reInbox[i].receiptImage;
			document.getElementById("displayList").appendChild(receiptImage);

			let requestState = document.createElement("li")
			requestState.innerText = "request state: " + reInbox[i].requestState.requestStateName;
			document.getElementById("displayList").appendChild(requestState);

			let requestFrom = document.createElement("li")
			requestFrom.innerText = "from employee number: " + reInbox[i].employeeId;
			document.getElementById("displayList").appendChild(requestFrom);

			let brealLine = document.createElement("BR")
			document.getElementById("displayList").appendChild(brealLine);
		}

		//buttons
		let reqButton1 = document.createElement("button")
		reqButton1.innerText = "submit a request";
		document.getElementById("displayList").appendChild(reqButton1);

		let myButton2 = document.createElement("button")
		myButton2.innerText = "close request inbox";
		document.getElementById("displayList").appendChild(myButton2);

		let mngrButton2 = document.createElement("button")
		mngrButton2.innerText = "view all manager options";
		document.getElementById("displayList").appendChild(mngrButton2);

		//click event to close employee record view
		myButton2.addEventListener("click", function () {
			document.getElementById("displayList").style.display = "none";
			reqButton1.style.display = "none";
			myButton2.style.display = "none";
			//mngrButton.style.display = "none";
		})

		//click evevent  to make new requests 
		reqButton1.addEventListener("click", function () {
			window.location = "http://localhost:8086/Project1/requestForm.html";
		});

		//click evennt to see all manager options 
		mngrButton2.addEventListener("click", function () {
			window.location = "http://localhost:8086/Project1/managerOptions.html";
		})
	})
}


function getUserApprovedReqs() { 
	fetch(`http://localhost:8086/Project1/viewApproved?id=${user.id}`).then(function (response) {
		//http://localhost:8086/Project1/request?id=1
	
		return response.json();

	}).then(function (data) {
		if (data.session === null) {
			window.location = "http://localhost:8086/Project1/profile";
		} else {
			//define behavior for user returned
			approvedReqs = data;
			console.log(approvedReqs);
		}
	});

	return approvedReqs; 
}


function getUserDeniedReqs() { 
	fetch(`http://localhost:8086/Project1/viewDenied?id=${user.id}`).then(function (response) {
		//http://localhost:8086/Project1/request?id=1
	
		return response.json();

	}).then(function (data) {
		if (data.session === null) {
			window.location = "http://localhost:8086/Project1/profile";
		} else {
			//define behavior for user returned
			deniedReqs = data;
			console.log(deniedReqs);
		}
	});
	
	return deniedReqs; 
}


