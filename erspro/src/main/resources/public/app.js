// JavaScript source code


async function getUsers() {
    let url = 'http://localhost:7000/erspro/users';
    try {
        let res = await fetch(url);
        return await res.json();
    } catch (error) {
        console.log(error);
    }
}

async function renderUsers() {
    let users = await getUsers();
    let html = '';
    users.forEach(user => {
        let htmlSegment = ` <tr>
                              <th scope="row">${user.user_id}</th>
                              <td>${user.firstName} ${user.lastName}</td>
                              <td>${user.email}</td>
                              <td>${user.address}</td>
                            </tr>`;

        html += htmlSegment;
    });

    let container = document.getElementById("hello");
    container.innerHTML = html;
}

renderUsers();
