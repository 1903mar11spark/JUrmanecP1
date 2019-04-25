
//page variables 
let user = {};
let reqByEmployee = {};
let allResolvedRequests = {};
let allEmpysAndManagers = {};

//variables 
let realEmpList; 


document.getElementById("col1").addEventListener("click", pullRequestsByEmpId);
document.getElementById("col2").addEventListener("click", mngrGetResolvedRequests);
document.getElementById("col3").addEventListener("click", pullAllEmpsAndManagers);


//onload function1
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
            console.log(user);
               
        }
    });
}

//onload function2
window.addEventListener("load", getAllEmployees, false)

function getAllEmployees() {

    realEmpList = new Array();

    fetch("http://localhost:8086/Project1/employee", { method: "GET" }).then(function (response) {

        return response.json();
    }).then(function (data2) {
        //check whether there was a valid session 
        //define behavior for no user returned 
        if (data2.session === null) {
            window.location = "http://localhost:8086/Project1/profile";
        } else {
            //define behavior for user returned
            allEmployees = data2;

            console.log(allEmployees);


            for (let i = 0; i < allEmployees.length; i++) {

                if (i % 2 == 0) {
                    realEmpList.push(allEmployees[i]);
                }

            }
        }

    });

    console.log(realEmpList);

}


//for column one 
function pullRequestsByEmpId() {

    //if diplay has been closed (set to none), enable display (set to block)
    if (document.getElementById("displayList").style.display = "none") {
        document.getElementById("displayList").style.display = "block"
    }
    //clear contents of list 
    while (displayList.firstChild) {
        displayList.removeChild(displayList.firstChild);
    }


    //generate selection field 
    let displayHeading = document.createElement("h4");
    displayHeading.innerText = "Please enter an employee id";
    document.getElementById("displayList").appendChild(displayHeading);

    let inputField = document.createElement("INPUT");
    inputField.setAttribute("type", "number");
    inputField.id = "inputField";
    document.getElementById("displayList").appendChild(inputField);

    let newButton = document.createElement('button');
    newButton.type = "button";
    newButton.id = "newButton";
    newButton.innerText = "submit";
    document.getElementById("displayList").appendChild(newButton);


    newButton.addEventListener("click", function () {

        console.log(inputField.value);

        let mngrId = user.id;
        console.log(mngrId);
        let empId = inputField.value;

        fetch(`http://localhost:8086/Project1//PullReqsByEmp?id=${empId}&mngrId=${mngrId}`).then(
            function (response) {
                //people.php?name=Joe&age=24

                return response.json();

            }).then(function (data) {

                //check whether there was a valid session 
                //define behavior for no user returned 
                if (data.session === null) {
                    window.location = "http://localhost:8086/Project1/login";
                } else {
                    //define behavior for user returned

                    //
                    reqByEmployee = data;
                    console.log(reqByEmployee);


                    //if diplay has been closed (set to none), enable display (set to block)
                    if (document.getElementById("displayList").style.display = "none") {
                        document.getElementById("displayList").style.display = "block"
                    }

                    //clear contents of list 
                    while (displayList.firstChild) {
                        displayList.removeChild(displayList.firstChild);
                    }


                    for (let i = 0; i < reqByEmployee.length; i++) {

                        let myId = document.createElement("li")
                        myId.innerText = "id: " + reqByEmployee[i].id;
                        //myId.style = "font-weight: bold"
                        document.getElementById("displayList").appendChild(myId)

                        let cost = document.createElement("li")
                        cost.innerText = "cost: " + reqByEmployee[i].cost;
                        document.getElementById("displayList").appendChild(cost);

                        let description = document.createElement("li")
                        description.innerText = "description: " + reqByEmployee[i].description;
                        document.getElementById("displayList").appendChild(description);

                        let receiptImage = document.createElement("li")
                        receiptImage.innerText = "receipt image: " + reqByEmployee[i].receiptImage;
                        document.getElementById("displayList").appendChild(receiptImage);

                        let requestState = document.createElement("li")
                        requestState.innerText = "request state: " + reqByEmployee[i].requestState.requestStateName;
                        document.getElementById("displayList").appendChild(requestState);

                        let empId = document.createElement("li")
                        empId.innerText = "employee id: : " + reqByEmployee[i].employeeId;
                        document.getElementById("displayList").appendChild(empId);

                        let mngrId = document.createElement("li")
                        mngrId.innerText = "manager id: : " + reqByEmployee[i].managerId;
                        document.getElementById("displayList").appendChild(mngrId);

                        let brealLine = document.createElement("BR")
                        document.getElementById("displayList").appendChild(brealLine);
                    }

                    //buttons
                    let button1 = document.createElement("button")
                    button1.innerText = "approve/deny a request";
                    document.getElementById("displayList").appendChild(button1);

                    let button2 = document.createElement("button")
                    button2.innerText = "close record";
                    document.getElementById("displayList").appendChild(button2);

                    button1.addEventListener("click", function () {


                        let newHeading = document.createElement("h6");
                        newHeading.innerText = "please enter a request id number and select choose approve or deny";
                        document.getElementById("displayList").appendChild(newHeading);

                        let inputField = document.createElement("INPUT");
                        inputField.setAttribute("type", "number");
                        inputField.id = "inputField";
                        document.getElementById("displayList").appendChild(inputField);

                        let br = document.createElement("BR")
                        document.getElementById("displayList").appendChild(br)

                        let approveButton = document.createElement("button");
                        approveButton.type = "button";
                        approveButton.id = "approveButton";
                        approveButton.innerText = "approve";
                        document.getElementById("displayList").appendChild(approveButton);

                        let denyButton = document.createElement("button");
                        denyButton.type = "button";
                        denyButton.id = "denyButton";
                        denyButton.innerText = "deny";
                        document.getElementById("displayList").appendChild(denyButton);


                        let newDiv = document.createElement("DIV")
                        newDiv.id = "newDiv";
                        document.getElementById("displayList").appendChild(newDiv);

                        //approve button function 
                        //event handler for submission of approve 
                        approveButton.addEventListener("click", function () {
                            //variables 
                            let reqStateId = 2;
                            reqId = inputField.value;
                            let reqStateName = "approved"

                            console.log("reqId: " + reqId);
                            console.log("reqStateId: " + reqStateId);

                            fetch(`http://localhost:8086/Project1/decide?reqId=${reqId}&reqStateId=${reqStateId}`, {
                                method: "POST"
                            })


                            let br = document.createElement("BR")
                            document.getElementById("displayList").appendChild(br);

                            //display
                            if (document.getElementById("newDiv").style.display = "none") {
                                document.getElementById("newDiv").style.display = "block"
                            }
                            //clear contents  
                            while (newDiv.firstChild) {
                                newDiv.removeChild(newDiv.firstChild);
                            }

                            let messageSpan = document.createElement("SPAN")
                            messageSpan.innerText = `Request number ${reqId} as been updated to have a state of ${reqStateName}.`
                            document.getElementById("newDiv").appendChild(messageSpan);
                        })

                        //deny button function 
                        //event handler for submit of deny
                        denyButton.addEventListener("click", function () {
                            //variables 
                            let reqStateId = 3;
                            reqId = inputField.value;
                            let reqStateName = "denied"

                            console.log("reqId: " + reqId);
                            console.log("reqStateId: " + reqStateId);

                            fetch(`http://localhost:8086/Project1/decide?reqId=${reqId}&reqStateId=${reqStateId}`, {
                                method: "POST"
                            })

                            let br = document.createElement("BR")
                            document.getElementById("displayList").appendChild(br);

                            //display
                            if (document.getElementById("newDiv").style.display = "none") {
                                document.getElementById("newDiv").style.display = "block"
                            }
                            //clear contents  
                            while (newDiv.firstChild) {
                                newDiv.removeChild(newDiv.firstChild);
                            }

                            let messageSpan = document.createElement("SPAN")
                            messageSpan.innerText = `Request number ${reqId} as been updated to have a state of ${reqStateName}.`
                            document.getElementById("newDiv").appendChild(messageSpan);
                        })

                    })

                    //click event to close record 
                    button2.addEventListener("click", function () {
                        document.getElementById("displayList").style.display = "none";
                    });

                }

            });

    })

}

