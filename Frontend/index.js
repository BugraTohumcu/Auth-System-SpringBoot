import {submitLoginForm} from './FormHandlers/Login.js'
import {submitRegisterForm} from './FormHandlers/Register.js'




document.getElementById("login-btn").addEventListener("click",submitLoginForm);
document.getElementById("register-btn").addEventListener("click",submitRegisterForm);


