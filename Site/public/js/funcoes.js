

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
                            link.focus = cep
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
                    recuperarDados(caixas.cep[0]);
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

function recuperarDados(cep) {

    if (cep != "") {
        var script = document.createElement('script');
        script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=performGeocodingRequest';
        document.body.appendChild(script);
    } else {
        alert("CEP vazio")
    }

}


function performGeocodingRequest(conteudo) {
    const APIKEY = "2e20e7f8-9be2-4e04-a213-75b2a2040023";

    var logradouro = conteudo.logradouro
    var bairro = conteudo.bairro
    var localidade = conteudo.localidade

    var text = `${logradouro}, ${bairro}, ${localidade}`

    // Set URL method depends

    URL = `https://api.mymappi.com/v2/geocoding/direct?apikey=${APIKEY}&q=${text}&country_code=BR`;

    // Create HTTP Request
    var xmlHttp = new XMLHttpRequest();
    // Send the request
    xmlHttp.open("GET", URL, false);
    // Check Status response
    xmlHttp.onload = function () {
        // Update variable, it shows in HTML response
        response_status = xmlHttp.status;
        // Bad request != 200 :(
        if (xmlHttp.status !== 200) {
            console.log(`Response code ${response_status}. Check your browser log`)
        } else {
            console.log(`Response code ${response_status}. Check your browser log`);
        }
    };
    // Close HTML Request
    xmlHttp.send(null);
    // Return data field from response to show on result section
    dados = JSON.parse(xmlHttp.responseText).data;
    console.log(JSON.stringify(dados))
    // return JSON.parse(xmlHttp.responseText).data;

    var lat = dados[0].lat;
    var lon = dados[0].lon;

    gerarMapa(lat, lon);

}

function gerarMapa(latitude, longitude) {

    document.getElementById('mapaCaixa').remove();

    var containerMapa = document.getElementById('container_mapa');

    var mapa = document.createElement('div');
    mapa.id = "mapaCaixa";

    containerMapa.appendChild(mapa)

    var map = L.map('mapaCaixa').setView([latitude, longitude], 13);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    L.marker([latitude, longitude]).addTo(map)
        .bindPopup('')
}


function addListeners() {
    var caixas = JSON.parse(sessionStorage.INFO_CAIXA);

    var numberReference = caixas.nome; // variável para pegar o length e manipular a quantidade de caixas cadastrados

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

    for (var i = 0; i < numberReference.length; i++) {
        var serialNumber = caixas.serialNumber[i];
        String(serialNumber)
        document.getElementById(serialNumber).addEventListener("click", function () {
            cep = this.focus

            recuperarDados(cep)
        })
    }
}