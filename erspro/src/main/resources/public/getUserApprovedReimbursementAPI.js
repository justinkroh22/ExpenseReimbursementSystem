// JavaScript source code
// JavaScript source code
// JavaScript source code

async function getUserReimbursements() {
    console.log("AHH")

    let url = 'http://localhost:7000/erspro/getUserApprovedReimbursements';
    try {
        console.log("Hello worl2www222d!");
        let res = await fetch(url);
        console.log("booo");
        return await res.json();
        //console.log(res);
        console.log("Hello world!");
    } catch (error) {
        //console.log(error);
        console.log("Hello worl2222d!");
    }
}

async function renderUserReimbursements() {
    let reimList = await getUserReimbursements();


    console.log(reimList);

    let html = '';

    reimList.forEach(reim => {
        let htmlSegment = ` <tr>
                              <th scope="row">${reim.reimbursement_id}</th>
                              <td>${reim.description}</td>
                              <td>${reim.amount}</td>
                              <td>${reim.status}</td>
                            </tr>`;

        html += htmlSegment;
    });

    let ddd = document.getElementById("getUserApprovedReimbursements");
    ddd.innerHTML = html;
}

renderUserReimbursements();