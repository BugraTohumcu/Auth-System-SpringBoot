import './register.css'
import { submitRegisterForm } from '../FormHandlers/RegisterHandler.js';
document.querySelector('#app').innerHTML = `
 <div class="container">
        <form id="register-form">
            <h1>Register</h1>
            <div class="register username">
                <input type="text" name="username" id="user-name" required>
                <label for="user-name">Enter username</label>
                <p id="username-response"></p>
            </div>

            <div class="register password">
            <input type="password" name="password" id="password" required>
            <label for="password">Enter Password</label>
            </div>
            <div class="sign-in"><a href="./login.html">Do you have an account?</a></div>
            <button type="button" id="register-btn">Submit</button>

        </form>
    </div>
`;

 document.getElementById("register-btn")?.addEventListener("click",submitRegisterForm);