
let username = document.getElementById("user-name").value;
let password = document.getElementById("password").value;
let response = document.getElementById("username-response");
function submitRegisterForm(){

    
    fetch('http://localhost:8080/register', {
        method:'POST',
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify({username: username , password: password})
    }).then(res => res.text())
    .then(data => response.innerHTML = data);
}

function submitLoginForm(){
    fetch('http://localhost:8080/login', {
        method:'POST',
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify({username: username , password: password})
    }).then(res => res.text())
    .then(data => response.innerHTML = data);

}


