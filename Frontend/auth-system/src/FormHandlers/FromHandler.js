export async function submitForm(url, username, password, responseElement) {
    if (!username || !password) {
        responseElement.innerHTML = "Username and password are required!";
        return;
    }

    let response = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: JSON.stringify({ username: username, password: password })
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Network response was not ok");
        }
        return res.json();
    })
    .then(data => {
        responseElement.innerHTML = data.message;
    })
    .catch(error => {
        responseElement.innerHTML = `Error: ${error.message}`;
    });

    return response;
}