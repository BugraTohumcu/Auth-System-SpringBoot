import {submitForm} from './SubmitForm.js'

export function submitRegisterForm() {
    let username = document.getElementById("user-name").value;
    let password = document.getElementById("password").value;
    let response = document.getElementById("username-response");
    submitForm('http://localhost:8080/register', username, password, response);
}