//for column two 
function mngrGetResolvedRequests() {
    fetch("http://localhost:8086/Project1/resolved", { method: "GET" }).then(function (response) {

        return response.json();

    }).then(function (data) {
        //check whether there was a valid session 
        //define behavior for no user returned 
        if (data.session === null) {
            window.location = "http://localhost:8086/Project1/profile";
        } else {
            //define behavior for user returned
            allResolvedRequests = data;

            console.log(allResolvedRequests);


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
            displayHeading.innerText = "Resolved Requests";
            document.getElementById("displayList").appendChild(displayHeading);


            for (let i = 0; i < allResolvedRequests.length; i++) {

                //definitions 
                let empId = allResolvedRequests[i].employeeId;
                let empFirstName; 
                let emplLastName; 

                //add request id number 
                let reqId = document.createElement("li")
                reqId.innerText = "Request id: " + allResolvedRequests[i].id;
                document.getElementById("displayList").appendChild(reqId)
                
                //print emp number and name
                let employeeName = document.createElement("li")
                for (let j = 0; j < realEmpList.length; j ++ ) {
                    if (realEmpList[j].id == allResolvedRequests[i].employeeId) {
                        employeeName.innerText = `Employee id: ${allResolvedRequests[i].employeeId}, Employee name: ${realEmpList[j].firstName} ${realEmpList[j].lastName}`; 
                    }
                }
                document.getElementById("displayList").appendChild(employeeName);
                
                //print manager number and name
                let manager = document.createElement("li")
                for (let j = 0; j < realEmpList.length; j ++ ) {
                    if (realEmpList[j].id == allResolvedRequests[i].managerId) {
                        manager.innerText = `Manager id: ${allResolvedRequests[i].managerId}, Manager name: ${realEmpList[j].firstName}  ${realEmpList[j].lastName}`; 
                    }
                }
                document.getElementById("displayList").appendChild(manager);

                //add the request state 
                
                let reqState = document.createElement("li")
                reqState.innerText = `Request state id: ${allResolvedRequests[i].requestState.id}, Request state name: ${allResolvedRequests[i].requestState.requestStateName}`; 
                document.getElementById("displayList").appendChild(reqState)
            
                //add line break to seperate entries 
                let brealLine = document.createElement("BR")
                document.getElementById("displayList").appendChild(brealLine); 

            }

            //buttons
            let myButton2 = document.createElement("button")
            myButton2.innerText = "close record";
            document.getElementById("displayList").appendChild(myButton2);

            //click event to close employee record view
            myButton2.addEventListener("click", function () {
                document.getElementById("displayList").style.display = "none";
                myButton2.style.display = "none";
            })

        }
    });
}


