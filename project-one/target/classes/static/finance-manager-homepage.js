//1. check if logged in and who is logged in
window.addEventListener('load', async() => {
    let res = await fetch('http://localhost:8080/checklogin', {
        credentials: 'include', //needed to make sure browser retains cookie
        method: 'GET'
    });

    if (res.status === 401) { //401 = client request has not been completed (not logged in so relocate to login)
        window.location.href = 'index.html';
    }

    populateTableWithReimbursements();
});

//logout
let logoutBtn = document.querySelector('#logout');

logoutBtn.addEventListener('click', async() => {

    let res = await fetch('http://localhost:8080/logout', {
        'method': 'POST',
        'credentials': 'include'
    });

    if (res.status === 200) {
        window.location.href = 'index.html';
    }

})

async function populateTableWithReimbursements() {
    let res = await fetch('http://localhost:8080/reimbursements', {
        credentials: 'include',
        method: 'GET'
    });

    let tbodyElement = document.querySelector('#reimbursement-table tbody');
    tbodyElement.innerHTML = '';
    let reimbursementArray = await res.json();

    for (let i = 0; i < reimbursementArray.length; i++) {
        let reimbursement = reimbursementArray[i]; //i = individual reimbursements

        let tr = document.createElement('tr'); //tr element for each reimb. row

        let td1 = document.createElement('td'); //mutiple td for a single row
        td1.innerHTML = reimbursement.reimb_id;

        let td2 = document.createElement('td');
        td2.innerHTML = reimbursement.reimb_amount;

        let td3 = document.createElement('td');
        td3.innerHTML = reimbursement.reimb_submitted;

        let td4 = document.createElement('td');


        let td5 = document.createElement('td');
        td5.innerHTML = reimbursement.status;

        let td6 = document.createElement('td');
        td6.innerHTML = reimbursement.type;

        let td7 = document.createElement('td');
        td7.innerHTML = reimbursement.description;

        // let td8 = document.createElement('td');


        let td8 = document.createElement('td');
        td8.innerHTML = reimbursement.reimb_author;

        let td9 = document.createElement('td');



        let td10 = document.createElement('td');
        let viewReceiptButton = document.createElement('button');
        viewReceiptButton.innerHTML = 'View Receipt'

        viewReceiptButton.addEventListener('click', () => {
            let imageElement = document.querySelector('#image');
            imageElement.setAttribute('src', `http://localhost:8080/reimbursements/${reimbursement.reimb_id}/image`);
        });


        let td11 = document.createElement('td');
        let s = document.createElement('select');
        let update1 = document.createElement("option");
        let update2 = document.createElement("option");
        // let update3 = document.createElement("option");
        // update1.innerHTML = '---Please select an option---'
        update1.innerHTML = 'Approve'
        update2.innerHTML = 'Deny'
        update1.setAttribute('value', 'Approved');
        update2.setAttribute('value', 'Denied');
        td11.appendChild(s);
        s.appendChild(update1);
        s.appendChild(update2);
        // s.appendChild(update3);

        let submitButton = document.createElement('button');
        submitButton.innerText = 'Submit';
        submitButton.addEventListener('click', async() => {

        let formData = new FormData();
            formData.append('reimbursementStatus', s.value);

        let res = await fetch(`http://localhost:8080/reimbursements/${reimbursement.reimb_id}/status`, {
                credentials: 'include',
                method: 'PUT',
                body: formData


            });


        if (res.status === 200) {
                populateTableWithReimbursements(); // Calling the function to repopulate the entire
                // table again
            }
        });

        td10.appendChild(viewReceiptButton);
        td11.appendChild(submitButton);

        if (reimbursement.reimb_resolver != 0) {
            td4.innerHTML = reimbursement.reimb_resolved;
            td9.innerHTML = reimbursement.reimb_resolver;
        } else {
            td4.innerHTML = 'Not yet resolved';
            td9.innerHTML = '-';
        }





        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);
        tr.appendChild(td10);
        tr.appendChild(td11);
        // tr.appendChild(td12);





        tbodyElement.appendChild(tr);



    }
}