import {submitForm} from './FromHandler.js'

export async function submitLoginForm() {
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