function submitForm(url, username, password, responseElement) {
    if (!username || !password) {
        responseElement.innerHTML = "Username and password are required!";
        return;
    }

    fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
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
    })
    .catch(error => {
        responseElement.innerHTML = `Error: ${error.message}`;
    });
}

function submitRegisterForm() {
    let username = document.getElementById("user-name").value;
    let password = document.getElementById("password").value;
    let response = document.getElementById("username-response");
    submitForm('http://localhost:8080/register', username, password, response);
}

function submitLoginForm() {
    let username = document.getElementById("user-name").value;
    let password = document.getElementById("password").value;
    let response = document.getElementById("username-response");
    submitForm('http://localhost:8080/login', username, password, response);
}