//for column three
function pullAllEmpsAndManagers() {
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
    displayHeading.innerText = "Employees and Managers";
    document.getElementById("displayList").appendChild(displayHeading);


    for (let i = 0; i < realEmpList.length; i++) {

        let employee = document.createElement("li")
        employee.innerText = `Employee id: " ${realEmpList[i].id}, Employee name: ${realEmpList[i].firstName} ${realEmpList[i].lastName}`;
        document.getElementById("displayList").appendChild(employee);

        let manager = document.createElement("li")
        let manFirstName; 
        let manLastName; 
        let manId; 
        for (let j = 0; j < realEmpList.length; j++) {
            if (realEmpList[i].reportsTo == realEmpList[j].id) {
                manFirstName = realEmpList[j].firstName; 
                manLastName = realEmpList[j].lastName; 
                manId = realEmpList[j].id; 
            }
        }
        manager.innerText = `Manager id: ${manId}, Manager name: ${manFirstName} ${manLastName}`;
        document.getElementById("displayList").appendChild(manager);

        let brealLine = document.createElement("BR")
        document.getElementById("displayList").appendChild(brealLine);

    }

    //buttons
    let myButton2 = document.createElement("button")
    myButton2.innerText = "close record";
    document.getElementById("displayList").appendChild(myButton2);

    //click event to close employee record view
    myButton2.addEventListener("click", function () {
        document.getElementById("displayList").style.display = "none";
        myButton2.style.display = "none";
    })


}