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
    .then(async res => {
        const data = await res.json();
        if (!res.ok) {
            throw new Error(`${data.message}`);
        }
        return data;
    })
    .then(data => {
        responseElement.innerHTML = data.message;
        return data.success;
    })
    .catch(error => {
        responseElement.innerHTML = `Error: ${error.message}`;
    });

    return response;
}