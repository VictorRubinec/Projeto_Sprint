var usuarioModel = require("../models/consultaModel");

function testar(req, res) {
    console.log("ENTRAMOS NA consultaController");
    res.json("ESTAMOS FUNCIONANDO!");
}



module.exports = {
    testar,
}
