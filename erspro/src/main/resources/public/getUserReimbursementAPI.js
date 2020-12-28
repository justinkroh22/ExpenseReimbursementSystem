// JavaScript source code

async function getUserReimbursements() {
    console.log("AHH")

    let url = 'http://localhost:7000/erspro/getUserReimbursements';
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
    let reim = await getUserReimbursements();

    let reimRecent = reim.slice(-1).pop();

    console.log(reimRecent);

    //console.log(user.firstName);


    let html = `<div class="container">
                            <h6>Most Recent</h6>

                            <p>Reimbursement ID = ${reimRecent.reimbursement_id}</p>
                            <p>Description =  ${reimRecent.description}</p>
                            <p>Amount =  ${reimRecent.amount}</p>
                            <p>Status =  ${reimRecent.status}</p>
                      </div>`;

   // let html = `<div class="container">
     //                       <p>"helllo"</p>
      //                      <h2>hello</h2>
      //                      <div class="email"><a>Hello</a></div>
       //                 </div>`;


    let ddd = document.getElementById("getUserReimbursements");
    ddd.innerHTML = html;
}

renderUserReimbursements();