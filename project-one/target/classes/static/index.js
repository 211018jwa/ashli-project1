window.addEventListener('load', async() => {

    // console.log('EXECUTED');

    let res = await fetch('http://localhost:8080/checklogin', {
        method: 'GET',
        credentials: 'include'
    });

    // If the above request results in a 200 status code, that means we are actually logged in
    // So we need to take the userRole information and determine where to redirect them to
    if (res.status === 200) {
        let userObj = await res.json();

        if (userObj.user_role === 'Employee') {
            window.location.href = 'employee-homepage.html';
        } else if (userObj.user_role === 'Finance Manager') {
            window.location.href = 'finance-manager-homepage.html';
        }
    }

});
/*
LOGIN FUNCTIONALITY
1. grab button element
2.specify what function to execute once the button is clicked
    a.must listen for the "click" event
    b.add an event listener
3.create a function
    a. make a fetch to actually send something from request body
    b. handle the promise

*/
let loginButton = document.querySelector('#login-btn');
loginButton.addEventListener('click', loginButtonClicked)

function loginButtonClicked(e) {
    e.preventDefault();
    login();
    // console.log('some string');
}
async function login() {
    let usernameInput = document.querySelector('#username');
    let passwordInput = document.querySelector('#myInput');

    try {
        let res = await fetch('http://localhost:8080/login', {
            method: 'POST',
            credentials: 'include', //needed to make sure browser retains cookie
            //body = js object
            body: JSON.stringify({
                username: usernameInput.value,
                password: passwordInput.value
            })
        });
        let data = await res.json();

        // let userObject = await res.json();
        // console.log(userObject); returns the userObject from JSON

        if (res.status === 400) {
            let loginErrorMessage = document.createElement('p');
            let loginDiv = document.querySelector('#login-info');
            // loginDiv.innerHTML = '';

            loginErrorMessage.innerHTML = data.message;
            loginErrorMessage.style.color = 'red';
            loginErrorMessage.style.fontSize = 'medium';
            loginErrorMessage.style.font = 'Times New Roman';
            loginDiv.appendChild(loginErrorMessage);
        }
        if (res.status === 200) {
            console.log(data.user_role);
            if (data.user_role === 'Employee') {
                window.location.href = 'employee-homepage.html';
            } else if (data.user_role === 'Finance Manager') {
                window.location.href = 'finance-manager-homepage.html';
            }
        }




    } catch (err) {
        console.log(err);
    }
   
}
