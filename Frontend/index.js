import {submitLoginForm} from './FormHandlers/Login.js'
import {submitRegisterForm} from './FormHandlers/Register.js'




const loginBtn = document.getElementById("login-btn");
if (loginBtn) {
    loginBtn.addEventListener("click", submitLoginForm);
}

const registerBtn = document.getElementById("register-btn");
if (registerBtn) {
    registerBtn.addEventListener("click", submitRegisterForm);
}

