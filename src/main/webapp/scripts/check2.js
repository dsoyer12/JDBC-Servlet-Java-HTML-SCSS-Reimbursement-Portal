let user = {};
let rem = {};



window.onload = function() {
    populateUser();
    populateReimbursements();
    populateReimbursementsbyPending();

}

function toggleTable() {
    var x = document.getElementById("showALL");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function toggleTable2() {
    var x = document.getElementById("showALL2");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function toggleTable3() {
    var x = document.getElementById("showALL3");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}


$(function() {
    $('#adminfetch').submit(function() {
        $.ajax({
            type: 'POST',
            url: '/adminreimbursement',
            data: { name:reimbursementid, 
                     }
        });
        return false;
    }); 
})
function populateUser() {
    // send a GET request to SessionServlet to obtain session information
    fetch("/servletDemo2/session").then(function(response) {
        return response.json(); // parsing json data in the response as a JS object
    }).then(function(data) {
        console.log(data);
        // check whether there is a valid session
        //define behavior for when there is no valid user
        if (data.session === null) {
            window.location = "/servletDemo2/login"
        } else {
            //define behavior for when a user is returned
            user = data;
            document.getElementById("firstname").innerText = user.firstName + " " + user.lastName;

        }
    })
}




function populateReimbursementsbyId() {
	 // send a GET request to SessionServlet to obtain session information
    // fetch("/servletDemo2/adminreimbursement").then(function(response)
    fetch("/servletDemo2/adminreimbursement").then(function(response)

 
        {
            return response.json(); // parsing json data in the response as a JS object
        }).then(function(data) {
        console.log(data);
        // check whether there is a valid session
        //define behavior for when there is no valid user
        if (data.session === null) {
            window.location = "/servletDemo2/login"
        } else {
 let rem3 = data;
        	let table = document.getElementById("tablebod3");
        	for (let i = 0; i < rem3.length; i++) {
        		var reimbursementid = document.getElementById("adminfetch").value;
        		console.log(reimbursementid);
        		
        		
        		if(rem3[i].id == reimbursementid){let tr = document.createElement("tr");


        	    tr.innerHTML = `
        	        <td>${rem3[i].reportsto}</td>
        	        <td>${rem3[i].id}</td>
        	        <td>${rem3[i].name}</td>
        	        <td>${rem3[i].description}</td>
        	        <td>${rem3[i].amount}</td>
        	        <td>${rem3[i].postdate}</td>
        	        <td>${rem3[i].resolvedate}</td>
        	        <td>${rem3[i].status}</td>`;
        	    table.appendChild(tr);

        	    




        	}
        	}



        }
    })

}


function populateReimbursements() {
    // send a GET request to SessionServlet to obtain session information
    // fetch("/servletDemo2/adminreimbursement").then(function(response)
    fetch("/servletDemo2/adminreimbursement").then(function(response)

 
        {
            return response.json(); // parsing json data in the response as a JS object
        }).then(function(data) {
        console.log(data);
        // check whether there is a valid session
        //define behavior for when there is no valid user
        if (data.session === null) {
            window.location = "/servletDemo2/login"
        } else {

            rem = data;


            let table = document.getElementById("tablebod");
            for (let i = 0; i < rem.length; i++) {
                let tr = document.createElement("tr");


                tr.innerHTML = `
                    <td>${rem[i].reportsto}</td>
                    <td>${rem[i].id}</td>
                    <td>${rem[i].name}</td>
                    <td>${rem[i].description}</td>
                    <td>${rem[i].amount}</td>
                    <td>${rem[i].postdate}</td>
                    <td>${rem[i].resolvedate}</td>
                    <td>${rem[i].status}</td>`;
                table.appendChild(tr);

            }


        }
    })
}



function populateReimbursementsbyPending() {
	 // send a GET request to SessionServlet to obtain session information
    // fetch("/servletDemo2/adminreimbursement").then(function(response)
    fetch("/servletDemo2/adminreimbursement").then(function(response)

 
        {
            return response.json(); // parsing json data in the response as a JS object
        }).then(function(data) {
        console.log(data);
        // check whether there is a valid session
        //define behavior for when there is no valid user
        if (data.session === null) {
            window.location = "/servletDemo2/login"
        } else {
 let rem2 = data;
        	let table = document.getElementById("tablebod2");
        	for (let i = 0; i < rem2.length; i++) {
        		
        		if(rem2[i].status == 'PENDING'){let tr = document.createElement("tr");


        	    tr.innerHTML = `
        	        <td>${rem2[i].reportsto}</td>
        	        <td>${rem2[i].id}</td>
        	        <td>${rem2[i].name}</td>
        	        <td>${rem2[i].description}</td>
        	        <td>${rem2[i].amount}</td>
        	        <td>${rem2[i].postdate}</td>
        	        <td>${rem2[i].resolvedate}</td>
        	        <td>${rem2[i].status}</td>`;
        	    table.appendChild(tr);

        	    




        	}
        	}



        }
    })


  }