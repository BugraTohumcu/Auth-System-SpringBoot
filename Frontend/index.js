import {submitLoginForm} from './utils/Login.js'
import {submitRegisterForm} from './utils/Register.js'




document.getElementById("login-btn").addEventListener("click",submitLoginForm);
document.getElementById("register-btn").addEventListener("click",submitRegisterForm);


