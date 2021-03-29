export async function getAllUsers() {

    const response = await fetch('/api/users');
    return await response.json();
}

export async function getAllUsersByEmail(data) {

    const response = await fetch('/api/users/email?email=' + data);
    return await response.json();
}

export async function createUser(data) {
    const response = await fetch(`/api/user`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
      })
          return await response;
}

export async function deleteUser(data) {
    const response = await fetch(`/api/users/'+data+'/delete`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
      })
          return await response;
}