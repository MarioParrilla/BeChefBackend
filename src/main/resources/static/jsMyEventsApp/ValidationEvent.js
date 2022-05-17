let btnSubmit = document.getElementById("btnSubmit");
let txtUserOwner = document.getElementById("userowner");
let txtUserSummoner = document.getElementById("usersummoner");
let txtEventName = document.getElementById("eventname");
let txtTema = document.getElementById("tema");
let txtInicio = document.getElementById("starttime");
let txtFin = document.getElementById("endtime");
let txtCoordenadas = document.getElementById("coordinates");
let txtVideomeeting = document.getElementById("videomeeting");
let radioCoordinates = document.getElementById("eventpreferencecoordinates");
let radioVideomeeting = document.getElementById("eventpreferencevideomeeting");

let lblUserOwner = document.getElementById("lblUserOwner");
let lblUserSummoner = document.getElementById("lblUserSummoner");
let lblEventName = document.getElementById("lblEventName");
let lblTema = document.getElementById("lblTema");
let lblInicio = document.getElementById("lblInicio");
let lblFin = document.getElementById("lblFin");
let lblCoordenadas = document.getElementById("lblCoordenadas");
let lblVideomeeting = document.getElementById("lblVideomeeting");

let divCoordenadas = document.getElementById("coorDiv");
let divVideomeeting = document.getElementById("videoDiv");


lblUserOwner.style.color = "red";
if(lblUserSummoner!=null) lblUserSummoner.style.color = "red";
lblEventName.style.color = "red";
lblTema.style.color = "red";
lblInicio.style.color = "red";
lblFin.style.color = "red";
lblCoordenadas.style.color = "red";
lblVideomeeting.style.color = "red";

let Comprobaciones = {
    userOwner: false,
    userSummoner: false,
    eventName: false,
    tema: false,
    inicio: false,
    fin: false,
    coordenadas: false,
    videomeeting: false,
};

if(lblUserSummoner==null) Comprobaciones.userSummoner = true;

let checkInputs = () => {
    if (Comprobaciones.userOwner===true && Comprobaciones.userSummoner===true && Comprobaciones.eventName===true && Comprobaciones.tema===true && Comprobaciones.inicio===true
        && Comprobaciones.fin===true && Comprobaciones.coordenadas===true && Comprobaciones.videomeeting===true) btnSubmit.disabled = false;
    else btnSubmit.disabled = true;
};

btnSubmit.disabled = true;



let checkRadioCoor = () => {
    let textCoor = txtCoordenadas.value;
    if(textCoor.length<=100 && textCoor.length>9){
        lblCoordenadas.innerText = "✔️";
        Comprobaciones.coordenadas=true;
        Comprobaciones.videomeeting=true;
        checkInputs();
    }else if(textCoor.length<8){
        lblCoordenadas.innerText = "❌¡Introduce unas coordenadas mayor a 9 digitos!❌";
        Comprobaciones.coordenadas=false;
        Comprobaciones.videomeeting=false;
        checkInputs();
    }
    else{
        lblCoordenadas.innerText = "❌¡Introduce unas coordenadas menores a 100 digitos!❌";
        Comprobaciones.coordenadas=false;
        Comprobaciones.videomeeting=false;
        checkInputs();
    }
};

let checkRadioMeeting = () => {
    let textVideo = txtVideomeeting.value;
    if(textVideo.length<=100 && textVideo.length>9){
        lblVideomeeting.innerText = "✔️";
        Comprobaciones.coordenadas=true;
        Comprobaciones.videomeeting=true;
        checkInputs();
    }else if(textVideo.length<8){
        lblVideomeeting.innerText = "❌¡Introduce un enlace mayor a 9 digitos!❌";
        Comprobaciones.coordenadas=false;
        Comprobaciones.videomeeting=false;
        checkInputs();
    }
    else{
        lblVideomeeting.innerText = "❌¡Introduce un enlace menor a 100 digitos!❌";
        Comprobaciones.coordenadas=false;
        Comprobaciones.videomeeting=false;
        checkInputs();
    }
};

let checkUserOwner = () => {
    let usrOwner = txtUserOwner.value;
    if(usrOwner.length<20 && usrOwner.length>0){
        lblUserOwner.innerText = "✔️";
        Comprobaciones.userOwner=true;
        checkInputs();
    }else if(usrOwner.length<1){
        lblUserOwner.innerText = "❌¡Introduce un ID!❌";
        Comprobaciones.userOwner=false;
        checkInputs();
    }
    else{
        lblUserOwner.innerText = "❌¡Introduce un ID menor a 20 digitos!❌";
        Comprobaciones.userOwner=false;
        checkInputs();
    }
};
let checkUserSummoner;
if(lblUserSummoner!=null){
    checkUserSummoner = () => {
        let usrSummoner = txtUserSummoner.value;
        let usrOwner = txtUserOwner.value;
        if(usrSummoner.length<20 && usrSummoner.length>0){
            lblUserSummoner.innerText = "✔️";
            Comprobaciones.userSummoner=true;
            checkInputs();
        }else if(usrSummoner.length<1){
            lblUserSummoner.innerText = "❌¡Introduce un ID!❌";
            Comprobaciones.userSummoner=false;
            checkInputs();
        }else if(usrSummoner===usrOwner){
            lblUserSummoner.innerText = "❌¡Introduce un ID diferente al dueño!❌";
            Comprobaciones.userSummoner=false;
        }
        else{
            lblUserSummoner.innerText = "❌¡Introduce un ID menor a 20 digitos!❌";
            Comprobaciones.userSummoner=false;
            checkInputs();
        }
    };
}

