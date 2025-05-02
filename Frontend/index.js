document.getElementById("login-btn").addEventListener("click",submitLoginForm);
document.getElementById("register-btn").addEventListener("click",submitRegisterForm);




async function submitForm(url, username, password, responseElement) {
    if (!username || !password) {
        responseElement.innerHTML = "Username and password are required!";
        return;
    }

    let response = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: JSON.stringify({ username: username, password: password })
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Network response was not ok");
        }
        return res.text();
    })
    .then(data => {
        responseElement.innerHTML = data;
        return fetch('http://localhost:8080/hello', {
            method: 'GET',
            headers: { 'Content-Type': 'text/plain' },
            credentials: 'include'
        });
    })
    .catch(error => {
        responseElement.innerHTML = `Error: ${error.message}`;
    });

    return response;
}

function submitRegisterForm() {
    let username = document.getElementById("user-name").value;
    let password = document.getElementById("password").value;
    let response = document.getElementById("username-response");
    submitForm('http://localhost:8080/register', username, password, response);
}

async function submitLoginForm() {
    let username = document.getElementById("user-name").value;
    let password = document.getElementById("password").value;
    let response = document.getElementById("username-response");
    let res = await submitForm('http://localhost:8080/login', username, password, response);

    if(res && res.ok) {
        window.location.href = 'http://localhost:8080/hello';
    }else{
        alert("Login failed! Please check your credentials.");
    }


}
