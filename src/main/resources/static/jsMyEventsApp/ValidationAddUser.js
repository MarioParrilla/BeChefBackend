let btnSubmit = document.getElementById("btnSubmit");
let txtPassword = document.getElementById("password");
let txtrPassword = document.getElementById("rpassword");
let txtEmail = document.getElementById("email");
let lblPassword = document.getElementById("lblPassword");
let lblrPassword = document.getElementById("lblrPassword");
let lblEmail = document.getElementById("lblEmail");

lblPassword.style.color = "red";
lblrPassword.style.color = "red";
lblEmail.style.color = "red";

let Comprobaciones = {
    password: false,
    rPassword: false,
    email: false,
};

btnSubmit.disabled = true;
window.addEventListener("load", () =>{
    checkPassword();
    checkrPassword();
    checkEmail();
});

txtPassword.addEventListener("change", () =>{
    checkPassword();
});

txtrPassword.addEventListener("change", () => {
    checkrPassword();
});

txtEmail.addEventListener("change", () =>{
    checkEmail();
});


let checkInputs = () => {
    if (Comprobaciones.password===true && Comprobaciones.rPassword===true && Comprobaciones.email===true) btnSubmit.disabled = false;
    else btnSubmit.disabled = true;
};


let checkPassword = () => {
    let password = txtPassword.value;
    let rPassword = txtrPassword.value;
    if(password.length>4 &&password.length<20 && rPassword===password){
        lblPassword.innerText = "✔️";
        lblrPassword.innerText = "✔️";
        Comprobaciones.password=true;
        Comprobaciones.rPassword=true;
        checkInputs();
    }
    else if(password.length>4 && password.length<20){
        lblPassword.innerText = "✔️";
        Comprobaciones.password=true;
        lblrPassword.innerText = "❌¡Introduce la misma contraseña!❌";
        Comprobaciones.rPassword=false;
        checkInputs();
    }
    else if (password.length<5){
        lblPassword.innerText = "❌¡Introduce una contraseña mayor a 4 digitos!❌";
        Comprobaciones.password=false;
        checkInputs();
    }
    else{
        lblPassword.innerText = "❌¡Introduce una contraseña menor a 20 digitos!❌";
        Comprobaciones.password=false;
        checkInputs();
    }
};

let checkrPassword = () => {
    let rPassword = txtrPassword.value;
    let password = txtPassword.value;
    if(password.length>4 && rPassword.length<20 && rPassword===password){
        lblrPassword.innerText = "✔️";
        lblPassword.innerText = "✔️";
        Comprobaciones.rPassword=true;
        Comprobaciones.password=true;
        checkInputs();
    }
    else if (rPassword!==password){
        lblrPassword.innerText = "❌¡Introduce la misma contraseña!❌";
        Comprobaciones.rPassword=false;
        checkInputs();
    }
    else if (rPassword.length<5){
        lblrPassword.innerText = "❌¡Introduce una contraseña mayor a 4 digitos!❌";
        Comprobaciones.rPassword=false;
        checkInputs();
    }
    else{
        lblrPassword.innerText = "❌¡Introduce una contraseña menor a 20 digitos!❌";
        Comprobaciones.rPassword=false;
        checkInputs();
    }
};

let checkEmail = () => {
    let email = txtEmail.value;
    if(email.length<50 && /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
        lblEmail.innerText = "✔️";
        Comprobaciones.email=true;
        checkInputs();
    }else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))){
        lblEmail.innerText = "❌¡Introduce un email correcto!❌";
        Comprobaciones.email=false;
        checkInputs();
    }
    else{
        lblEmail.innerText = "❌¡Introduce una contraseña menor a 50 digitos!❌";
        Comprobaciones.email=false;
        checkInputs();
    }
};