import './login.css';
import { submitLoginForm} from '../FormHandlers/LoginHandler.js';

document.querySelector('#app').innerHTML = `
  <div class="container">
    <form id="login-form">
      <h1>Login</h1>
      <div class="login username">
        <input type="text" name="username" id="user-name" required>
        <label for="user-name">Enter username</label>
        <p id="username-response"></p>
      </div>

      <div class="login password">
        <input type="password" name="password" id="password" required>
        <label for="password">Enter Password</label>
      </div>
      <div id="sign-up"><a href="./register.html">Don't you have an account?</a></div>
      <button type="button" id="login-btn">Submit</button>
    </form>
  </div>
`;

document.getElementById("login-btn")?.addEventListener("click",submitLoginForm);
