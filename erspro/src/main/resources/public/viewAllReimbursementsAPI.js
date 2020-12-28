// JavaScript source code
// JavaScript source code

async function getAllReimbursements() {
    console.log("AHH")

    let url = 'http://localhost:7000/erspro/getAllReimbursements';
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

async function renderAllReimbursements() {
    let reimList = await getAllReimbursements();


    let html = '';

    reimList.forEach(reim => {
        let htmlSegment = ` <tr>
                              <th scope="row">${reim.user_id}</th>
                              <td>${reim.reimbursement_id}</td>
                              <td>${reim.description}</td>
                              <td>${reim.amount}</td>
                              <td>${reim.status}</td>
                            </tr>`;

        html += htmlSegment;
    });


    // let html = `<div class="container">
    //                       <p>"helllo"</p>
    //                      <h2>hello</h2>
    //                      <div class="email"><a>Hello</a></div>
    //                 </div>`;


    let ddd = document.getElementById("getAllReimbursements");
    ddd.innerHTML = html;
}

renderAllReimbursements();