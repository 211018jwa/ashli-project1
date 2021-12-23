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
        // td4.innerHTML = reimbursement.reimb_resolved;

        let td5 = document.createElement('td');
        td5.innerHTML = reimbursement.status;

        let td6 = document.createElement('td');
        td6.innerHTML = reimbursement.type;

        let td7 = document.createElement('td');
        td7.innerHTML = reimbursement.description;

        // let td8 = document.createElement('td');
        // td8.innerHTML = reimbursement.reimb_receipt;

        let td8 = document.createElement('td');
        td8.innerHTML = reimbursement.reimb_author;

        let td9 = document.createElement('td');
        // td10.innerHTML = reimbursement.reimb_resolver;

        if(reimbursement.reimb_resolver != 0){
            td4.innerHTML = reimbursement.reimb_resolved;
            td9.innerHTML = reimbursement.reimb_resolver;
        } else {
            td4.innerHTML = 'Not yet resolved';
            td9.innerHTML = '-';
        }
        let td10 = document.createElement('td');
        let viewReceiptButton = document.createElement('button');
        viewReceiptButton.id = "myButton"
        viewReceiptButton.innerHTML = 'View Receipt'
        td10.appendChild(viewReceiptButton);
        viewReceiptButton.addEventListener('click', async () => {
        let imageElement = document.querySelector('#image');
        imageElement.setAttribute('src', `http://localhost:8080/reimbursements/${reimbursement.reimb_id}/image`);
        })
        
        //  if (res.status === 200) { //successful update
        //         populateTableWithAssignments();
        //     } else if (res.status === 404) {
        //         let viewReceipt = document.getElementById('#myButton');
        //         viewReceipt = appendChild(viewReceiptButton);
        //         let data = await res.json();

        //         let pTag = document.createElement('p');
        //         pTag.innerHTML = data.message;
        //         pTag.style.color = 'red';

        //         viewReceipt.appendChild(pTag);
        //     }
        // });

        


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
        // tr.appendChild(td11);

        tbodyElement.appendChild(tr);

    }
}