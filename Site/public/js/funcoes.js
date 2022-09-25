// sessão
function validarSessao() {

    var email = sessionStorage.EMAIL_USUARIO;
    var usuario = sessionStorage.NOME_USUARIO;
    var spanUsuario = document.getElementById("span_user");

    if (email != null ) {
        spanUsuario.innerHTML = usuario;
    } else {
        window.location = "../login.html";
    }
}

function limparSessao() {
    sessionStorage.clear();
    window.location = "../login.html";
}



