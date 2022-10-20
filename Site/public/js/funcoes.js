

// sessão
function validarSessao() {

    var email = sessionStorage.EMAIL_USUARIO;
    var usuario = sessionStorage.NOME_USUARIO;
    var spanUsuario = document.getElementById("span_user");

    if (email != null) {
        spanUsuario.innerHTML = usuario;
    } else {
        window.location = "../login.html";
    }
}

function limparSessao() {
    sessionStorage.clear();
    window.location = "../login.html";
}


function verificarCargo() {
    cargo = sessionStorage.CARGO_USUARIO;

    if (cargo == "tec") {
        document.getElementById("link_add_user").style.display = "none";
        document.getElementById("link_list_user").style.display = "none";
        document.getElementById("titulo_cargo").innerHTML = "Técnico";
    }
    else if (cargo == "ger") {
        document.getElementById("link_add_user").style.display = "none";
        document.getElementById("link_add_caixa").style.display = "none";
        document.getElementById("titulo_cargo").innerHTML = "Gerente";
    }
}

function listarCaixas() {

    var cnpjVar = sessionStorage.BANCO_ID;

    fetch("/usuarios/listarCaixas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            cnpjServer: cnpjVar
        })
    }).then(function (resposta) {
        console.log("ESTOU NO THEN DO listar()!")
        console.log(resposta);

        if (resposta.status == 204) {
            var divCaixas = document.getElementById("div_caixas");

            var item = document.createElement('li');
            item.className = "nav-item";
            divCaixas.appendChild(item);

            var link = document.createElement('a');
            link.className = "nav-link";
            item.appendChild(link);


            var span = document.createElement('span');
            span.innerHTML = "Nenhum caixa cadastrado";
            link.appendChild(span);
        }
        else if (resposta.ok) {
            console.log(resposta);

            resposta.json().then(json => {
                console.log(json);
                console.log(JSON.stringify(json));

                if (json.length > 0) {
                    var divCaixas = document.getElementById("div_caixas");

                    var item = document.createElement('li');
                    item.className = "nav-item";
                    divCaixas.appendChild(item);

                    var caixas = {
                        "nome": [],
                        "serialNumber": [],
                        "cep": []
                    }

                    for (var i = 0; i < json.length; i++) {
                        maquina = json[i].Maquina;
                        serialNumber = json[i].NumeroSerial;
                        cep = json[i].Cep;

                        var link = document.createElement('a');
                        link.className = "nav-link";

                        if (window.location == "http://localhost:3333/dashboard/index.html") { // verificando se está na index ou não
                            link.id = serialNumber;
                        }
                        else {
                            link.href = "index.html"
                        }

                        item.appendChild(link);

                        var icon = document.createElement('i');
                        icon.className = "fas fa-donate";
                        link.appendChild(icon);

                        var span = document.createElement('span');
                        span.innerHTML = maquina;
                        link.appendChild(span);

                        caixas.nome.push(maquina);
                        caixas.serialNumber.push(serialNumber);
                        caixas.cep.push(cep);

                    }

                    sessionStorage.INFO_CAIXA = JSON.stringify(caixas);
                    verificarComponentes(caixas.serialNumber[0]);
                    obterDadosGraficoCpu(caixas.serialNumber[0]);
                    obterDadosGraficoRam(caixas.serialNumber[0]);
                    obterDadosGraficoDisco(caixas.serialNumber[0]);
                    setRamMax(caixas.serialNumber[0]);
                }

            });



        } else {

            console.log("Houve um erro ao tentar listar os caixas!");

            resposta.text().then(texto => {
                console.log(texto)
            });
        }

    }).catch(function (erro) {
        console.log(erro);
    })

    return false;
}


function addListeners() {
    var caixas = JSON.parse(sessionStorage.INFO_CAIXA);

    var numberReference = caixas.nome; // variável para pegaro length e manipular a quantidade de caixas cadastrados

    for (var i = 0; i < numberReference.length; i++) {
        var serialNumber = caixas.serialNumber[i];
        String(serialNumber)
        document.getElementById(serialNumber).addEventListener("click", function () {
            key = this.id;

            verificarComponentes(key);
            obterDadosGraficoDisco(key);
            obterDadosGraficoCpu(key);
            obterDadosGraficoRam(key);
            setRamMax(key);
        })
    }
}

function gerarMapa(latitude, longitude){
    var map = L.map('mapaCaixa').setView([-23.704886, -46.564630], 13);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    L.marker([-23.704886, -46.564630]).addTo(map)
        .bindPopup('')
        .openPopup();
}