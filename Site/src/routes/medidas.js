var express = require("express");
var router = express.Router();

var medidaController = require("../controllers/medidaController");

router.get("/ultimasCpu/:serialNumber", function (req, res) {
    medidaController.buscarUltimasMedidasCpu(req, res);
});

router.get("/ultimasCondicaoCpu/:serialNumber", function (req, res) {
    medidaController.buscarUltimasCondicaoCpu(req, res);
});

router.get("/ultimasCondicaoRam/:serialNumber", function (req, res) {
    medidaController.buscarUltimasCondicaoRam(req, res);
});

router.get("/ultimasCondicaoDisco/:serialNumber", function (req, res) {
    medidaController.buscarUltimasCondicaoDisco(req, res);
});

router.get("/tempo-realCpu/:serialNumber", function (req, res) {
    medidaController.buscarMedidasEmTempoRealCpu(req, res);
});

router.get("/ultimasRam/:serialNumber", function (req, res) {
    medidaController.buscarUltimasMedidasRam(req, res);
});

router.get("/tempo-realRam/:serialNumber", function (req, res) {
    medidaController.buscarMedidasEmTempoRealRam(req, res);
});

router.get("/ultimasDisco/:serialNumber", function (req, res) {
    medidaController.buscarUltimasMedidasDisco(req, res);
});

router.get("/maxDisco/:serialNumber", function (req, res) {
    medidaController.buscarMaxDisco(req, res);
});

router.get("/maxRam/:serialNumber", function (req, res) {
    medidaController.buscarMaxRam(req, res);
});


module.exports = router;