let checkEventName = () => {
    let textEventName = txtEventName.value;
    if(textEventName.length<=30 && textEventName.length>3){
        lblEventName.innerText = "✔️";
        Comprobaciones.eventName=true;
        checkInputs();
    }else if(textEventName.length<4){
        lblEventName.innerText = "❌¡Introduce un nombre de evento mayor a 4 digitos!❌";
        Comprobaciones.eventName=false;
        checkInputs();
    }
    else{
        lblEventName.innerText = "❌¡Introduce un nombre de evento menor a 31 digitos!❌";
        Comprobaciones.eventName=false;
        checkInputs();
    }
};

let checkTema = () => {
    let textTema = txtTema.value;
    if(textTema.length<=30 && textTema.length>3){
        lblTema.innerText = "✔️";
        Comprobaciones.tema=true;
        checkInputs();
    }else if(textTema.length<4){
        lblTema.innerText = "❌¡Introduce un nombre de evento mayor a 4 digitos!❌";
        Comprobaciones.tema=false;
        checkInputs();
    }
    else{
        lblTema.innerText = "❌¡Introduce un nombre de evento menor a 31 digitos!❌";
        Comprobaciones.tema=false;
        checkInputs();
    }
};

let checkInicio = () => {
    let textInicio = txtInicio.value;
    let textFin = txtFin.value;
    if(textInicio.length>0){
        if (new Date(textInicio)>=new Date()) {
            if(new Date(textInicio).getTime()>new Date().getTime()){
                if(textInicio.length>0 ){
                    lblInicio.innerText = "✔️";
                    Comprobaciones.inicio=true;
                    checkInputs();
                }
                else{
                    lblInicio.innerText = "❌¡Elige una fecha de inicio!❌";
                    Comprobaciones.inicio=false;
                    checkInputs();
                }
            }else{
                lblInicio.innerText = "❌¡Elige una hora de inicio que no sea una hora ya pasada!❌";
                Comprobaciones.inicio=false;
                checkInputs();
            }
            if (new Date(textFin) < new Date(textInicio)) {
                lblFin.innerText = "❌¡Elige una fecha de fin que sea despues de la hora de inicio!❌";
                Comprobaciones.fin=false;
                checkInputs();
            }
        }else{
            lblInicio.innerText = "❌¡Elige una fecha de inicio que no sea una fecha pasada o que no sea a esta hora!❌";
            Comprobaciones.inicio=false;
            checkInputs();
        }
    }else{
        lblInicio.innerText = "❌¡Elige una fecha de inicio!❌";
        Comprobaciones.inicio=false;
        checkInputs();
    }
};
let checkFin = () => {
    let textFin2 = txtFin.value;
    let textInicio2 = txtInicio.value;
    if (new Date(textInicio2).getTime()<new Date(textFin2).getTime()) {
        if(textFin2.length>0 && !(new Date(textFin2) < new Date(textInicio2))){
            lblFin.innerText = "✔️";
            Comprobaciones.fin=true;
            checkInputs();
        }
        else if (new Date(textFin2) < new Date(textInicio2)) {
            lblFin.innerText = "❌¡Elige una fecha de fin que sea después de inicio!❌";
            Comprobaciones.fin=false;
            checkInputs();
        }
        else{
            lblFin.innerText = "❌¡Elige una fecha de fin!❌";
            Comprobaciones.fin=false;
            checkInputs();
        }
    }else{
        lblFin.innerText = "❌¡Elige una hora de fin que sea mayor a la hora de inicio!❌";
        Comprobaciones.fin=false;
        checkInputs();
    }
};

window.addEventListener("load", () =>{
    
    divCoordenadas.style.display = "none";
    divVideomeeting.style.display = "none";

    if (radioCoordinates.checked) divCoordenadas.style.display = "block";
    else if (radioVideomeeting.checked)divVideomeeting.style.display = "block";

    if (radioCoordinates.checked) {
        txtVideomeeting.value = "";
        Comprobaciones.coordenadas = false;
        Comprobaciones.videomeeting = false;
        checkRadioCoor();
    }else{
        txtCoordenadas.value = "";
        Comprobaciones.coordenadas = false;
        Comprobaciones.videomeeting = false;
        checkRadioMeeting();
    }

    checkUserOwner();
    if(lblUserSummoner!=null) checkUserSummoner();
    checkEventName();
    checkTema();
    checkInicio();
    checkFin();
});

radioCoordinates.addEventListener("click", () =>{
    divVideomeeting.style.display = "none";
    divCoordenadas.style.display = "block";
    txtVideomeeting.value = "";
    Comprobaciones.coordenadas = false;
    Comprobaciones.videomeeting = false;
    checkRadioCoor();
});

radioVideomeeting.addEventListener("click", () =>{
    divVideomeeting.style.display = "block";
    divCoordenadas.style.display = "none";
    txtCoordenadas.value = "";
    Comprobaciones.coordenadas = false;
    Comprobaciones.videomeeting = false;
    checkRadioMeeting();
});

txtUserOwner.addEventListener("change", () =>{
    checkUserOwner();
});

if(lblUserSummoner!=null) {
    txtUserSummoner.addEventListener("change", () =>{
    checkUserSummoner();
    });
}

txtEventName.addEventListener("change", () =>{
    checkEventName();
});

txtTema.addEventListener("change", () =>{
    checkTema();
});

txtInicio.addEventListener("change", () =>{
    checkInicio();
});

txtFin.addEventListener("change", () =>{
    checkFin();
});

txtCoordenadas.addEventListener("change", () =>{
    checkRadioCoor();
});

txtVideomeeting.addEventListener("change", () =>{
    checkRadioMeeting();
});

