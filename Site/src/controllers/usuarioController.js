var usuarioModel = require("../models/usuarioModel");
var nodemailer = require("nodemailer");
const { query } = require("mssql");

var sessoes = [];

function testar(req, res) {
    console.log("ENTRAMOS NA usuarioController");
    res.json("ESTAMOS FUNCIONANDO!");
}

function listarCaixas(req, res) {
    var cnpj = req.body.cnpjServer;

    if (cnpj == undefined) {
        res.status(400).send("Seu cnpj está undefined!");
    }
    else {
        usuarioModel.listarCaixas(cnpj)
            .then(function (resultado) {
                if (resultado.length > 0) {
                    res.status(200).json(resultado);
                } else {
                    res.status(204).send("Nenhum resultado encontrado!")
                }
            }).catch(
                function (erro) {
                    console.log(erro);
                    console.log("Houve um erro ao realizar a consulta! Erro: ", erro.sqlMessage);
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }

}

function selectCargo(req, res) {

    var query = req.body.queryServer;

    usuarioModel.selectCargo(query)
        .then(function (resultado) {
            if (resultado.length > 0) {
                res.status(200).json(resultado);
            } else {
                res.status(204).send("Nenhum resultado encontrado!")
            }
        }).catch(
            function (erro) {
                console.log(erro);
                console.log("Houve um erro ao realizar a consulta! Erro: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        );

}

function entrar(req, res) {
    var email = req.body.emailServer;
    var senha = req.body.senhaServer;

    if (email == undefined) {
        res.status(400).send("Seu email está undefined!");
    } else if (senha == undefined) {
        res.status(400).send("Sua senha está indefinida!");
    } else {

        usuarioModel.entrar(email, senha)
            .then(
                function (resultado) {
                    console.log(`\nResultados encontrados: ${resultado.length}`);
                    console.log(`Resultados: ${JSON.stringify(resultado)}`); // transforma JSON em String

                    if (resultado.length == 1) {
                        console.log(resultado);
                        res.json(resultado[0]);
                    } else if (resultado.length == 0) {
                        res.status(403).send("Email e/ou senha inválido(s)");
                    } else {
                        res.status(403).send("Mais de um usuário com o mesmo login e senha!");
                    }
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log("\nHouve um erro ao realizar o login! Erro: ", erro.sqlMessage);
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }

}

function cadastrar(req, res) {
    // Crie uma variável que vá recuperar os valores do arquivo cadastro.html
    var cnpj = req.body.cnpjServer;
    var cargo = req.body.cargoServer;
    var nome = req.body.nomeServer;
    var email = req.body.emailServer;
    var senha = req.body.senhaServer;
    var cadastroBanco = req.body.cadastroBanco;
    // Faça as validações dos valores
    if (cnpj == undefined) {
        res.status(400).send("Sua senha está undefined!");
    } else if (nome == undefined) {
        res.status(400).send("Sua senha está undefined!");
    } else if (email == undefined) {
        res.status(400).send("Sua senha está undefined!");
    } else if (senha == undefined) {
        res.status(400).send("Sua senha está undefined!");
    } else {
        // Passe os valores como parâmetro e vá para o arquivo usuarioModel.js
        if (cadastroBanco) {
            var nomeBanco = req.body.nomeBancoServer;
            var emailBanco = req.body.emailBancoServer;

            usuarioModel.cadastrarBanco(nomeBanco, emailBanco, cnpj)
                .then(
                    function (resultado) {
                        enviarEmail(email, senha, emailBanco);
                        res.json(resultado);
                    }
                ).catch(
                    function (erro) {
                        console.log(erro);
                        console.log(
                            "\nHouve um erro ao realizar o cadastro! Erro: ",
                            erro.sqlMessage
                        );
                        res.status(500).json(erro.sqlMessage);
                    }
                );
        }

        usuarioModel.cadastrarUsuario(nome, email, senha, cargo, cnpj)
            .then(
                function (resultado) {
                    res.json(resultado);
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro ao realizar o cadastro! Erro: ",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }
}

function cadastrarMaquina(req, res) {

    // Crie uma variável que vá recuperar os valores do arquivo cadastro.html
    var serialNumber = req.body.serialNumberServer;
    var nome = req.body.nomeServer;
    var cep = req.body.cepServer;
    var componente = req.body.componenteServer;
    var cnpj = req.body.cnpjServer;
    var cidade = req.body.cidadeServer;
    var regiao = req.body.regiaoServer;

    if (serialNumber == undefined) {
        res.status(400).send("Seu serial number está undefined!");
    } else if (nome == undefined) {
        res.status(400).send("O nome da máquina está undefined!");
    } else if (cep == undefined) {
        res.status(400).send("O cep está undefined!");
    } else if (componente == undefined) {
        res.status(400).send("O componente está undefined!");
    } else {
        // Passe os valores como parâmetro e vá para o arquivo usuarioModel.js
        usuarioModel.cadastrarMaquina(serialNumber, nome, cep, cnpj, cidade, regiao)
            .then(
                function (resultado) {
                    res.json(resultado);
                    usuarioModel.cadastrarComponente(serialNumber, componente)
                        .then(
                            function (resultado) {
                                res.json(resultado);
                            }
                        ).catch(
                            function (erro) {
                                console.log(erro);
                                console.log(
                                    "\nHouve um erro ao realizar o cadastro! Erro: ",
                                    erro.sqlMessage
                                );
                                res.status(500).json(erro.sqlMessage);
                            }
                        );
                }
            ).catch(
                function (erro) {
                    console.log(erro);
                    console.log(
                        "\nHouve um erro ao realizar o cadastro! Erro: ",
                        erro.sqlMessage
                    );
                    res.status(500).json(erro.sqlMessage);
                }
            );
    }

}

function cadastrarComponente(req, res) {

    serialNumber = req.body.serialNumberServer;
    componente = req.body.componenteServer;

    usuarioModel.cadastrarComponente(serialNumber, componente)
        .then(
            function (resultado) {
                res.json(resultado);
            }
        ).catch(
            function (erro) {
                console.log(erro);
                console.log(
                    "\nHouve um erro ao realizar o cadastro! Erro: ",
                    erro.sqlMessage
                );
                res.status(500).json(erro.sqlMessage);
            }
        );

}


function enviarEmail(email, senha, emailBanco) {
    var transporter = nodemailer.createTransport({
        service: 'outlook',
        auth: {
            user: '222-1cco-grupo10@bandtec.com.br',
            pass: '1cco*grupo10'
        }
    });

    var mailOptions = {
        from: '222-1cco-grupo10@bandtec.com.br',
        to: emailBanco,
        subject: 'Acesso aos serviços da KASH+!',
        html: '<h1>Bem vindo a família Kash+!!!</h1><br>' +
            "<p>Aqui estão as credenciais do seu usuário administrador para acessar a nossa dashboard, através dele você poderá cadastrar novos usuários: </p><br>" +
            `Email: ${email} <br>` +
            `Senha: ${senha}`,
    };

    transporter.sendMail(mailOptions, function (error, info) {
        if (error) {
            console.log(error);
        } else {
            console.log('Email enviado: ' + info.response);
        }
    })
}

function listarQuantidade(req, res) {

        usuarioModel.listarQuantidade()
           .then(
               function (resultado) {
                   res.json(resultado);
               }
           ).catch(
               function (erro) {
                   console.log(erro);
                   console.log(
                       erro.sqlMessage
                   );
                   res.status(500).json(erro.sqlMessage);
              }
            );

}

function listarMaquinasRegiao(req, res) {

    var query = req.body.queryServer;

    usuarioModel.listarMaquinasRegiao(query)
        .then(function (resultado) {
            if (resultado.length > 0) {
                res.status(200).json(resultado);
            } else {
                res.status(204).send("Nenhum resultado encontrado!")
            }
        }).catch(
            function (erro) {
                console.log(erro);
                console.log("Houve um erro ao realizar a consulta! Erro: ", erro.sqlMessage);
                res.status(500).json(erro.sqlMessage);
            }
        );
    }

function verificarComponentes(req, res) {
    var serialNumber = req.body.serialNumberServer;

        usuarioModel.listarComponentes(serialNumber)
           .then(
               function (resultado) {
                   res.json(resultado);
               }
           ).catch(
               function (erro) {
                   console.log(erro);
                   console.log(
                       erro.sqlMessage
                   );
                   res.status(500).json(erro.sqlMessage);
              }
            );

}



module.exports = {
    entrar,
    cadastrar,
    listarCaixas,
    selectCargo,
    testar,
    cadastrarMaquina,
    cadastrarComponente,
    listarQuantidade,
    listarMaquinasRegiao,
    verificarComponentes,
}