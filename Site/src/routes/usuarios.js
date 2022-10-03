var express = require("express");
var router = express.Router();

var usuarioController = require("../controllers/usuarioController");

router.get("/", function (req, res) {
    usuarioController.testar(req, res);
});

router.post("/listar", function (req, res) {
    usuarioController.listar(req, res);
});

router.post("/selectCargo", function (req, res) {
    usuarioController.selectCargo(req, res);
});

//Recebendo os dados do html e direcionando para a função cadastrar de usuarioController.js
router.post("/cadastrar", function (req, res) {
    usuarioController.cadastrar(req, res);
})

router.post("/autenticar", function (req, res) {
    usuarioController.entrar(req, res);
});

router.post("/cadastrarMaquina", function (req, res) {
    usuarioController.cadastrarMaquina(req, res);
});

router.post("/cadastrarComponente", function (req, res) {
    usuarioController.cadastrarComponente(req, res);
});

module.exports = router;