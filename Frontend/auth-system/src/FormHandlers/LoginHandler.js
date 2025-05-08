import {submitForm} from './FromHandler.js'

export async function submitLoginForm() {
    let username = document.getElementById("user-name").value;
    let password = document.getElementById("password").value;
    let response = document.getElementById("username-response");
    await submitForm('http://localhost:8080/login', username, password, response);

    return fetch('http://localhost:8080/hello', {
            method: 'GET',
            headers: { 'Content-Type': 'text/plain' },
            credentials: 'include'
        }).then(()=>{
            window.location.href = 'http://localhost:8080/hello';
        }).catch(err => {
            response.innerHTML = `Error: ${err.message}`
        });
}