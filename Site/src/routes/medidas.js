var express = require("express");
var router = express.Router();

var medidaController = require("../controllers/medidaController");

router.get("/ultimasCpu/:serialNumber", function (req, res) {
    medidaController.buscarUltimasMedidasCpu(req, res);
});

router.get("/tempo-realCpu/:serialNumber", function (req, res) {
    medidaController.buscarMedidasEmTempoRealCpu(req, res);
});


module.exports = router;