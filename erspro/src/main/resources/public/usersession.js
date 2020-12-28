// JavaScript source code

async function getUserSessionData() {
    console.log("AHH")

    let url = 'http://localhost:7000/erspro/getUserSessionData';
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

async function renderUserSessionData() {
    let user = await getUserSessionData();

    console.log(user);
    let html = `<div class="container">
                            <h6>Your Info</h6>

                            <p>Name: ${user.firstName} ${user.lastName}</p>
                            <p>ERSPRO ID:  ${user.user_id}</p>
                            <p>Email:  ${user.email}</p>
                            <p>Address:  ${user.address}</p>
                      </div>`;

   // let html = `<div class="container">
     //                       <p>"helllo"</p>
      //                      <h2>hello</h2>
      //                      <div class="email"><a>Hello</a></div>
       //                 </div>`;


    let bbb = document.getElementById("userinfo");
    bbb.innerHTML = html;
}

renderUserSessionData();