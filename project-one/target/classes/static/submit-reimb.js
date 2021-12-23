// Submitting reimbursement
let reimbursementSubmitButton = document.querySelector('#sub-reimb-btn');
reimbursementSubmitButton.addEventListener('click', submitReimbursement);

async function submitReimbursement() {
    let reimbursementAmountInput = document.querySelector('#ramt');
    let reimbursementTypeInput = document.querySelector('#rtype');
    let reimbursementDescriptionInput = document.querySelector('#rdes');
    let reimbursementReceiptInput = document.querySelector('#rfile');

    const file = reimbursementReceiptInput.files[0];


    let formData = new FormData();
    formData.append('reimbursementAmount', reimbursementAmountInput.value);
    formData.append('reimbursementType', reimbursementTypeInput.value);
    formData.append('reimbursementDescription', reimbursementDescriptionInput.value);
    formData.append('reimbursementReceipt', file);



    let res = await fetch('http://localhost:8080/reimbursements', {
        method: 'POST',
        credentials: 'include',
        body: formData
    });

    if (res.status === 201) { //successful update
        window.location.href = 'employee-homepage.html';
    } else if (res.status === 400) {
        let submitReimbursementForm = document.querySelector('#reimbursement-submit-form');

        let data = await res.json();

        let pTag = document.createElement('p');
        pTag.innerHTML = data.message;
        pTag.style.color = 'red';

        submitReimbursementForm.appendChild(pTag);
    }


}