document.addEventListener("DOMContentLoaded", () => {

  const path = window.location.pathname;

  
  if (path.includes("login.html")) {
    import('./LoginPage/login.js');

  } else if (path.includes("register.html")) {
    import('./RegisterPage/register.js');

  } else {
    window.location.pathname = '/login.html';
    return;
  }

});
